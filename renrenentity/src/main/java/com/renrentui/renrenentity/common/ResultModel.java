package com.renrentui.renrenentity.common;
 

public class ResultModel <T> {
	private int code;
	private String msg;
	private T data;
	public int getCode() {
		return code;
	}
	public ResultModel<T> setCode(int code) {
		this.code = code;
		return this;
	}
	public String getMsg() {
		return msg;
	}
	public ResultModel<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public T getData() {
		return data;
	}
	public ResultModel<T> setData(T data) {
		this.data = data;
		return this;
	}
}