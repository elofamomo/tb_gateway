package com.ylkj.fbs.mq;

/***
 *	PushCallback.java
 *	@author	Administrator
 *	@date	2017年9月13日-下午4:57:13 
 ***/

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ylkj.fbs.mq.queue.LinkedBlockingQueueServer;

/**
 * 发布消息的回调类 mqtt发送类 必须实现MqttCallback的接口并实现对应的相关接口方法 ◦CallBack 类将实现
 * MqttCallBack。每个客户机标识都需要一个回调实例。在此示例中
 * ，构造函数传递客户机标识以另存为实例数据。在回调中，将它用来标识已经启动了该回调的哪个实例。 ◦必须在回调类中实现三个方法：
 * 
 * *The callback class mqtt sending class that publishes messages must implement
 * the interface of MqttCallback and the corresponding related interface
 * methods. CallBack class will implement
 * 
 * MqttCallBack。 A callback instance is required for each client identity. In
 * this example
 * 
 * , the constructor passes the client identity to save as instance data. In the
 * callback, it is used to identify which instance of the callback has been
 * started. · Three methods must be implemented in the callback class:
 * 
 * 
 * 接收已经预订的发布。 publications that have already been subscribed to. public void
 * messageArrived(MqttTopic topic, MqttMessage message)
 * 
 * 在断开连接时调用。Called when disconnected. public void connectionLost(Throwable
 * cause)
 * 
 * 接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用。 ◦由 MqttClient.connect 激活此回调。 Called when
 * the delivery token of a published QoS 1 or QoS 2 message is received. · This
 * callback is activated by MqttClient.connect. public void
 * deliveryComplete(MqttDeliveryToken token))
 * 
 */
public class EmqServerPushCallback implements MqttCallbackExtended {
	Logger log = LoggerFactory.getLogger(getClass());

	private LinkedBlockingQueueServer queueServer;

	// private EmqServerPushCallbackService callBackAervice;
	private MqttAsyncClient client;

	public EmqServerPushCallback() {
	}

	public EmqServerPushCallback(MqttAsyncClient client,
			LinkedBlockingQueueServer queueServer) {
		// this.callBackAervice = callBackAervice;
		this.client = client;
		this.queueServer = queueServer;
	}

	public void connectionLost(Throwable cause) {
		// logger.info("deliveryComplete---------连接断开，可以做重连");
		// logger.info("deliveryComplete---------Disconnected, can be reconnected");

	}

	public void deliveryComplete(MqttDeliveryToken token) {
		// logger.info("publish---------" + token.isComplete());
	}

	/***
	 * 接收emq的消息方法 Message method of receiving emq
	 */
	public void messageArrived(String s, MqttMessage mqttmessage) {

		// log.info("------------------------------------");
		// log.info(":theme:" + s);
		// log.info(":Qos:" + mqttmessage.getQos());
		// log.info(":Receive subject message content:" + s +
		// BytesUtils.toHexString(mqttmessage.getPayload(), true));
		// log.info("------------------------------------");
		// callBackAervice.handle(mqttmessage.getPayload());
		queueServer.puStrList(s, mqttmessage);

	}

	public void deliveryComplete(IMqttDeliveryToken imqttdeliverytoken) {

	}

	@Override
	public void connectComplete(boolean reconnect, String serverURI) {
		// 连接成功或者重连成功后将调用这个方法，通常用于订阅topic和重发连接断开期间的消息
		// This method will be called after successful connection or
		// reconnection. It is usually used to subscribe to topic and resend
		// messages during disconnection
		if (reconnect) {

			try {
				client.subscribe(EmqServerListenner.empTopic,
						EmqServerListenner.QoS_int);
			} catch (MqttException e) {
				e.printStackTrace();
			}

		}
	}

}
