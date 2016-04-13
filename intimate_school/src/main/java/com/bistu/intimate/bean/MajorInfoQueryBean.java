package com.bistu.intimate.bean;

public class MajorInfoQueryBean extends BaseBean{
	private static final long serialVersionUID = -3854262791215746245L;
	/**
	 * 专业名称
	 */
	private String majorName;
	/**
	 * 专业门类
	 */
	private String majorClass;
	/**
	 * 专业类型
	 */
	private String majorType;
	
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getMajorClass() {
		return majorClass;
	}
	public void setMajorClass(String majorClass) {
		this.majorClass = majorClass;
	}
	public String getMajorType() {
		return majorType;
	}
	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}
	
}
