package com.bistu.intimate.vo;

import java.io.Serializable;

public class MajorDetailVo implements Serializable {
	private static final long serialVersionUID = 5751185037010148937L;
	
	/**
	 * 专业详情id
	 */
	private Integer majorDetailId;
	
	/**
	 * 学校id
	 */
	private Integer schoolId;
	
	/**
	 * 学校名称
	 */
	private String schoolName;
	
	/**
	 * 专业id
	 */
	private Integer majorId;
	
	/**
	 * 专业名称
	 */
	private String majorName;
	
	/**
	 * 招生人数
	 */
	private Integer enrollmentNo;
	
	/**
	 * 报名人数
	 */
	private Integer participantNo;
	
	/**
	 * 录取人数
	 */
	private Integer offerNo;
	
	/**
	 * 是否收藏
	 */
	private boolean collect;

	public Integer getMajorDetailId() {
		return majorDetailId;
	}

	public void setMajorDetailId(Integer majorDetailId) {
		this.majorDetailId = majorDetailId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Integer getEnrollmentNo() {
		return enrollmentNo;
	}

	public void setEnrollmentNo(Integer enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}

	public Integer getParticipantNo() {
		return participantNo;
	}

	public void setParticipantNo(Integer participantNo) {
		this.participantNo = participantNo;
	}

	public Integer getOfferNo() {
		return offerNo;
	}

	public void setOfferNo(Integer offerNo) {
		this.offerNo = offerNo;
	}

	public boolean isCollect() {
		return collect;
	}

	public void setCollect(boolean collect) {
		this.collect = collect;
	}

	
}
