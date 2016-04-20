package com.bistu.intimate.vo;

import java.io.Serializable;

public class MajorInfoSearchVo implements Serializable {
	private static final long serialVersionUID = -6415247282230553890L;
	
	/**
	 * 专业id
	 */
	private int majorId;
	
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
	
	/**
	 * 科目1
	 */
	private String lesson1Name;

	/**
	 * 科目2
	 */
	private String lesson2Name;
	
	/**
	 * 科目3
	 */
	private String lesson3Name;
	
	/**
	 * 科目4
	 */
	
	private String lesson4Name;

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

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

	public String getLesson1Name() {
		return lesson1Name;
	}

	public void setLesson1Name(String lesson1Name) {
		this.lesson1Name = lesson1Name;
	}

	public String getLesson2Name() {
		return lesson2Name;
	}

	public void setLesson2Name(String lesson2Name) {
		this.lesson2Name = lesson2Name;
	}

	public String getLesson3Name() {
		return lesson3Name;
	}

	public void setLesson3Name(String lesson3Name) {
		this.lesson3Name = lesson3Name;
	}

	public String getLesson4Name() {
		return lesson4Name;
	}

	public void setLesson4Name(String lesson4Name) {
		this.lesson4Name = lesson4Name;
	}
	
}
