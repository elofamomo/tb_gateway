package com.ylkj.common.bytes;

import com.ylkj.fbs.service.bean.EmqZigbeeClassToDao;

/***
 * 十六进制字符串按位异或运算 ByteCheck.java
 *
 * @author Administrator
 * @date 2022年11月27日-下午4:30:19
 ***/
public class ByteCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String
		// str="CCCC1D009B6F000D6F000BCB21BF000D6F000BCB5B9403010301A5DDDD";
		// String code = checkcode("010200000002");
		// System.out.println("code：" + code);
		//
		//
		// System.out.println("code：" + str.substring(0,2));
		// System.out.println("code：" + str.substring(2,4));
		// System.out.println("code：" + str.substring(4,str.length()-4));
	}

	public static byte endCode(byte[] b) {
		String bcode = BytesHexStrTranslate.bytes2HexString(b);
		String endCode = ByteCheck
				.checkcode(bcode.substring(4, bcode.length()));
		// System.out.println("endCode的值："+endCode);
		byte code;
		if (endCode.length() > 1) {
			code = EmqZigbeeClassToDao.hexString2Bytes(endCode + "")[0];
		} else {
			code = EmqZigbeeClassToDao.hexString2Bytes("0" + endCode)[0];
		}
		return code;

	}

	public static String checkcode(String para) {
		int length = para.length() / 2;
		String[] dateArr = new String[length];

		for (int i = 0; i < length; i++) {
			dateArr[i] = para.substring(i * 2, i * 2 + 2);
		}
		String code = "00";
		for (int i = 0; i < dateArr.length; i++) {
			code = xor(code, dateArr[i]);
		}
		if (code.length() == 1) {
			code = "0" + code;
		}
		return code;
	}

	private static String xor(String strHex_X, String strHex_Y) {
		// 将x、y转成二进制形式
		String anotherBinary = Integer.toBinaryString(Integer.valueOf(strHex_X,
				16));
		String thisBinary = Integer.toBinaryString(Integer
				.valueOf(strHex_Y, 16));
		String result = "";
		// 判断是否为8位二进制，否则左补零
		if (anotherBinary.length() != 8) {
			for (int i = anotherBinary.length(); i < 8; i++) {
				anotherBinary = "0" + anotherBinary;
			}
		}
		if (thisBinary.length() != 8) {
			for (int i = thisBinary.length(); i < 8; i++) {
				thisBinary = "0" + thisBinary;
			}
		}
		// 异或运算
		for (int i = 0; i < anotherBinary.length(); i++) {
			// 如果相同位置数相同，则补0，否则补1
			if (thisBinary.charAt(i) == anotherBinary.charAt(i))
				result += "0";
			else {
				result += "1";
			}
		}
		// System.out.println(result);
		return Integer.toHexString(Integer.parseInt(result, 2));
	}
}
