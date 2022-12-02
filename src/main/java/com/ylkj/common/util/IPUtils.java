package com.ylkj.common.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.ylkj.config.ConfigEmq;

/**
 * IP地址
 * 
 * @date 2022年11月27日-下午4:30:19
 */
@Service
public class IPUtils {
	private Logger logger = LoggerFactory.getLogger(IPUtils.class);
	@Autowired
	ConfigEmq config;

	/**
	 * 获取IP地址
	 * 
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded
	 * -For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = null;
		try {
			ip = request.getHeader("x-forwarded-for");
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} catch (Exception e) {
			logger.error("IPUtils ERROR ", e);
		}
		logger.info("ipAll:" + ip);
		// 使用代理，则获取第一个IP地址
		if (StringUtils.isEmpty(ip) && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		logger.info("ipReq:" + ip);
		return ip;
	}

	public List<SysIpBean> getHostIP() {

		List<SysIpBean> li = new ArrayList<SysIpBean>();
		Enumeration<NetworkInterface> allNetInterfaces = null;

		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
					.nextElement();
			// System.out.println(netInterface.getName());
			Enumeration<InetAddress> addresses = netInterface
					.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address) {
					SysIpBean bean = new SysIpBean();
					bean.setAddres("http://" + ip.getHostAddress() + ":"
							+ config.port + "" + config.pathName);
					bean.setName(netInterface.getName());
					if (ip.getHostAddress().startsWith("127.0.0")) {
						bean.setDes("本机-别人不能访问");
					} else {
						bean.setDes("同一个网络可以访问");
					}
					li.add(bean);
					// System.out.println("本机地址是：" + ip.getHostAddress());
				}
			}
		}
		return li;

	}
}
