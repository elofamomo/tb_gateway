ylkj-fbs-light-api

给客户提供对接的api-例子/Provide customers with api examples for docking
帮助客户更快的对接我们终端设备-专用/Help customers connect with our terminal equipment faster - special
2022-11-28




拿到当前代码之后的操作：
1、先修改配置文件application-dev.yml，里面的emq配置
2、启动测试，是否正常，项目是否正常启动
3、如果mq配置没问题，项目是可以正常启动的，注意和灯一起的订阅主题
4、系统中会自动接收设备上报的信息，保持基本的解析，操作
5、系统中有开关灯的指令例子，详细查看PutDataController，tg_open|tg_close方法
访问地址默认是：(需要手动修改macId)
	http://192.168.3.5:9988/light_api/api/tg_close
	http://192.168.3.5:9988/light_api/api/tg_open



Operation after getting the current code:
1. Modify the emq configuration in the configuration file application-dev.yml first
2. Whether the startup test is normal and whether the project is started normally
3. If the mq configuration is OK, the project can be started normally. Pay attention to the subscription topic with the light
4. The system will automatically receive the information reported by the equipment, maintain basic analysis and operation
5. There is an example of the command to turn on and off the lights in the system. See PutDataController, tg for details_ open|tg_ Close method
The default access address is:(You need to manually modify the macId)
	http://192.168.3.7:9988/light_api/api/tg_close
	http://192.168.3.7:9988/light_api/api/tg_open
2022-11-30


注意事项：matters needing attention:
	1、下发指令的方法com.ylkj.fbs.controller.PutDataController类，tg_open|tg_close方法
	   The com.ylkj.fbs.controller.PutDataController class, tg_ open|tg_ Close method
	2、接收mq消息的方法com.ylkj.fbs.mq.EmqServerPushCallback.messageArrived()方法
	   Method of receiving mq message com. ylkj. fbs. mq. EmqServerPushCallback. messageArrived() method
业务流程：	Business process:
	1、接收mq消息的流程：通过emq的服务订阅指定的主题，接收消息，成功后，把消息放在队列中，在通过队列消费，到指定的方法中进行业务消费处理，根据方法追踪，进行详细的查看。
	  The process of receiving mq messages: subscribe to the specified topic through the service of the emq, receive the message, 
	  and put the message in the queue after success. The message will be consumed through the queue, 
	  and then processed in the specified method for business consumption. Detailed viewing will be performed according to the method tracking.
	  


















