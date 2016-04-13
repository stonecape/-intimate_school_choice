package com.bistu.intimate.common;

public class Result<T> implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 处理成功标识
	 */
	private boolean success;
	/** 
	 * 返回码
	 */
	private String resultCode;
	/**
	 * 错误信息
	 */
	private String errorMsg;
	private T value;

	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	
}
