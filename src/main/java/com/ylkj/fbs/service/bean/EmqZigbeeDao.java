package com.ylkj.fbs.service.bean;

import java.util.List;

public class EmqZigbeeDao {

	/***
	 * 帧长度 1字节 0x11 该帧的总长度
	 */
	public byte dataLeng;

	/****
	 * 标识 1字节 0x00 单播 0x01 组播
	 */
	public byte identType;

	/**
	 * 受控地址数量 1字节
	 */
	public byte nwkCount;

	/***
	 * NWK/组ID 2字节 0x****（单播填充NWK，组播填充组ID）
	 */
	public byte[] nekId;

	/***
	 * 主控MAC 4字节 需填入主控端MAC地址
	 */
	public byte[] zkMac;

	/****
	 * 受控MAC 4字节 如标识为0x01，都填0
	 */
	public byte[] dkMac;

	/***
	 * 包长度 1字节 0x03
	 */
	public byte packLeng;

	/***
	 * 主命令 1字节 0x01
	 */
	public byte mainMl;

	/***
	 * 子命令 1字节 0x01
	 */
	public byte zMl;

	/***
	 * 数据包 2字节0x0000~0xFFFF
	 */
	public byte[] data;

	/**
	 * nwkList
	 */
	public List<byte[]> nwkList;

	public List<byte[]> getNwkList() {
		return nwkList;
	}

	public void setNwkList(List<byte[]> nwkList) {
		this.nwkList = nwkList;
	}

	public byte getNwkCount() {
		return nwkCount;
	}

	public void setNwkCount(byte nwkCount) {
		this.nwkCount = nwkCount;
	}

	public byte getDataLeng() {
		return dataLeng;
	}

	public void setDataLeng(byte dataLeng) {
		this.dataLeng = dataLeng;
	}

	public byte getIdentType() {
		return identType;
	}

	public void setIdentType(byte identType) {
		this.identType = identType;
	}

	public byte[] getNekId() {
		return nekId;
	}

	public void setNekId(byte[] nekId) {
		this.nekId = nekId;
	}

	public byte[] getZkMac() {
		return zkMac;
	}

	public void setZkMac(byte[] zkMac) {
		this.zkMac = zkMac;
	}

	public byte[] getDkMac() {
		return dkMac;
	}

	public void setDkMac(byte[] dkMac) {
		this.dkMac = dkMac;
	}

	public byte getPackLeng() {
		return packLeng;
	}

	public void setPackLeng(byte packLeng) {
		this.packLeng = packLeng;
	}

	public byte getMainMl() {
		return mainMl;
	}

	public void setMainMl(byte mainMl) {
		this.mainMl = mainMl;
	}

	public byte getzMl() {
		return zMl;
	}

	public void setzMl(byte zMl) {
		this.zMl = zMl;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
