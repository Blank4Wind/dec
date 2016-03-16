package com.peach.dec.core.qes.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

/**
 * 试卷实体类
 * 
 * @author peach
 * @date 2015年5月17日
 */
@Component
@Entity
@Table(name = "qes_exam_paper")
@DynamicUpdate
public class ExamPaperEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column
	private String name;
	@Column(name = "paper_url")
	private String paperUrl;
	@Column(name = "paper_type")
	private Short paperType;
	@Column(name = "course_id")
	private String courseId;
	@Column(name = "total_point")
	private Integer totalPoint;
	@Column(name = "pass_point")
	private Integer passPoint;
	@Column(name = "total_minutes")
	private Integer totalMinutes;
	@Column(name = "single_option_number")
	private Short singleOptionNumber;
	@Column(name = "single_option_each_point")
	private Short singleOptionEachPoint;
	@Column(name = "single_option_difficulty")
	private Short singleOptionDifficulty;
	@Column(name = "multi_option_number")
	private Short multiOptionNumber;
	@Column(name = "multi_option_each_point")
	private Short multiOptionEachPoint;
	@Column(name = "multi_option_difficulty")
	private Short multiOptionDifficulty;
	@Column(name = "judge_number")
	private Short judgeNumber;
	@Column(name = "judge_each_point")
	private Short judgeEachPoint;
	@Column(name = "judge_difficulty")
	private Short judgeDifficulty;
	@Column(name = "opreate_user_id")
	private String opreateUserId;
	@Column(name = "opreate_time")
	private Date opreateTime;
	@Column
	private Short state;
	@Column
	private String memo;
	@Transient
	private String courseName;
	@Transient
	private String userName;

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

	public String getPaperUrl() {
		return paperUrl;
	}

	public void setPaperUrl(String paperUrl) {
		this.paperUrl = paperUrl;
	}

	public Short getPaperType() {
		return paperType;
	}

	public void setPaperType(Short paperType) {
		this.paperType = paperType;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
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

	public Short getSingleOptionNumber() {
		return singleOptionNumber;
	}

	public void setSingleOptionNumber(Short singleOptionNumber) {
		this.singleOptionNumber = singleOptionNumber;
	}

	public Short getSingleOptionEachPoint() {
		return singleOptionEachPoint;
	}

	public void setSingleOptionEachPoint(Short singleOptionEachPoint) {
		this.singleOptionEachPoint = singleOptionEachPoint;
	}

	public Short getSingleOptionDifficulty() {
		return singleOptionDifficulty;
	}

	public void setSingleOptionDifficulty(Short singleOptionDifficulty) {
		this.singleOptionDifficulty = singleOptionDifficulty;
	}

	public Short getMultiOptionNumber() {
		return multiOptionNumber;
	}

	public void setMultiOptionNumber(Short multiOptionNumber) {
		this.multiOptionNumber = multiOptionNumber;
	}

	public Short getMultiOptionEachPoint() {
		return multiOptionEachPoint;
	}

	public void setMultiOptionEachPoint(Short multiOptionEachPoint) {
		this.multiOptionEachPoint = multiOptionEachPoint;
	}

	public Short getMultiOptionDifficulty() {
		return multiOptionDifficulty;
	}

	public void setMultiOptionDifficulty(Short multiOptionDifficulty) {
		this.multiOptionDifficulty = multiOptionDifficulty;
	}

	public Short getJudgeNumber() {
		return judgeNumber;
	}

	public void setJudgeNumber(Short judgeNumber) {
		this.judgeNumber = judgeNumber;
	}

	public Short getJudgeEachPoint() {
		return judgeEachPoint;
	}

	public void setJudgeEachPoint(Short judgeEachPoint) {
		this.judgeEachPoint = judgeEachPoint;
	}

	public Short getJudgeDifficulty() {
		return judgeDifficulty;
	}

	public void setJudgeDifficulty(Short judgeDifficulty) {
		this.judgeDifficulty = judgeDifficulty;
	}

	public String getOpreateUserId() {
		return opreateUserId;
	}

	public void setOpreateUserId(String opreateUserId) {
		this.opreateUserId = opreateUserId;
	}

	public Date getOpreateTime() {
		return opreateTime;
	}

	public void setOpreateTime(Date opreateTime) {
		this.opreateTime = opreateTime;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "ExamPaperEntity [id=" + id + ", name=" + name + ", paperUrl="
				+ paperUrl + ", paperType=" + paperType + ", courseId="
				+ courseId + ", totalPoint=" + totalPoint + ", passPoint="
				+ passPoint + ", totalMinutes=" + totalMinutes
				+ ", singleOptionNumber=" + singleOptionNumber
				+ ", singleOptionEachPoint=" + singleOptionEachPoint
				+ ", singleOptionDifficulty=" + singleOptionDifficulty
				+ ", multiOptionNumber=" + multiOptionNumber
				+ ", multiOptionEachPoint=" + multiOptionEachPoint
				+ ", multiOptionDifficulty=" + multiOptionDifficulty
				+ ", judgeNumber=" + judgeNumber + ", judgeEachPoint="
				+ judgeEachPoint + ", judgeDifficulty=" + judgeDifficulty
				+ ", opreateUserId=" + opreateUserId + ", opreateTime="
				+ opreateTime + ", state=" + state + ", memo=" + memo
				+ ", courseName=" + courseName + ", userName=" + userName + "]";
	}

}