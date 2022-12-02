package com.ylkj.fbs.mq.send;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylkj.common.api.EmqFrameDecoder;
import com.ylkj.config.ConfigEmq;

/***
 * mq初始化一个客户端服务 Mq initializes a client service
 *
 * @author Administrator
 * @date 2022年11月27日-下午4:30:19
 ***/
@Service("sendEmqClientService")
public class SendEmqClientService {

	Logger logger = LoggerFactory.getLogger(getClass());
	// private static String topics = "china/#";
	protected static final int QoS_int = Integer.parseInt(System.getProperty(
			"QoS", "1"));
	protected static boolean isRunClient = true;
	protected MqttConnectOptions options;
	protected static MemoryPersistence persistence = new MemoryPersistence();
	// protected MqttAsyncClient client;
	protected static Boolean emqStartOn = false;
	protected static String emqTcpUrl = "";
	protected static String emqClientId = "";
	protected static String emqUsername = "";
	protected static String emqPassword = "";
	protected static String empTopic = "";
	protected static boolean empStartPassword = false;
	private static URI serverURI;
	IMqttClient client = null;
	private static MqttClientFactoryPaho clientFactory;
	@Autowired
	ConfigEmq configEmq;

	@Autowired
	private EmqFrameDecoder emqFrameDecoder;

	void init() {
		try {
			emqStartOn = configEmq.sendStartOn;
			emqTcpUrl = configEmq.receiveUrl;
			emqClientId = configEmq.sendClientId;
			emqPassword = configEmq.receivePassword;
			emqUsername = configEmq.receiveUsername;
			empTopic = configEmq.sendEmpTopic;
			empStartPassword = configEmq.receiveStartPassword;
			logger.info("emqStartOn:" + emqStartOn);
			logger.info("emqTcpUrl:" + emqTcpUrl);
			logger.info("emqClientId:" + emqClientId);
			logger.info("emqUsername:" + emqUsername);
			logger.info("emqPassword:" + emqPassword);
			logger.info("empTopic:" + empTopic);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() throws InterruptedException {
		try {

			if (isRunClient) {
				init();
				if (emqStartOn) {
					logger.info("---------------系统启动-------开启EMQClient，等待接受主题消息:");
					logger.info("---------------Emq System startup-------Start the EMQ server and wait to receive the topic message:");

					EmqttServerAll();
					isRunClient = false;
				} else {
					logger.info("---------------系统启动-------不开启EMQClient emqStartOn="
							+ emqStartOn);
					logger.info("---------------Emq System startup-------Do not turn on EMQ server emqStartOn="
							+ emqStartOn);
				}
			}

		} catch (Exception e) {
			isRunClient = false;
		}

	}

	public void EmqttServerAll() {
		try {
			serverURI = new URI(emqTcpUrl);
			clientFactory = new MqttClientFactoryPaho();
			clientFactory.open();
			try {
				client = clientFactory.createMqttClient(serverURI, emqClientId);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			options = new MqttConnectOptions();
			if (empStartPassword) {
				options.setUserName(emqUsername);
				options.setPassword(emqPassword.toCharArray());
			}
			// 开始新会话/Start a new session
			options.setCleanSession(true);
			// 自动重连/Automatic reconnection
			options.setAutomaticReconnect(true);
			// 设置超时时间/Set timeout
			options.setConnectionTimeout(30);
			// 设置会话心跳时间 设置会话心跳时间 单位为秒
			// 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
			options.setKeepAliveInterval(20);
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
			// client.setBufferOpts(disconnectedBufferOptions);
			try {
				client.connect(options);

			} catch (MqttSecurityException e) {
				e.printStackTrace();
			} catch (MqttException e) {
				e.printStackTrace();
				logger.info(e.getMessage());

				// System.exit(1);
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

	public boolean sendessage(String topic, MqttMessage message) {
		try {
			if (getClient().isConnected()) {
				// topic="client/"+topic;

				// logger.info("send mq message topic:"+topic+" message:"+message);
				client.publish(topic, message);
				logger.info("send mq message ok");
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			logger.info("send mq message error:" + e.getMessage());
			return false;
		}
	}

	public IMqttClient getClient() throws MqttSecurityException, MqttException {

		if (client.isConnected()) {
			return client;
		} else {
			client.connect(options);
			return client;
		}

	}

}
