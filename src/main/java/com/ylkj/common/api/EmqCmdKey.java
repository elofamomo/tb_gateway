package com.ylkj.common.api;

/***
 * EmqCmdKey.java
 *
 * @author Administrator
 * @date 2022年11月27日-下午4:30:19
 ***/
public class EmqCmdKey {

	public final static int SEND_CLOSE = 2;

	public final static byte CLIENT_OPEN = 0x5A;
	public final static byte CLIENT_CLOSE = 0x3C;

	/**
	 * 命令发送次数初始值
	 */
	public final static int SEND_COUNT_CONSTANT = 0;

	/***
	 * 开关命令-受控-开关灯
	 */
	public static final int SK_SEND_1 = 1;
	/***
	 * 调整亮度
	 */
	public static final int SK_SEND_2 = 2;

	/**
	 * 设定功率大小
	 */
	public static final int SK_SEND_3 = 3;

	/**
	 * 设置光衰补偿率
	 */
	public static final int SK_SEND_4 = 4;

	/**
	 * 设置中夜降亮的亮度
	 */
	public static final int SK_SEND_5 = 5;

	/**
	 * 光控器复位
	 */
	public static final int SK_SEND_6 = 6;

	/**
	 * SOS呼吸灯
	 */
	public static final int SK_SEND_7 = 7;

	/**
	 * 还原光控器出厂默认设定
	 */
	public static final int SK_SEND_8 = 8;

	/**
	 * 打开光源重新矫正
	 */
	public static final int SK_SEND_9 = 9;

	/**
	 * 设置欠压值
	 */
	public static final int SK_SEND_10 = 10;

	/**
	 * 设定过压值
	 */
	public static final int SK_SEND_11 = 11;

	/**
	 * 查询光控器状态
	 */
	public static final int SK_SEND_12 = 12;

	/**
	 * 光控器故障状态上报
	 */
	public static final int SK_SEND_13 = 13;

	/**
	 * 光空器实时状态上报
	 */
	public static final int SK_SEND_14 = 14;

	/**
	 * 设置光控器感光开关亮度值
	 */
	public static final int SK_SEND_15 = 15;

	/**
	 * 退出云端控制模式
	 */
	public static final int SK_SEND_16 = 16;

	/**
	 * 重置路灯灯泡使用天数
	 */
	public static final int SK_SEND_17 = 17;

	/**
	 * app SOS命令
	 */
	public static final int SK_SEND_18 = 18;

	/**
	 * 设置周期上报时间
	 */
	public static final int SK_SEND_19 = 19;

	/**
	 * 设置自感光下限值
	 */
	public static final int SK_SEND_20 = 20;

	/**
	 * 时钟同步
	 */
	public static final int SK_SEND_21 = 21;

	/**
	 * 下发策略或获取策略
	 */
	public static final int SK_SEND_22 = 22;

	/**
	 * 过流预警
	 */
	public static final int SK_SEND_23 = 23;
	/**
	 * 恒照度功能
	 */
	public static final int SK_SEND_24 = 24;

	/**
	 * app 调光
	 */
	public static final int SK_SEND_25 = 25;

	/**
	 * app 策略
	 */
	public static final int SK_SEND_32 = 32;

	/**
	 * app 自感
	 */
	public static final int SK_SEND_33 = 33;

	/**
	 * 自感光开关
	 */
	public static final int SK_SEND_34 = 34;

