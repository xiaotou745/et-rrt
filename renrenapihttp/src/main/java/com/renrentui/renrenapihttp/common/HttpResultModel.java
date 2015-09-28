package com.renrentui.renrenapihttp.common;


public class HttpResultModel<T> {
	private int code = HttpReturnRnums.Success.value();
	private String msg = HttpReturnRnums.Success.desc();
	private T data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

}
