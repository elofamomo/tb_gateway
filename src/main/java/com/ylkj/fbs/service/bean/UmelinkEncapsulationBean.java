package com.ylkj.fbs.service.bean;

import java.util.List;

/***
 * 封装参数对象
 * 
 * @author ljw
 *
 */
public class UmelinkEncapsulationBean {
	//
	String master_cmd;
	String slave_cmd;
	String identType;
	String mac;
	List<String> NWK;
	String num_of_NWK;
	String val;

	/**
	 * 获取master_cmd
	 * 
	 * @return mac
	 */
	public String getMaster_cmd() {
		return master_cmd;
	}

	/**
	 * 设置master_cmd
	 * 
	 * @param mac
	 */
	public void setMaster_cmd(String master_cmd) {
		this.master_cmd = master_cmd;
	}

	/**
	 * 获取slave_cmd
	 * 
	 * @return slave_cmd
	 */
	public String getSlave_cmd() {
		return slave_cmd;
	}

	/**
	 * 设置slave_cmd
	 * 
	 * @param slave_cmd
	 */
	public void setSlave_cmd(String slave_cmd) {
		this.slave_cmd = slave_cmd;
	}

	public String getIdentType() {
		return identType;
	}

	public void setIdentType(String identType) {
		this.identType = identType;
	}

	/**
	 * 获取mac
	 * 
	 * @return mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * 设置mac
	 * 
	 * @param mac
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * 获取nWK
	 * 
	 * @return nWK
	 */
	public List<String> getNWK() {
		return NWK;
	}

	/**
	 * 设置nWK
	 * 
	 * @param nWK
	 */
	public void setNWK(List<String> nWK) {
		NWK = nWK;
	}

	/**
	 * 获取num_of_NWK
	 * 
	 * @return num_of_NWK
	 */
	public String getNum_of_NWK() {
		return num_of_NWK;
	}

	/**
	 * 设置num_of_NWK
	 * 
	 * @param num_of_NWK
	 */
	public void setNum_of_NWK(String num_of_NWK) {
		this.num_of_NWK = num_of_NWK;
	}

	/**
	 * 获取val
	 * 
	 * @return val
	 */
	public String getVal() {
		return val;
	}

	/**
	 * 设置val
	 * 
	 * @param val
	 */
	public void setVal(String val) {
		this.val = val;
	}

}
