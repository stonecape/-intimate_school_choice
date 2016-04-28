package com.bistu.intimate.bean;

public class SchoolInfoQueryBean extends BaseBean{
	private static final long serialVersionUID = 1329968761052954258L;

	/**
	 * 学校名称
	 */
	 private String schoolName;
	 
	 /**
	  * 省市
	  */
	 private String province;

	 /**
	  * 地区
	  */
	 private String area;

	 /**
	  * 属性985...
	  */
	 private String schoolProperty;

	 /**
	  * 类型
	  */
	 private String schoolType;
	 
	 /**
	  * 性质
	  */
	 private String schoolCharacter;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSchoolProperty() {
		return schoolProperty;
	}

	public void setSchoolProperty(String schoolProperty) {
		this.schoolProperty = schoolProperty;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public String getSchoolCharacter() {
		return schoolCharacter;
	}

	public void setSchoolCharacter(String schoolCharacter) {
		this.schoolCharacter = schoolCharacter;
	}
	 
}
