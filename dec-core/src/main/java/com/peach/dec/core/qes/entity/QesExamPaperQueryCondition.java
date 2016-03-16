package com.peach.dec.core.qes.entity;

/**
 * @author guosong
 * @version 2015年5月26日 下午3:16:202015年5月26日
 * @explain 试卷查询分页条件
 */
public class QesExamPaperQueryCondition {

	private String name;
	private String courseId;
	private String courseName;
	private short state;
	private int page;
	private int pageSize;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "QesExamPaperQueryCondition [name=" + name + ", courseId="
				+ courseId + ", courseName=" + courseName + ", state=" + state
				+ ", page=" + page + ", pageSize=" + pageSize + "]";
	}
	
}
