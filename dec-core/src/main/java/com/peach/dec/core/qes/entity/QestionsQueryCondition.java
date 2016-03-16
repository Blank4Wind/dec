package com.peach.dec.core.qes.entity;

/**
 * @author guosong
 * @version 2015年5月26日 下午3:16:202015年5月26日
 * @explain 试卷查询分页条件
 */
public class QestionsQueryCondition {

	private String question;
	private String courseId;
	private short questionType;
	private short difficulty;
	private String courseName;
	private Short state;
	private int page;
	private int pageSize;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public short getQuestionType() {
		return questionType;
	}

	public void setQuestionType(short questionType) {
		this.questionType = questionType;
	}

	public short getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(short difficulty) {
		this.difficulty = difficulty;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
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
		return "QestionsQueryCondition [question=" + question + ", courseId="
				+ courseId + ", questionType=" + questionType + ", difficulty="
				+ difficulty + ", courseName=" + courseName + ", state="
				+ state + ", page=" + page + ", pageSize=" + pageSize + "]";
	}

}
