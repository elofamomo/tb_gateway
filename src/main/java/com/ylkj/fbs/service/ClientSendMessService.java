package com.ylkj.fbs.service;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylkj.common.api.UmelinkApiConfig;
import com.ylkj.common.util.CommonUtils;
import com.ylkj.common.util.R;
import com.ylkj.fbs.mq.send.SendEmqClientService;
import com.ylkj.fbs.service.bean.UmelinkEncapsulationBean;

/***
 * 封装下行指令服务类/Encapsulate downlink instruction service class
 *
 * @author Administrator
 * @date 2022年11月29日-下午3:02:19
 ***/
@Service
public class ClientSendMessService {
	@Autowired
	UmelinkApiConfig umelinkApiConfig;
	@Autowired
	private SendEmqClientService sendEmqClientService;

	public R send_message(UmelinkEncapsulationBean bean) {
		MqttMessage message = UmelinkApiConfig.getMessage();
		try {
			byte[] messageByte = umelinkApiConfig.encrypt(bean);
			message.setPayload(messageByte);
			sendEmqClientService.sendessage(bean.getMac(), message);
			return CommonUtils.msgNotCheckNull(true);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonUtils.msgNotCheckNull(false);
		}
	}
}
