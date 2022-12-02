package com.ylkj.common.bytes;


public class BytesUtils extends ByteUtils {

	/**
	 * byte 数组转换为16进制字符串String
	 * 
	 * @param data
	 *            待转换的数据
	 * @param upper
	 *            转换的结果数据采用大写还是小写
	 * @return 结果字符串
	 */
	public static String toHexString(byte[] data, boolean upper) {
		StringBuffer sb = new StringBuffer();
		for (byte d : data) {
			sb.append(byte2char((byte) ((d >> 4) & 0xf), upper));
			sb.append(byte2char((byte) (d & 0xf), upper));
		}
		return UtilStr.customToString(sb);
	}

	/**
	 * 16进制字符转String 转换为 byte数组
	 * 
	 * @param data
	 *            待转换的数据
	 * @return 结果数组
	 */
	public static byte[] toBytes(String data) throws NumberFormatException {

		int length = data.length();
		byte result[] = new byte[length >> 1];
		for (int i = 0; i < length; i += 2) {
			byte high = char2byte(data.charAt(i));
			byte low = char2byte(data.charAt(i + 1));
			result[i >> 1] = (byte) ((high << 4) | low);
		}
		return result;
	}

	/**
	 * 比较两个数组是否相同
	 * 
	 * @param src
	 *            原数组
	 * @param dest
	 *            目的数组
	 * @param length
	 *            要比较的数据长度
	 * @return 是否形同
	 */
	public static boolean compare(byte[] src, byte[] dest, int length) {
		if (src.length < length || dest.length < length) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			if (src[i] != dest[i])
				return false;
		}
		return true;
	}
}
