package com.ylkj.fbs.mq.queue;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylkj.common.bytes.BytesUtils;
import com.ylkj.common.util.DateUtils;
import com.ylkj.fbs.service.EmqServerPushCallbackService;

/***
 * 队列缓冲区，所有指令进来都会先到队列，等待消费处理，当然这只是我们设计的时候，考虑的高性能问题，也可以不需要，直接拿到指令进行消费处理业务
 * 
 * In the queue buffer, all instructions will arrive in the queue first and wait
 * for consumption processing. Of course, this is just a high-performance
 * problem that we considered when designing. You can also get instructions
 * directly for consumption processing without needing them
 *
 * @author ljw
 * @date 2022年11月27日-下午4:30:19
 ***/
@Service
public class LinkedBlockingQueueServer {
	Logger log = LoggerFactory.getLogger(getClass());
	// 设置队列上限10000条
	// Set the maximum number of queues to 10000
	private static int MQ_MessListMax = 10000;

	private LinkedBlockingQueue<String> messList = new LinkedBlockingQueue<String>(
			MQ_MessListMax);
	@Autowired
	EmqServerPushCallbackService callBackService;

	public LinkedBlockingQueueServer() {
		threadHandle();
	}

	public void threadHandle() {
		try {
			log.info("|启动循环线程 |Start thread     "
					+ DateUtils.format(new Date()));
			Thread threadMess = new Thread(new Runnable() {
				public void run() {
					while (true) {

						String mess;
						try {
							// log.info("队列...      " + MessListMax + "||"
							// + messList.size());
							mess = messList.take();
							// log.info("队列接收消息...      " +mess);
							handlingBusiness(mess);
						} catch (InterruptedException e) {
							log.error("Information retrieval failed");
							e.printStackTrace();
						}

					}
				}
			});
			threadMess.start();
		} catch (Exception e) {
			log.error("Thread start failed");
			// TODO: handle exception

		}
	}

	/***
	 * mq消息添加到队列中
	 * 
	 * Add mq messages to the queue
	 * 
	 * @param mess
	 * @throws InterruptedException
	 */
	public void puStrList(String s, MqttMessage mqttmessage) {
		String mess = BytesUtils.toHexString(mqttmessage.getPayload(), true);
		// log.info("数据进入..." + messList.size());
		if (checkData(s, mess, mqttmessage)) {
			try {
				messList.put(mess);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Failed to add mq message to the queue");
			}

		}

	}

	/***
	 * 消息验证，防止不能解析的消息增加程序负载
	 * 
	 * Message validation to prevent the program load from being increased by
	 * messages that cannot be parsed
	 * 
	 * @param s
	 * @param mess
	 * @param mqttmessage
	 * @return
	 */
	public boolean checkData(String s, String mess, MqttMessage mqttmessage) {
		try {
			if (mess == null || mess.equals("") || !mess.startsWith("CCCC")) {
				log.info("------------------------------------");
				log.info(":theme:" + s);
				log.info(":Qos:" + mqttmessage.getQos());
				log.info(":Receive subject message content:" + mess);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/***
	 * 处理业务
	 * 
	 * Processing business
	 * 
	 * @param mess
	 */
	public void handlingBusiness(String mess) {
		try {
			callBackService.handle(mess);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Handling business exceptions");
			e.printStackTrace();
		}
	}
}
