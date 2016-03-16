package com.peach.dec.core.qes.entity;

import java.util.List;

/**
 * @author guosong
 * @version 2015年5月27日 下午9:27:232015年5月27日
 * @explain 试卷试题类
 */
public class QesExamPaperEntity {

	private String id;
	private String name;
	private String courseId;
	private String courseName;
	private String userId;
	private String userName;

	private Short paperType;


	private Integer totalPoint;
	private Integer passPoint;
	private Integer totalMinutes;

	private List<QestionsEntity> listSingle;
	private List<QestionsEntity> listMulti;
	private List<QestionsEntity> listJudge;


	private ExamPaperEntity examPaperEntity;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Short getPaperType() {
		return paperType;
	}

	public void setPaperType(Short paperType) {
		this.paperType = paperType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Integer getPassPoint() {
		return passPoint;
	}

	public void setPassPoint(Integer passPoint) {
		this.passPoint = passPoint;
	}

	public Integer getTotalMinutes() {
		return totalMinutes;
	}

	public void setTotalMinutes(Integer totalMinutes) {
		this.totalMinutes = totalMinutes;
	}

	public List<QestionsEntity> getListSingle() {
		return listSingle;
	}

	public void setListSingle(List<QestionsEntity> listSingle) {
		this.listSingle = listSingle;
	}

	public List<QestionsEntity> getListMulti() {
		return listMulti;
	}

	public void setListMulti(List<QestionsEntity> listMulti) {
		this.listMulti = listMulti;
	}

	public List<QestionsEntity> getListJudge() {
		return listJudge;
	}

	public void setListJudge(List<QestionsEntity> listJudge) {
		this.listJudge = listJudge;
	}


	public ExamPaperEntity getExamPaperEntity() {
		return examPaperEntity;
	}

	public void setExamPaperEntity(ExamPaperEntity examPaperEntity) {
		this.examPaperEntity = examPaperEntity;
	}

	@Override
	public String toString() {
		return "QesExamPaperEntity [id=" + id + ", name=" + name
				+ ", courseId=" + courseId + ", courseName=" + courseName
				+ ", userId=" + userId + ", userName=" + userName
				+ ", paperType=" + paperType + ", totalPoint=" + totalPoint
				+ ", passPoint=" + passPoint + ", totalMinutes=" + totalMinutes
				+ ", listSingle=" + listSingle + ", listMulti=" + listMulti
				+ ", listJudge=" + listJudge + ", examPaperEntity="
				+ examPaperEntity + "]";
	}

}