	/**
	 * 设置关灯阈值补偿差值
	 */
	public static final int SK_SEND_36 = 36;
	/**
	 * 触发源上报功能开关0：打开 ：1关闭
	 */
	public static final int SK_SEND_37 = 37;
	/**
	 * 上报触发源 0：微波触发，1：433触发
	 */
	public static final int SK_SEND_38 = 38;
	/**
	 * 设置微亮亮度
	 */
	public static final int SK_SEND_39 = 39;
	/**
	 * 触发亮度开关
	 */
	public static final int SK_SEND_40 = 40;
	/**
	 * 关灯到微亮耗时
	 */
	public static final int SK_SEND_41 = 41;
	/**
	 * 微亮到全亮耗时
	 */
	public static final int SK_SEND_42 = 42;
	/**
	 * 全亮到微亮耗时
	 */
	public static final int SK_SEND_43 = 43;
	/**
	 * 微亮到关灯耗时
	 */
	public static final int SK_SEND_44 = 44;
	/**
	 * 微亮亮度维持时间
	 */
	public static final int SK_SEND_45 = 45;
	/**
	 * 触发亮度维持时间
	 */
	public static final int SK_SEND_46 = 46;
	/**
	 * 微波联动
	 */
	public static final int SK_SEND_47 = 47;
	/**
	 * 微波灵敏度
	 */
	public static final int SK_SEND_48 = 48;
	/**
	 * 读取定位信息
	 */
	public static final int SK_SEND_49 = 49;

	/***
	 * 主控制命令
	 */
	/**
	 * ZigBee业务主命令
	 */
	public static final int M_SEND_KEY_1 = 1;

	/**
	 * 灯控业务主命令
	 */
	public static final int M_SEND_KEY_2 = 2;

	/**
	 * 
	 */
	public static final int M_SEND_KEY_3 = 3;

	/**
	 * 远程读取设备固件版本及设备类型
	 */
	public static final int SK_SEND_REDERICCID_KEY_1501 = 1;

	/**
	 * 远程读取主控灯的网络配置信息
	 */
	public static final int SK_SEND_REDERICCID_KEY_1502 = 2;

	/**
	 * 数据帧回复
	 */
	public static final int SK_SEND_REDERICCID_KEY_1503 = 3;
	/**
	 * 国际移动台设备识别码
	 */
	public static final int SK_SEND_REDERICCID_KEY_1504 = 4;
	/**
	 * mq切换回复
	 */
	public static final int SK_SEND_REDERICCID_KEY_1505 = 5;
	/**
	 * 硬件版本号回复
	 */
	public static final int SK_SEND_REDERICCID_KEY_1506 = 6;

	/**
	 * 特殊命令：主命令：15
	 */
	public static final int M_SEND_KEY_15 = 15;

	/**
	 * 特殊命令：主命令：10 在线升级相关业务
	 */
	public static final int M_SEND_KEY_10 = 10;

	// --------------------------主命令：0A；的子命令---------------------------

	/**
	 * OTA开始
	 */
	public final static int OTA_SEND_KEY1 = 1;

	/**
	 * OTA请求快
	 */
	public final static int OTA_SEND_KEY2 = 2;

	/**
	 * 退出OTA请求
	 */
	public final static int OTA_SEND_KEY3 = 3;

	/**
	 * 版本恢复策略
	 */
	public final static int OTA_SEND_KEY4 = 4;

	/**
	 * 主控/联网设备周期上报存储固件信息
	 */
	public final static int OTA_SEND_KEY5 = 5;

	/**
	 * 标识（1字节 0x00 单播 ）
	 */

	public static final int IDENT_TYPE_ONE = 0;
	/**
	 * 标识（0x01 组播）
	 */
	public static final int IDENT_TYPE_GROUP = 1;

	/**
	 * NB标识
	 */
	public static final int IDENT_TYPE_2 = 2;

	/**
	 * NB711标识
	 */
	public static final int IDENT_TYPE_3 = 3;
	/**
	 * 245CG 4G
	 */
	public static final int IDENT_TYPE_4 = 4;
	/**
	 * 712G3L
	 */
	public static final int IDENT_TYPE_5 = 5;
	/**
	 * NB控制标识
	 */
	public static final int IDENT_TYPE_KEY2 = 2;

	/**
	 * 命令标识（主命令+子命令）
	 */
	// 创建网络
	public static final int IDENT_TYPE_11 = 1001;
	// 允许入网
	public static final int IDENT_TYPE_12 = 1002;
	// 复位设备
	public static final int IDENT_TYPE_16 = 1006;

