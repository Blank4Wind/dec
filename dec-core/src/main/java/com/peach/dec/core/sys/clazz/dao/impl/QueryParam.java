package com.peach.dec.core.sys.clazz.dao.impl;

import java.util.Date;

/**
 * @author guosong
 * @version 2015年5月23日 下午4:03:462015年5月23日
 * @explain
 */
public class QueryParam {

	private String name;
	private Date startTime;
	private Date endTime;
	private Short state;
	private int pageSize;
	private int page;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

}
