package com.bistu.intimate.vo;

import java.io.Serializable;

public class UserVo implements Serializable {
	private static final long serialVersionUID = 6201587780599196886L;
	
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 学校
	 */
	private String school;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 重复密码
	 */
	private String rePassword;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	
}
