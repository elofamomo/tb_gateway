package com.ylkj.fbs.service.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 终端上传的动态数据
 *
 * @date 2022年11月27日-下午4:30:19
 */
public class ProReceivingEmqEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * 受控MAC 8字节 如标识为0x01，都填0
	 */
	private String dkMac;

	/**
	 * 主控MAC 8字节 需填入主控端MAC地址
	 */
	private String zkMac;

	/**
	 * 帧的总长度 1字节 0x11
	 */
	private String dataLeng;

	/**
	 * 标识（1字节 0x00 单播 0x01 组播）
	 */
	private String identType;

	/**
	 * 受控地址数量 1字节
	 */
	private int nwkCount;

	/**
	 * 短地址列表
	 */
	public List<String> nwkList;

	/**
	 * NWK/组ID 2字节0x****（单播填充NWK，组播填充组ID）
	 */
	private String nekId;

	/**
	 * 包长度 1字节 0x03
	 */
	private String packLeng;

	/**
	 * 主命令 1字节 0x02
	 */
	private String mainMl;

	/**
	 * 子命令 1字节 0x01
	 */
	private String zMl;

	/**
	 * 数据包 1字节 0x3C 关 0x5A 开
	 */
	private String data;

	/**
	 * 校验数据
	 */
	private String checkDate;

	/**
	 * 
	 */
	private String receBytesBtr;

	/**
	 * 项目名称
	 */
	private String proName;
	/**
	 * 项目Id
	 */
	private String proId;

	/**
	 * boolean
	 */
	private String terNum;

	/**
	 * boolean
	 */
	private boolean checkIsTrue;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * PAN　ID
	 */
	private String panId;

	/**
	 * 灯控器型号
	 */
	private String terModel;

	/**
	 * 显示状态值（0：正常；1：异常，灰色）
	 */
	private Integer showStatus;

	/**
	 * 时区ID
	 */
	private Integer timezoneId;

	/**
	 * x坐标
	 */
	private String xCoordinate;

	/**
	 * y坐标
	 */
	private String yCoordinate;

	/**
	 * 坐标来源
	 */
	private int userMap;

	/**
	 * 服务来源
	 */
	private String serverId;
	/**
	 * 开关灯
	 */
	private int status;

	/**
	 * 发送命令失败提示类型
	 */
	private int arrIndex;

	/**
	 * 策略名称
	 */
	private String tacticName;

	/**
	 * 灯控器状态
	 */
	private int faultCondition;

	/**
	 * 数据帧crc16校验值
	 */
	private String checkValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getNwkList() {
		return nwkList;
	}

	public void setNwkList(List<String> nwkList) {
		this.nwkList = nwkList;
	}

	public ProReceivingEmqEntity() {
		super();
	}

	public void setDkMac(String dkMac) {
		this.dkMac = dkMac;
	}

	public String getDkMac() {
		return dkMac;
	}

	public void setZkMac(String zkMac) {
		this.zkMac = zkMac;
	}

	public String getZkMac() {
		return zkMac;
	}

	public void setDataLeng(String dataLeng) {
		this.dataLeng = dataLeng;
	}

	public String getDataLeng() {
		return dataLeng;
	}

	public void setIdentType(String identType) {
		this.identType = identType;
	}

	public String getIdentType() {
		return identType;
	}

	public void setNekId(String nekId) {
		this.nekId = nekId;
	}

	public String getNekId() {
		return nekId;
	}

	public void setPackLeng(String packLeng) {
		this.packLeng = packLeng;
	}

	public String getPackLeng() {
		return packLeng;
	}

	public void setMainMl(String mainMl) {
		this.mainMl = mainMl;
	}

	public String getMainMl() {
		return mainMl;
	}

	public void setZMl(String zMl) {
		this.zMl = zMl;
	}

	public String getZMl() {
		return zMl;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setReceBytesBtr(String receBytesBtr) {
		this.receBytesBtr = receBytesBtr;
	}

	public String getReceBytesBtr() {
		return receBytesBtr;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public void setCheckIsTrue(boolean checkIsTrue) {
		this.checkIsTrue = checkIsTrue;
	}

	public boolean getCheckIsTrue() {
		return checkIsTrue;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getNwkCount() {
		return nwkCount;
	}

	public void setNwkCount(int nwkCount) {
		this.nwkCount = nwkCount;
	}

	public String getzMl() {
		return zMl;
	}

	public void setzMl(String zMl) {
		this.zMl = zMl;
	}

	public String getTerNum() {
		return terNum;
	}

	public void setTerNum(String terNum) {
		this.terNum = terNum;
	}

	public String getPanId() {
		return panId;
	}

	public void setPanId(String panId) {
		this.panId = panId;
	}

	public String getTerModel() {
		return terModel;
	}

	public void setTerModel(String terModel) {
		this.terModel = terModel;
	}

	public Integer getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Integer showStatus) {
		this.showStatus = showStatus;
	}

	public Integer getTimezoneId() {
		return timezoneId;
	}

	public void setTimezoneId(Integer timezoneId) {
		this.timezoneId = timezoneId;
	}

	public String getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(String xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public String getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(String yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public int getUserMap() {
		return userMap;
	}

	public void setUserMap(int userMap) {
		this.userMap = userMap;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getArrIndex() {
		return arrIndex;
	}

	public void setArrIndex(int arrIndex) {
		this.arrIndex = arrIndex;
	}

	public String getTacticName() {
		return tacticName;
	}

	public void setTacticName(String tacticName) {
		this.tacticName = tacticName;
	}

	public int getFaultCondition() {
		return faultCondition;
	}

	public void setFaultCondition(int faultCondition) {
		this.faultCondition = faultCondition;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

}
