package com.ylkj.common.api;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ylkj.common.bytes.ByteCheck;
import com.ylkj.fbs.service.bean.UmelinkEncapsulationBean;

/***
 * init so
 * 
 * @author ljw
 *
 */
@Service
public class UmelinkApiConfig {

	static Logger log = LoggerFactory.getLogger(UmelinkApiConfig.class);

	/***
	 * 根据指令的参数，封装下行指令转换成字节
	 * 
	 * According to the parameters of the instruction, the encapsulated downlink
	 * instruction is converted to bytes
	 * 
	 * @param bean
	 * @return
	 */
	public byte[] encrypt(UmelinkEncapsulationBean bean) {
		log.info("Encapsulate objects=" + JSONObject.toJSONString(bean));
		try {
			int valLen = 1;
			if (bean.getVal() != null) {
				valLen = bean.getVal().getBytes().length / 2;
			} else {
				bean.setVal("0");
			}
			int dataLen = 11 + Integer.parseInt(bean.getNum_of_NWK(), 16) * 2
					+ valLen;
			byte[] b = new byte[(int) dataLen];
			// 包头
			b[0] = (byte) 0xCC;
			b[1] = (byte) 0xCC;
			// 帧长度
			byte[] lang = hexString2Bytes(Integer.toHexString(dataLen));
			if (lang.length > 1) {
				b[2] = lang[0];
				b[3] = lang[1];
			} else {
				b[2] = (byte) 0x00;
				b[3] = lang[0];
			}
			// // 标识 00单播 01组播
			b[4] = hexString2Bytes(bean.getIdentType())[0];
			// // 受控地址数量
			b[5] = hexString2Bytes(bean.getNum_of_NWK())[0];
			// // 受控地址
			int length = (int) 6;
			for (int i = 0; i < bean.getNWK().size(); i++) {
				b[length] = hexString2Bytes(bean.getNWK().get(i))[0];
				length++;
				b[length] = hexString2Bytes(bean.getNWK().get(i))[1];
				length++;
			}
			// // 主命令
			b[length] = hexString2Bytes(bean.getMaster_cmd())[0];
			// // 子命令
			length = length + 1;
			b[length] = hexString2Bytes(bean.getSlave_cmd())[0];
			String data = bean.getVal();

			byte[] val = hexString2Bytes(data);
			// // 数据包
			for (int i = 0; i < bean.getVal().getBytes().length / 2; i++) {
				length++;
				b[length] = val[i];
			}
			byte code = ByteCheck.endCode(b);
			b[dataLen - 3] = code;
			b[dataLen - 2] = (byte) 0xDD;
			b[dataLen - 1] = (byte) 0xDD;
			// log.info("发送字节数组为：" + Arrays.toString(b));
			// log.info("方法三：" + BytesHexStrTranslate.bytes2HexString(b));
			// return UmelinkOutsideMessImp.encryptionToSend(reqDao);
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage() + " data:" + JSON.toJSONString(bean));
			return null;
		}
	}

	public static MqttMessage getMessage() {
		MqttMessage message = new MqttMessage();
		message = new MqttMessage();
		message.setQos(1);
		message.setRetained(true);
		return message;
	}

	/*
	 * 字符转换为字节
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/*
	 * 16进制字符串转字节数组
	 */
	public static byte[] hexString2Bytes(String hex) {

		if ((hex == null) || (hex.equals(""))) {
			return null;
		} else {
			if (hex.length() % 2 != 0) {
				hex = "0" + hex;
			}
			hex = hex.toUpperCase();
			int len = hex.length() / 2;
			byte[] b = new byte[len];
			char[] hc = hex.toCharArray();
			for (int i = 0; i < len; i++) {
				int p = 2 * i;
				b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
			}
			return b;
		}

	}

}
