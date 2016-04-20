package com.bistu.intimate.bean;

import java.io.Serializable;

public class BaseBean implements Serializable {
	private static final long serialVersionUID = 3170540224269300366L;
	/**
	 * 页码
	 */
	private int pageNo;
	/**
	 * 每页size
	 */
	private int pageSize = 5;
	
	public int getPageNo() {
		if(pageNo <= 0) {
			return 1;
		}
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}