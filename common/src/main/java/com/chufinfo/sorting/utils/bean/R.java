package com.chufinfo.sorting.utils.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 返回数据
 * 
 * @author kenyon
 * @date 2016年10月27日 下午9:59:27
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 1);
	}

	public static R error() {
		return error(500 ,"未知异常，请联系管理员");
	}

	public static R error(String msg) {
		return error(500, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok (List<Object> list){
		R r = new R();
		r.put("msg", list);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}
	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
