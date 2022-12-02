package com.ylkj.common.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ylkj.common.bytes.BytesUtils;
import com.ylkj.fbs.service.bean.ProReceivingEmqEntity;

/**
 * 接收到数据进行解码器解码
 * 
 * @author ljw
 *
 */
@Service("emqFrameDecoder")
public class EmqFrameDecoder {
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UmelinkApiConfig UmelinkApiConfig;

	// **************************************硬件上传新协议*********************************************//
	public List<ProReceivingEmqEntity> decodeNew(byte[] bytes) {
		List<ProReceivingEmqEntity> childList = new ArrayList<ProReceivingEmqEntity>();
		if (bytes == null) {
			return childList;
		}
		String byteStr = BytesUtils.toHexString(bytes, true);

		log.info(byteStr);
		int leng = 0;
		getChild(byteStr, leng, childList);
		return childList;
	}

	public List<ProReceivingEmqEntity> decodeNew(String byteStr) {
		// log.info(byteStr);
		List<ProReceivingEmqEntity> childList = new ArrayList<ProReceivingEmqEntity>();
		if (byteStr == null) {
			return childList;
		}
		int leng = 0;
		getChild(byteStr, leng, childList);
		return childList;
	}

	private List<ProReceivingEmqEntity> getChild(String byteStr, int leng,
			List<ProReceivingEmqEntity> childList) {
		int str = byteStr.length();
		int strLeng = 0;
		if (leng < str) {
			try {
				strLeng = Integer.parseInt(
						byteStr.substring(leng + 4, leng + 8), 16) * 2;
				// strLeng = Integer.parseInt(
				// byteStr.substring(leng + 4, leng + 6), 16) * 2;
				String data = byteStr.substring(leng, leng + strLeng);
				// ProReceivingEmqEntity ProReceivingEmqEntity = analyze(data);
				ProReceivingEmqEntity ProReceivingEmqEntity = decryptionAnalyze(data);
				childList.add(ProReceivingEmqEntity);
				leng += strLeng;
				getChild(byteStr, leng, childList);
			} catch (Exception e) {
				log.error("解析数据有问题..." + byteStr);
			}
		}
		return childList;
	}

	private ProReceivingEmqEntity decryptionAnalyze(String byteStr)
			throws Exception {
		// //CCCC 1D 00 9B6F 000D6F00 0BCB21BF 000D6F00 0BCB5B94 03 01 03 01 A5
		// DDDD
		ProReceivingEmqEntity dao = new ProReceivingEmqEntity();
		// //CCCC 1D 00 9B6F 000D6F00 0BCB21BF 000D6F00 0BCB5B94 03 01 03 01 A5
		// DDDD
		dao.setCheckIsTrue(true);
		if (byteStr.length() > 50 && byteStr.substring(46, 48).equals("01")
				&& byteStr.substring(48, 50).equals("03")) {
			dao.setIdentType(byteStr.substring(8, 10));
			dao.setNekId(byteStr.substring(10, 14));
			dao.setZkMac(byteStr.substring(14, 30));
			dao.setDkMac(byteStr.substring(30, 46));
			dao.setMainMl(byteStr.substring(46, 48));
			dao.setZMl(byteStr.substring(48, 50));
			dao.setData(byteStr.substring(50, byteStr.length() - 6));
		} else {
			dao.setIdentType(byteStr.substring(8, 10));
			dao.setDkMac(byteStr.substring(10, 26));
			dao.setMainMl(byteStr.substring(26, 28));
			dao.setZMl(byteStr.substring(28, 30));
			dao.setData(byteStr.substring(30, byteStr.length() - 6));
		}

		dao.setReceBytesBtr(byteStr);
		log.info("---------receive data:---" + JSON.toJSONString(byteStr));
		// 包长读最少长度，如果不满足则认为包有问题
		log(dao, byteStr);
		return dao;
	}

	public void log(ProReceivingEmqEntity dao, String byteStr) {
		// logger.info("Decoder str=" + byteStr);
		// logger.info("getDataLeng=" + dao.getDataLeng());
		// logger.info("getIdentType=" + dao.getIdentType());
		// logger.info("getNekId=" + dao.getNekId());
		// logger.info("getZkMac=" + dao.getZkMac());
		// logger.info("getDkMac=" + dao.getDkMac());
		// logger.info("getPackLeng=" + dao.getPackLeng());
		// logger.info("getMainMl=" + dao.getMainMl());
		// logger.info("getzMl=" + dao.getZMl());
		// logger.info("getData=" + dao.getData());
		//
		// logger.info("getCheckDate=" + dao.getCheckDate());

	}

}
