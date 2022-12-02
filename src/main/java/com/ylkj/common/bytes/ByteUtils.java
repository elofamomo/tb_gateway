package com.ylkj.common.bytes;

public class ByteUtils {

	/**
	 * char转换为byte,注意两个char才能转换为一个byte
	 * 
	 * @param c
	 *            带转换的char
	 * @return 转换的结果
	 * @throws NumberFormatException
	 */
	public static byte char2byte(char c) throws NumberFormatException {
		if (c >= 0x30 && c <= 0x39) {
			return (byte) (c - 0x30);
		} else if (c >= 0x41 && c <= 0x46) {
			return (byte) (c - 0x37);
		} else if (c >= 0x61 && c <= 0x66) {
			return (byte) (c - 0x57);
		} else {
			throw new NumberFormatException("unknown char: " + c);
		}
	}

	/**
	 * byte转换为char,注意一个byte可以转换为两个char
	 * 
	 * @param b
	 *            待转换的byte
	 * @param upper
	 *            转换的结果字符采用大写还是小写
	 * @return 转换的结果
	 * @throws NumberFormatException
	 */
	public static char byte2char(byte b, boolean upper)
			throws NumberFormatException {
		if (b >= 0 && b <= 9) {
			return (char) (b + 0x30);
		} else if (b >= 10 && b <= 15) {
			if (upper) {
				return (char) (b + 0x37);
			} else {
				return (char) (b + 0x57);
			}
		}
		throw new NumberFormatException("unknown byte: " + b);
	}

	/**
	 * 将 data转换为16进制字符串,保持16个字符,不足部分左边补0
	 * 
	 * @param data
	 *            待转换的数据
	 * @param upper
	 *            采用大写还是小写字符
	 * @return 转换结果
	 */

	public static String toHexString(byte data, boolean upper) {
		char result[] = new char[2];
		byte d = (byte) ((data >> 4) & 0xf);
		result[0] = byte2char(d, upper);
		d = (byte) (data & 0xf);
		result[1] = byte2char(d, upper);
		return new String(result);
	}
}
