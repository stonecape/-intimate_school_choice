package com.bistu.intimate.bean;

import java.io.Serializable;

public class UserBean implements Serializable {
	private static final long serialVersionUID = -348018571792713849L;
	
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
	
}
