package com.ylkj.common.util;

/**
 * 系统提示静态变量
 *
 * @author ljw
 * 
 * 
 * @date 2022年11月27日-下午4:30:19
 */
public class MsgConstant {

	/**
	 * 操作成功
	 */
	public static final String MSG_OPERATION_SUCCESS = "操作成功！";

	/**
	 * 操作失败
	 */
	public static final String MSG_OPERATION_FAILED = "操作失败！";

	/**
	 * 删除时，提示有子节点无法删除
	 */
	public static final String MSG_HAS_CHILD = "操作失败，当前所选数据有子节点数据！";

	/**
	 * 删除时，提示有空开存在禁止删除
	 */
	public static final String MSG_KKAIRGPRS_CHILD = "操作失败，当前所选数据有空开存在！";

	/**
	 * 当前平均光照度过高
	 */
	public static final String MSG_ABOVE_AVG_ILLUMINANCEAVG = "当前平均照度过高，不能进行采光校准";

	/**
	 * 当前平均光照度过低
	 */
	public static final String MSG_BELOW_AVG_ILLUMINANCEAVG = "当前平均照度过低，不能进行采光校准";

	/**
	 * 当前环境光照度为空
	 */
	public static final String MSG_NULL_AVG_ILLUMINANCEAVG = "当前环境光照度为空，不能进行采光校准";

	/**
	 * 加载表单数据错误提示
	 */
	public static final String MSG_INIT_FORM = "初始化表单数据失败，请重试！";

	/**
	 * 删除时，分组状态为禁止删除
	 */
	public static final String MSG_HAS_STATUS = "分组状态为禁止删除";

	public static final String APP_MSG_MACID_EXIST_ERROR = "mac信息已存在，请重新输入";

	/**
	 * 删除数据项不是全部所选
	 * 
	 * @param total
	 * @param process
	 * @return
	 */
	public static String removeFailed(int total, int process) {
		return "本次共处理：" + String.valueOf(total) + "条，成功："
				+ String.valueOf(process) + "条！";
	}

}