	/**
	 * 获取当前设备zigbee网络配置信息
	 */
	public static final int IDENT_TYPE_17 = 17;
	/**
	 * 通知升级
	 */
	public static final int IDENT_TYPE_19 = 19;

	/**
	 * 远程读取设备固件版本及设备类型
	 */
	public static final int IDENT_TYPE_1501 = 151;

	/**
	 * 远程读取主控灯的网络配置信息
	 */
	public static final int IDENT_TYPE_1502 = 152;

	// Zigbee子命令

	/**
	 * zigbee网络参数设置 ：信道，射频
	 */
	public final static int ZM_SEND_ZIGBEE_KEY1 = 1;

	/**
	 * 打开网络
	 */
	public final static int ZM_SEND_ZIGBEE_KEY2 = 2;

	/**
	 * 入离网
	 */
	public final static int ZM_SEND_ZIGBEE_KEY3 = 3;

	/**
	 * 2.4.10.白名单批量操作
	 */
	public final static int ZM_SEND_ZIGBEE_KEY12 = 12;

	/**
	 * 当返回 data = 0 时成功
	 */
	public final static int SEND_CONSTANT_SUCCESS = 0;

	/**
	 * 当返回 data = 1 时失败
	 */
	public final static int SEND_CONSTANT_ERROR = 1;
	/**
	 * 当返回 data = 255 时失败
	 */
	public final static int SEND_CONSTANT_ERROR_255 = 255;

	/**
	 * 灯故障状态
	 */

	/**
	 * 0 正常
	 */
	public final static int FAULT_CONDITION_KEY0 = 0;
	/**
	 * 1故障
	 */
	public final static int FAULT_CONDITION_KEY1 = 1;
	/**
	 * 2维修
	 */
	public final static int FAULT_CONDITION_KEY2 = 2;
	/**
	 * 3告警
	 */
	public final static int FAULT_CONDITION_KEY3 = 3;

	/**
	 * 故障统计
	 */
	/**
	 * 0：正常
	 */
	public final static int FAULT_TOTAL_KEY0 = 0;

	/**
	 * 1：故障
	 */
	public final static int FAULT_TOTAL_KEY1 = 1;

	/**
	 * 亮度故障是否恢复，亮灯和灭灯的时候实际功率与理论功率匹配恢复正常，0:生成故障
	 * 1：亮灯时匹配(灭灯时判断匹配则恢复正常)；2：灭灯时匹配(亮灯时判断匹配则恢复正常) luminanceFault
	 */
	public final static String LUMINANCE_FAULT = "luminanceFault_";
	/**
	 * 故障标识主标识
	 */
	/**
	 * 主动上报0
	 */
	public final static int FAULT_TYPE_0 = 0;
	/**
	 * 通讯故障1
	 */
	public final static int FAULT_TYPE_1 = 1;
	/**
	 * 人为上报
	 */
	public final static int FAULT_TYPE_2 = 2;

	/**
	 * 系统分析
	 */
	public final static int FAULT_TYPE_3 = 3;

	// ----------------------灯控器告警上报-----------------------
	/**
	 * 恢复正常
	 */
	public final static int FAULT_SUB_KEY_0 = 0;

	/**
	 * 欠压恢复
	 */
	public final static int FAULT_SUB_KEY_1 = 1;

	/**
	 * 过压恢复
	 */
	public final static int FAULT_SUB_KEY_2 = 2;

	/**
	 * 过流恢复
	 */
	public final static int FAULT_SUB_KEY_3 = 3;

	/**
	 * 过压
	 */
	public final static int FAULT_SUB_KEY_16 = 16;
	/**
	 * 过流
	 */
	public final static int FAULT_SUB_KEY_17 = 17;
	/**
	 * 欠压
	 */
	public final static int FAULT_SUB_KEY_18 = 18;
	/**
	 * 微波故障
	 */
	public final static int FAULT_SUB_KEY_19 = 19;
	/**
	 * 433故障
	 */
	public final static int FAULT_SUB_KEY_20 = 20;

