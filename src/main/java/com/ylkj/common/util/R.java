package com.ylkj.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面响应entity
 *
 * @author ljw
 * 
 * 
 * @date 2022年11月27日-下午4:30:19
 */
public class R extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 0);
	}

	public static R error(HttpServletRequest request) {
		return error(request, 500, "未知异常，请联系管理员");
	}

	public static R error(HttpServletRequest request, String msg) {
		return error(request, 500, msg);
	}

	public static R error(HttpServletRequest request, int code, String msg) {

		R r = new R();
		r.put("code", code);
		r.put("data", msg);
		return r;
	}

	public static R error(HttpServletRequest request, int code, String msg,
			boolean isimage) {

		R r = new R();
		r.put("code", code);
		r.put("data", msg);
		r.put("verification", isimage);
		return r;
	}

	public static R ok(HttpServletRequest request, String msg) {

		R r = new R();
		r.put("data", msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok(int code, Object msg) {
		R r = new R();
		r.put("code", code);
		r.put("data", msg);
		return r;
	}

	public static R ok(HttpServletRequest request, int code, String msg) {

		R r = new R();
		r.put("code", code);
		r.put("data", msg);
		return r;
	}

	public static R ok(Object msg) {
		R r = new R();
		r.put("data", msg);
		return r;
	}

	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	/**
	 * App返回
	 * 
	 * @param msg
	 * @return
	 */
	public static R error(String msg) {
		return error(500, msg);
	}

	/**
	 * App返回
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("data", msg);
		return r;
	}

	/**
	 * App返回
	 * 
	 * @return
	 */
	public static R error() {
		return error(500, "未知异常，请联系管理员");
	}
}