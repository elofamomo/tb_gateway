package com.ylkj.fbs.mq;

import java.util.concurrent.Executors;

import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.TimerPingSender;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylkj.config.ConfigEmq;
import com.ylkj.fbs.mq.queue.LinkedBlockingQueueServer;

/***
 * mq初始化并启动一个服务/Mq initializes and starts a service
 * 
 * @author Administrator
 * @date 2022年11月27日-下午4:30:19
 ***/
@Service("emqServerListenner")
public class EmqServerListenner {
	Logger logger = LoggerFactory.getLogger(getClass());
	// protected static String topics = "china/#";
	public static final int QoS_int = Integer.parseInt(System.getProperty(
			"QoS", "1"));
	protected static boolean isRun = true;
	protected static MemoryPersistence persistence = new MemoryPersistence();
	protected MqttAsyncClient client;
	@Autowired
	ConfigEmq configEmq;

	protected static Boolean emqStartOn = false;
	protected static String emqTcpUrl = "";
	protected static String emqClientId = "";
	protected static String emqUsername = "";
	protected static String emqPassword = "";
	protected static String empTopic = "";
	protected static Boolean empStartPassword = false;
	@Autowired
	private LinkedBlockingQueueServer queueServer;

	void init() {
		try {
			emqStartOn = configEmq.receiveStartOn;
			emqTcpUrl = configEmq.receiveUrl;
			emqClientId = configEmq.receiveClientId;
			emqPassword = configEmq.receivePassword;
			emqUsername = configEmq.receiveUsername;
			empTopic = configEmq.receiveEmpTopic;
			empStartPassword = configEmq.receiveStartPassword;
			logger.info("emqStartOn:" + emqStartOn);
			logger.info("emqTcpUrl:" + emqTcpUrl);
			logger.info("emqClientId:" + emqClientId);
			logger.info("emqUsername:" + emqUsername);
			logger.info("emqPassword:" + emqPassword);
			logger.info("empTopic:" + empTopic);
			logger.info("empStartPassword:" + empStartPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() throws InterruptedException {
		try {

			if (isRun) {
				init();
				if (emqStartOn) {
					logger.info("---------------系统启动-------开启EMQ服务器，等待接受主题消息:");
					logger.info("---------------Emq System startup-------Start the EMQ server and wait to receive the topic message:");
					EmqttServerAll();
					isRun = false;
				} else {
					logger.info("---------------系统启动-------不开启EMQ服务器emqStartOn="
							+ emqStartOn);
					logger.info("---------------Emq System startup-------Do not turn on EMQ server emqStartOn="
							+ emqStartOn);
				}
			}

		} catch (Exception e) {
			isRun = false;
		}

	}

	public void EmqttServerAll() {
		try {
			client = new MqttAsyncClient(emqTcpUrl, emqClientId, persistence,
					new TimerPingSender(),
					Executors.newScheduledThreadPool(1000));
			MqttConnectOptions options = new MqttConnectOptions();
			// 开始新会话/Start a new session
			options.setCleanSession(true);
			// 自动重连/Automatic reconnection
			options.setAutomaticReconnect(true);
			// 设置超时时间/Set timeout
			options.setConnectionTimeout(30);
			// 设置会话心跳时间/Set session heartbeat time
			options.setKeepAliveInterval(20);
			if (empStartPassword) {
				options.setUserName(emqUsername);
				options.setPassword(emqPassword.toCharArray());
			}
			client.setCallback(new EmqServerPushCallback(client, queueServer));
			// client.setCallback(this);
			DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
			disconnectedBufferOptions.setBufferEnabled(true);
			// 缓存的消息数根据业务数据量设置
			// The number of cached messages is set according to the amount of
			// business data
			disconnectedBufferOptions.setBufferSize(200000);
			// 当缓存满了之后删掉最旧的数据/Delete the oldest data when the cache is full
			disconnectedBufferOptions.setDeleteOldestMessages(false);
			// 不持久化缓存/Do not persist cache
			disconnectedBufferOptions.setPersistBuffer(false);
			client.setBufferOpts(disconnectedBufferOptions);
			// 等待连接成功//Wait for the connection to succeed
			client.connect(options).waitForCompletion();

			client.subscribe(empTopic, QoS_int);
		} catch (MqttException e) {
			logger.info(e.getMessage());
			// System.exit(1);
		}
	}
}
