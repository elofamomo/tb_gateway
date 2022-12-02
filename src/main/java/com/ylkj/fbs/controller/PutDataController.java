package com.ylkj.fbs.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ylkj.common.util.CommonUtils;
import com.ylkj.common.util.R;
import com.ylkj.fbs.service.ClientSendMessService;
import com.ylkj.fbs.service.bean.UmelinkEncapsulationBean;

//http://192.168.3.5:9988/light_api/api/test
@RestController
@RequestMapping("/api")
public class PutDataController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	ClientSendMessService sendClientService;

	String macID = "00000000000296E4";

	/***
	 * 测试 http://192.168.3.5:9988/light_api/api/test
	 * 
	 * @return
	 */
	@RequestMapping("/test")
	public String test() {

		log.info("put-test ...");

		return "ok";
	}

	// 封装对象={"identType":"04",
	// "mac":"00000000000296E7",
	// "master_cmd":"02",
	// "nWK":["0000"],
	// "num_of_NWK":"1",
	// "slave_cmd":"02",
	// "val":"100"}
	/***
	 * 模拟开灯的指令/Simulate lamp on command
	 * 
	 * http://192.168.3.5:9988/light_api/api/tg_open
	 * 
	 * @return
	 */
	@RequestMapping("/tg_open")
	public R tg_open() {

		log.info("client-test ...open");
		UmelinkEncapsulationBean bean = new UmelinkEncapsulationBean();

		List<String> nwk = new ArrayList<String>();
		nwk.add("0000");
		bean.setMac(macID);
		// bean.setMac("00000000000296E7");
		bean.setMaster_cmd("02");
		bean.setNWK(nwk);
		bean.setNum_of_NWK("01");
		bean.setSlave_cmd("02");
		bean.setVal("64");// 16进制=10进制100
		bean.setIdentType("04");

		sendClientService.send_message(bean);

		return CommonUtils.msg("Ok");
	}

	/***
	 * 模拟关灯的指令/Simulate the command to turn off the light
	 * 
	 * http://192.168.3.5:9988/light_api/api/tg_close
	 * 
	 * @return
	 */
	@RequestMapping("/tg_close")
	public R tg_close() {

		log.info("client-test ...close");
		UmelinkEncapsulationBean bean = new UmelinkEncapsulationBean();

		List<String> nwk = new ArrayList<String>();
		nwk.add("0000");
		bean.setMac(macID);
		// bean.setMac("00000000000296E7");
		bean.setMaster_cmd("02");
		bean.setNWK(nwk);
		bean.setNum_of_NWK("01");
		bean.setSlave_cmd("02");
		bean.setVal("00");
		bean.setIdentType("04");

		sendClientService.send_message(bean);
		return CommonUtils.msg("Ok");
	}

}
