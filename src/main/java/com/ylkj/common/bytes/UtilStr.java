package com.ylkj.common.bytes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilStr {

	/***
	 * 判断是否null
	 * 
	 * @param obj
	 * @return true=null
	 */
	public static boolean isNotObjnull(Object obj) {
		try {
			if (obj == null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			if (obj.equals("")) {
				return true;
			} else {
				return false;
			}
		}
	}

	/***
	 * 转为字符串
	 * 
	 * @param obj
	 * @return true=null
	 */
	public static String customToString(Object str) {
		if (str == null || str.equals("")) {
			return "";
		}
		return str.toString();
	}

	/**
	 * 将list拼接成字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String listToString(List<String> list) {
		StringBuffer sb = new StringBuffer();
		for (String id : list) {
			sb.append("'");
			sb.append(id);
			sb.append("'");
			sb.append(",");
		}
		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * 将list拼接成字符串
	 * 
	 * @param userIdList
	 * @return
	 */
	public static String userIdStringBuff(List<Long> userIdList) {
		if (null != userIdList && userIdList.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (Long id : userIdList) {
				sb.append(id);
				sb.append(",");
			}
			return sb.substring(0, sb.length() - 1);
		}
		return null;
	}

	/**
	 * 将字符串转成list
	 * 
	 * @param str
	 *            如：["1234567","8910111213"]
	 * @return
	 */
	public static List<String> StringToList(String str) {
		List<String> strList = new ArrayList<String>();
		Pattern p = Pattern.compile("\"(.*?)\"");
		Matcher m = p.matcher(str);
		while (m.find()) {
			strList.add(m.group().trim().replace("\"", ""));
		}
		return strList;
	}
}
