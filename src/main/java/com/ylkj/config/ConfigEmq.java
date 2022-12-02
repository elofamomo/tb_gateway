package com.ylkj.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/***
 * 配置中心，获取参数-关于EMQ的配置
 * 
 * @author ljw Administrator
 *
 */
@Configuration
@Component
public class ConfigEmq {
	Logger log = LoggerFactory.getLogger(getClass());
	@Value("${server.servlet.context-path}")
	public String pathName;

	@Value("${server.port}")
	public int port;

	@Autowired
	public ConfigEmq configEmq;

	@Value("${emq.receive.url}")
	public String receiveUrl;

	@Value("${emq.receive.clientId}")
	public String receiveClientId;

	@Value("${emq.receive.startPassword}")
	public boolean receiveStartPassword;

	@Value("${emq.receive.username}")
	public String receiveUsername;

	@Value("${emq.receive.password}")
	public String receivePassword;

	@Value("${emq.receive.startOn}")
	public boolean receiveStartOn;

	@Value("${emq.receive.empTopic}")
	public String receiveEmpTopic;

	/***
	 * 
	 */

	@Value("${emq.send.clientId}")
	public String sendClientId;

	@Value("${emq.send.startOn}")
	public boolean sendStartOn;

	@Value("${emq.send.empTopic}")
	public String sendEmpTopic;

	public void log() {
		log.info("*******init config EMQ ********");
		log.info("receiveUrl=" + receiveUrl);
		log.info("receiveClientId=" + receiveClientId);
		log.info("receiveStartPassword=" + receiveStartPassword);
		log.info("receiveUsername=" + receiveUsername);
		log.info("receivePassword=" + receivePassword);
		log.info("receiveStartOn=" + receiveStartOn);

		log.info("sendClientId=" + sendClientId);
		log.info("sendStartOn=" + sendStartOn);
		log.info("sendEmpTopic=" + sendEmpTopic);
		log.info("********************");
	}

}
