package com.ylkj.fbs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ylkj.common.api.EmqCmdKey;
import com.ylkj.common.api.EmqFrameDecoder;
import com.ylkj.common.api.UmelinkApiConfig;
import com.ylkj.common.util.CommonUtils;
import com.ylkj.common.util.DateUtils;
import com.ylkj.common.util.R;
import com.ylkj.common.util.StopWatchLog;
import com.ylkj.config.ConfigEmq;
import com.ylkj.fbs.mq.send.SendEmqClientService;
import com.ylkj.fbs.service.bean.ProReceivingEmqEntity;
import com.ylkj.fbs.service.bean.UmelinkEncapsulationBean;

/***
 * 接收终端数据，进行解析，进行业务处理
 * 
 * Receive terminal data, analyze it, and conduct business processing
 *
 * @author ljw
 * @date 2022年11月28日-下午3:31:20
 ***/
@Service
public class EmqServerPushCallbackService {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ConfigEmq config;

	@Autowired
	UmelinkApiConfig umelinkApiConfig;
	@Autowired
	private SendEmqClientService sendEmqClientService;
	@Autowired
	private EmqFrameDecoder emqFrameDecoder;

	// ***************************************************************
	// ****1、目前解析支持所有产品（除zigbee产品之外）
	// ****2、目前只做了上报解析，回复的处理，别的指令没有做处理，根据文档一一处理即可
	// ****3、目前只实现基本的控制指令，调整亮度值，即开关灯
	// 1. Currently, all products (except zigbee products) are supported
	// 2. At present, only the report analysis and reply processing have been
	// done, and other instructions have not been processed, which can be
	// processed one by one according to the document
	// 3 At present, only the basic control command is realized, and the
	// brightness value is adjusted, that is, the light is turned on and off
	// ***************************************************************

	/***
	 * 解析设备上来的数据，进行处理
	 * 
	 * Parse the data from the equipment for processing
	 * 
	 * @param bytes
	 */

	public void handle(String bytes) {
		try {
			List<ProReceivingEmqEntity> list = emqFrameDecoder.decodeNew(bytes);
			for (ProReceivingEmqEntity dao : list) {
				try {
					StopWatch sw = new StopWatch();
					sw.start(dao.getDkMac() + "计算耗时...");

					checkPeriodicReporting(dao);
					// logger.info(JSONObject.toJSONString(r));

					logger.info(StopWatchLog.logStop(sw));

					// System.out.println(JSON.toJSON(ProReceivingEmqEntity));
				} catch (Exception e) {

					logger.error("处理命令出错..." + e.getMessage() + " error data"
							+ JSON.toJSONString(dao));
				}
			}

		} catch (Exception e) {
			logger.error(bytes);
			logger.error("解析命令出错..." + e.getMessage());
		}

	}

	/***
	 * 
	 * @param dao
	 */
	public void checkPeriodicReporting(ProReceivingEmqEntity dao) {
		logger.info("Periodic reporting Dao=" + JSONObject.toJSONString(dao));
		int key_main = Integer.parseInt(dao.getMainMl(), 16);
		int zMl = Integer.parseInt(dao.getzMl(), 16);
		Boolean isReturn = false;
		logger.info("macID=" + dao.getDkMac());
		switch (key_main) {
		case EmqCmdKey.M_SEND_KEY_2:
			switch (zMl) {
			case EmqCmdKey.SK_SEND_14:
				logger.info("周期上报/Periodic reporting..."
						+ DateUtils.format(new Date(),
								DateUtils.DATE_TIME_PATTERN));
				isReturn = true;
				break;
			case EmqCmdKey.SK_SEND_21:
				logger.info("时钟同步上报/Clock synchronization report..."
						+ DateUtils.format(new Date(),
								DateUtils.DATE_TIME_PATTERN));
				String result = DateUtils.format(new Date(), "yy"
						+ DateUtils.DATE_PICTURE_PATTERN);
				List<String> nwkList = new ArrayList<String>();
				nwkList.add("0000");
				// 受控地址数量Number of controlled addresses
				dao.setNwkList(nwkList);
				dao.setData(result);
				isReturn = true;
				break;
			default:
				break;
			}
			break;
		case EmqCmdKey.M_SEND_KEY_15: {
			logger.info("上电0F指令回复..."
					+ DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
			// 回复reply
			isReturn = true;
			break;
		}
		default:
			break;
		}
		if (isReturn) {
			// 回复reply
			mqSendFaultReturn(dao);
		}
	}

	/***
	 * 收到设备信息，进行回复操作 Receive device information and reply
	 * 
	 * @param dao
	 * @return
	 */
	public R mqSendFaultReturn(ProReceivingEmqEntity dao) {
		MqttMessage message = UmelinkApiConfig.getMessage();
		try {
			String macId = dao.getZkMac();
			if (dao.getZkMac() == null || dao.getZkMac().equals("")) {
				macId = dao.getDkMac();
			}

			UmelinkEncapsulationBean bean = new UmelinkEncapsulationBean();
			bean.setMac(macId);
			bean.setMaster_cmd(dao.getMainMl());
			bean.setSlave_cmd(dao.getZMl());
			List<String> nwk = new ArrayList<String>();
			bean.setIdentType(dao.getIdentType());
			if (dao.getNekId() == null) {
				nwk.add("0000");
			} else {
				nwk.add(dao.getNekId());
			}

			bean.setNWK(nwk);
			bean.setNum_of_NWK(Integer.toHexString(1));
			int mainMl = Integer.parseInt(dao.getMainMl(), 16);
			int zMl = Integer.parseInt(dao.getzMl(), 16);
			switch (mainMl) {
			case EmqCmdKey.M_SEND_KEY_1: {
				switch (zMl) {
				case EmqCmdKey.SK_SEND_12: {
					bean.setVal(dao.getData());
					break;
				}
				default: {
					bean.setVal("00");
					break;
				}
				}
				break;
			}
			case EmqCmdKey.M_SEND_KEY_2: {
				switch (zMl) {
				case EmqCmdKey.SK_SEND_4:
				case EmqCmdKey.SK_SEND_13:
				case EmqCmdKey.SK_SEND_21: {
					bean.setVal(dao.getData());
					break;
				}
				default: {
					bean.setVal("00");
					break;
				}
				}
				break;
			}
			case EmqCmdKey.M_SEND_KEY_15: {
				switch (zMl) {
				case EmqCmdKey.SK_SEND_REDERICCID_KEY_1501: {
					bean.setVal("81");
					break;
				}
				default: {
					bean.setVal("00");
					break;
				}
				}
				break;
			}
			case EmqCmdKey.M_SEND_KEY_10: {
				switch (zMl) {
				case EmqCmdKey.OTA_SEND_KEY1: {
					bean.setVal("FF");
					bean.setSlave_cmd("02");
					break;
				}
				case EmqCmdKey.OTA_SEND_KEY4: {
					bean.setVal("00");
					break;
				}
				default: {
					bean.setVal("00");
					break;
				}
				}
				break;
			}
			default: {
				bean.setVal("00");
				break;
			}
			}

			dao.setDataLeng("");
			byte[] messageByte = umelinkApiConfig.encrypt(bean);
			// 封装消息体Encapsulate message body
			message.setPayload(messageByte);
			// 发送消息send message
			sendEmqClientService.sendessage(macId, message);

			return CommonUtils.msgNotCheckNull(true);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonUtils.msgNotCheckNull(false);
		}
	}
}