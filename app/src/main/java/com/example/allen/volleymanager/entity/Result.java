package com.example.allen.volleymanager.entity;

import java.io.Serializable;

/**
 * 
 * @author fc
 *
 *         所有的返回数据，统一解析成Result对象，以供调用；
 *         必须遵循统一的格式，例如："{"isSuccess":true,"msg":"","data":{"name":"付超"}}";
 * @param <T>
 */
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean isSuccess;
	private String msg;
	private int errorCode;
	private T data;

	public Result() {
	}

	public Result(boolean isSuccess, String msg, int errorCode, T data) {
		this.isSuccess = isSuccess;
		this.msg = msg;
		this.errorCode = errorCode;
		this.data = data;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [isSuccess=" + isSuccess + ", msg=" + msg
				+ ", errorCode=" + errorCode + ", data=" + data + "]";
	}

}
