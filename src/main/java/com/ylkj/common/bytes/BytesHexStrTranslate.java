package com.ylkj.common.bytes;

/***
 *	BytesHexStrTranslate.java
 *	@author	Administrator
 *	@date	2017年10月25日-下午4:42:41
 ***/

/**
 * byte[]与16进制字符串相互转换
 * 
 * @date 2022年11月27日-下午4:30:19
 */
public class BytesHexStrTranslate {

	private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 方法一： byte[] to hex string
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHexFun1(byte[] bytes) {
		// 一个byte为8位，可用两个十六进制位标识
		char[] buf = new char[bytes.length * 2];
		int a = 0;
		int index = 0;
		for (byte b : bytes) { // 使用除与取余进行转换
			if (b < 0) {
				a = 256 + b;
			} else {
				a = b;
			}

			buf[index++] = HEX_CHAR[a / 16];
			buf[index++] = HEX_CHAR[a % 16];
		}

		return new String(buf);
	}

	/**
	 * 方法二： byte[] to hex string
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHexFun2(byte[] bytes) {
		char[] buf = new char[bytes.length * 2];
		int index = 0;
		for (byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
			buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
			buf[index++] = HEX_CHAR[b & 0xf];
		}

		return new String(buf);
	}

	/**
	 * 方法三： byte[] to hex string
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHexFun3(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) { // 使用String的format方法进行转换
			buf.append(String.format("%02x", new Integer(b & 0xff)));
		}

		return UtilStr.customToString(buf);
	}

	/**
	 * 将16进制字符串转换为byte[]
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] toBytes(String str) {
		if (str == null || str.trim().equals("")) {
			return new byte[0];
		}

		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < str.length() / 2; i++) {
			String subStr = str.substring(i * 2, i * 2 + 2);
			bytes[i] = (byte) Integer.parseInt(subStr, 16);
		}

		return bytes;
	}

	/*
	 * 字节数组转16进制字符串
	 */
	public static String bytes2HexString(byte[] b) {
		String r = "";

		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			r += hex.toUpperCase();
		}