	/**
	 * 过压恢复上报
	 */
	public final static int FAULT_SUB_KEY_160 = 160;

	/**
	 * 过流恢复上报
	 */
	public final static int FAULT_SUB_KEY_161 = 161;

	/**
	 * 欠压恢复上报
	 */
	public final static int FAULT_SUB_KEY_162 = 162;

	/**
	 * 微波恢复上报
	 */
	public final static int FAULT_SUB_KEY_163 = 163;

	/**
	 * 433恢复上报
	 */
	public final static int FAULT_SUB_KEY_164 = 164;

	/**
	 * 通讯失败
	 */
	public final static int FAULT_SUB_TYPE_10 = 10;

	/**
	 * 灯控器：单控
	 */
	public final static int CONTROL_TYPE_0 = 0;

	/**
	 * 灯控器：主控
	 */
	public final static int CONTROL_TYPE_1 = 1;

	/**
	 * 全局控制状态 1：发送成功
	 */
	public final static int TERMINAL_CONTROLLER_STATE_1 = 1;

	/**
	 * 全局控制状态 2：发送失败
	 */
	public final static int TERMINAL_CONTROLLER_STATE_2 = 2;

	/**
	 * 全局控制状态 3：发送中
	 */
	public final static int TERMINAL_CONTROLLER_STATE_3 = 3;

	/**
	 * 灯控器发送状态：-1：发送
	 */
	public final static int TERMINAL_SEND_STATUS_1 = -1;

	/**
	 * 灯控器发送状态：0：返回成功
	 */
	public final static int TERMINAL_SEND_STATUS0 = 0;

	/**
	 * 灯控器发送状态：1：返回失败
	 */
	public final static int TERMINAL_SEND_STATUS1 = 1;

	/**
	 * 灯控器发送状态：2：app呼吸灯
	 */
	public final static int TERMINAL_SEND_STATUS2 = 2;

	/**
	 * 调试界面进行调试
	 */
	public final static int UPDATE_CONTROLLER_STATUS_0 = 0;

	/**
	 * 维修调试
	 */
	public final static int UPDATE_CONTROLLER_STATUS_1 = 1;

	/**
	 * 选择策略自感下发端口0：前端
	 */
	public final static int TACTIC_TYPE_0 = 0;
	/**
	 * 自感光模式
	 */
	public final static int TACTIC_TYPE_DATA_0 = 0;
	/**
	 * 移动感应模式
	 */
	public final static int TACTIC_TYPE_DATA_1 = 1;
	/**
	 * 自感光+移动感应模式
	 */
	public final static int TACTIC_TYPE_DATA_2 = 2;

	/**
	 * 自感光+移动感应模式
	 */
	public final static int TACTIC_TYPE_4 = 4;
	/**
	 * 移动感应模式
	 */
	public final static int TACTIC_TYPE_5 = 5;
	/**
	 * SOS模式
	 */
	public final static int TACTIC_TYPE_6 = 6;

	/**
	 * 选择策略自感下发端口1：app
	 */
	public final static int TACTIC_TYPE_1 = 1;

	/**
	 * 下发开始升级状态值 0：下发
	 */
	public final static int UPGRADE_START_STATUS_0 = 0;

	/**
	 * 下发开始升级状态值 1：下发成功
	 */
	public final static int UPGRADE_START_STATUS_1 = 1;

	/**
	 * 下发开始升级状态值 2：下发失败
	 */
	public final static int UPGRADE_START_STATUS_2 = 2;

	/**
	 * 白名单下发升级状态值 1：增加成功
	 */
	public final static int UPGRADE_WHITELIST_START_1 = 1;

	/**
	 * 白名单下发升级状态值 2：删除成功
	 */
	public final static int UPGRADE_WHITELIST_START_2 = 2;

}
