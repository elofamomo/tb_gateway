package com.ylkj.common.util;

import org.springframework.util.StopWatch;

/***
 * StopWatchLog.java
 *
 * @author Administrator
 * @date 2022年11月4日-下午10:10:15
 ***/
public class StopWatchLog {

	/***
	 * 返回打印输出，并关闭StopWatch.stop
	 * 
	 * @param sw
	 * @return
	 */
	public static String logStop(StopWatch sw) {
		// StopWatch sw = new StopWatch();
		// sw.start("getInfobyMacId...");
		sw.stop();
		StringBuffer buffer = new StringBuffer();
		buffer.append(sw.prettyPrint()).append("|");
		buffer.append(sw.getTotalTimeSeconds()).append("秒|");
		buffer.append(sw.getTotalTimeMillis()).append("毫秒|");
		return buffer.toString();
	}
}
