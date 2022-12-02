package com.ylkj.config.init;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ylkj.common.util.IPUtils;
import com.ylkj.common.util.SysIpBean;
import com.ylkj.config.ConfigEmq;
import com.ylkj.fbs.mq.EmqServerListenner;
import com.ylkj.fbs.mq.send.SendEmqClientService;

/***
 * 系统初始化结束后执行的操作/Operation after system initialization
 * 
 * @author ljw
 *
 */
@Component
public class InitApplicationRunner implements ApplicationRunner {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	ConfigEmq config;
	@Autowired
	IPUtils ips;
	@Autowired(required = true)
	private EmqServerListenner emqService;

	@Autowired
	SendEmqClientService sendEmqClientService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub

		log();
		log.info("________________________________--------init emq service-----------________________________________________");
		try {
			// 接收server启动/Receive server startup
			emqService.run();
			// 发送client启动/Sending client startup
			sendEmqClientService.run();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.info("________________________________--------init emq service error-----------________________________________________");
			e.printStackTrace();
		}

	}

	public void log() {
		log.info("*********************************************");
		log.info("*********************************************");
		log.info("**********智慧路灯通讯系统**对接***************");
		log.info("**********Intelligent street lamp communication system,connection");
		log.info("**********http://127.0.0.1:" + config.port + ""
				+ config.pathName + "**********");
		List<SysIpBean> ipList = ips.getHostIP();
		for (Iterator iterator = ipList.iterator(); iterator.hasNext();) {
			SysIpBean sysIpBean = (SysIpBean) iterator.next();
			log.info(JSONObject.toJSONString(sysIpBean));
		}
		log.info("**********************************************");

	}

}