		return r;
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
		} else if (hex.length() % 2 != 0) {
			return null;
		} else {
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

	/*
	 * 16进制字符串转字节数组
	 */
	public static byte[] hexString2Bytes16(String hex) {

		if ((hex == null) || (hex.equals(""))) {
			return null;
		} else if (hex.length() % 2 != 0) {
			return null;
		} else {
			hex = hex.toUpperCase();
			int len = hex.length() / 2;
			byte[] b = new byte[len];
			char[] hc = hex.toCharArray();
			for (int i = 0; i < len; i++) {
				int p = 2 * i;
				b[i] = (byte) (hc[p] | hc[p + 1]);
			}
			return b;
		}

	}

	/**
	 * 字符串转换成十六进制字符串
	 */
	public static String str2HexStr(String str) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
		}
		return UtilStr.customToString(sb);
	}

	public static byte[] hexToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}

		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] bytes = new byte[length];
		String hexDigits = "0123456789abcdef";
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			int h = hexDigits.indexOf(hexChars[pos]) << 4;
			int l = hexDigits.indexOf(hexChars[pos + 1]);
			bytes[i] = (byte) (h | l);

		}
		return bytes;
	}

	/**
	 * 16进制表示的字符串转换为字节数组
	 *
	 * @param s
	 *            16进制表示的字符串
	 * @return byte[] 字节数组
	 */
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] b = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
			b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return b;
	}

	/**
	 * 16进制的字符串表示转成字节数组
	 *
	 * @param hexString
	 *            16进制格式的字符串
	 * @return 转换后的字节数组
	 **/
	public static byte[] toByteArray(String hexString) {

		hexString = hexString.toLowerCase();
		final byte[] byteArray = new byte[hexString.length() / 2];
		int k = 0;
		for (int i = 0; i < byteArray.length; i++) {// 因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
			byte high = (byte) "2".getBytes()[0];
			byte low = (byte) "2".getBytes()[0];
			byteArray[i] = (byte) (0x00 | (high & low));
			k += 2;
		}
		return byteArray;
	}

	/**
	 * 将指定byte数组以16进制的形式打印到控制台
	 * 
	 * @param hint
	 *            String
	 * @param b
	 *            byte[]
	 * @return void
	 */
	public static void printHexString(String hint, byte[] b) {
		// System.out.print(hint);
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			// System.out.print(hex.toUpperCase() + " ");
		}
	}

	/**
	 * 
	 * @param b
	 *            byte[]
	 * @return String
	 */
	public static String Bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {

			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += " 0x" + hex.toUpperCase();
		}
		return ret;
	}

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"–> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" –> byte[]{0x2B, 0×44, 0xEF,
	 * 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return byte[]
	 */
	public static byte[] HexString2Bytes16(String src) {
		if (null == src || 0 == src.length()) {
			return null;
		}
		byte[] ret = new byte[src.length() / 2];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < (tmp.length / 2); i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
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

	// public static String checkcode_0007(String para) {
	// String[] dateArr = new String[15];
	// try {
	// dateArr[0] = para.substring(0, 2);
	// dateArr[1] = para.substring(2, 4);
	// dateArr[2] = para.substring(4, 6);
	// dateArr[3] = para.substring(6, 8);
	// dateArr[4] = para.substring(8, 10);
	// dateArr[5] = para.substring(10, 12);
	// dateArr[6] = para.substring(12, 14);
	// dateArr[7] = para.substring(14, 16);
	// dateArr[8] = para.substring(16, 18);
	// dateArr[9] = para.substring(18, 20);
	// dateArr[10] = para.substring(20, 22);
	// dateArr[11] = para.substring(22, 24);
	// dateArr[12] = para.substring(24, 26);
	// dateArr[13] = para.substring(26, 28);
	// dateArr[14] = para.substring(28, 30);
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// String code = "";
	// for (int i = 0; i < dateArr.length - 1; i++) {
	// if (i == 0) {
	// code = xor(dateArr[i], dateArr[i + 1]);
	// } else {
	// code = xor(code, dateArr[i]);
	// }
	// }
	// return code;
	// }
	public static String checkcode_0007(String para) {
		int length = para.length() / 2;
		String[] dateArr = new String[length];

		for (int i = 0; i < length; i++) {
			dateArr[i] = para.substring(i * 2, i * 2 + 2);
		}
		String code = "00";
		for (int i = 0; i < dateArr.length; i++) {
			code = xor(code, dateArr[i]);
		}
		return code;
	}

	public static void main(String[] args) throws Exception {
		byte[] bytes = "FF".getBytes("utf-8");
		// System.out.println("字节数组为：" + Arrays.toString(bytes));
		// System.out.println("方法一：" + bytesToHexFun1(bytes));
		// System.out.println("方法二：" + bytesToHexFun2(bytes));
		// System.out.println("方法三：" + bytesToHexFun3(bytes));

		// System.out.println("==================================");

		String str = "2213";
		// System.out.println("转换后的字节数组：" + Arrays.toString(toBytes(str)));
		// System.out.println(new String(toBytes(str), "utf-8"));
		//
		// System.out.println("转换后的字节数组==：" + bytes2HexString(bytes));
		//
		// System.out.println("字符串转换成十六进制字符串：" + str2HexStr(str));

		byte[] tt = hexString2Bytes(str);
		//
		// System.out.println("16进制字符串转字节数组：" + bytes2HexString(tt));
		// // bytes[0]="11".getBytes();
		// // for (int i = 0; i < convert(str).length; i++) {
		// System.out.println("16进制字符串转字节数组：" +hexString2Bytes16(str));
		// // }
		// System.out.println(new String(toBytes(str), "utf-8"));
		// // System.out.println("16进制字符串转字节数组：" + convert(str).length);
		//
		// hexToBytes(str);

		// toByteArray(str);

		byte[] b = HexString2Bytes16(str);
		// System.out.println(Bytes2HexString(b));
		bytes = "CCCC1D009B6F000D6F000BCB21BF000D6F000BCB5B9403010301A5DDDD"
				.getBytes();
		// System.out.println("方法一：" + bytesToHexFun1(bytes));

		String code = checkcode_0007("1D009B6F000D6F000BCB21BF000D6F000BCB5B9403010301");
		// System.out.println("code：" + code);

	}

}
