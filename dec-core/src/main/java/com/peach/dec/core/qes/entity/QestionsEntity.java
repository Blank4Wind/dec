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
 * 试题实体类
 * 
 * @author peach
 * @date 2015年5月17日
 */
@Component
@Entity
@Table(name = "qes_qestions")
@DynamicUpdate
public class QestionsEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column
	private String question;
	@Column
	private String attachment;
	@Column(name = "question_type")
	private Short questionType;
	@Column(name = "course_id")
	private String courseId;
	@Column(name = "answer_a")
	private String answerA;
	@Column(name = "answer_b")
	private String answerB;
	@Column(name = "answer_c")
	private String answerC;
	@Column(name = "answer_d")
	private String answerD;
	@Column(name = "answer")
	private String answer;
	@Column
	private Short difficulty;
	@Column
	private String analysis;
	@Column
	private String keywords;
	@Column(name = "operate_user_id")
	private String operateUserId;
	@Column(name = "operate_time")
	private Date operateTime;
	@Column
	private Short state;
	@Column
	private String memo;

	// 扩展字段
	@Transient
	private String courseName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Short getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Short questionType) {
		this.questionType = questionType;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getAnswerA() {
		return answerA;
	}

	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}

	public String getAnswerB() {
		return answerB;
	}

	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}

	public String getAnswerC() {
		return answerC;
	}

	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}

	public String getAnswerD() {
		return answerD;
	}

	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Short getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Short difficulty) {
		this.difficulty = difficulty;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
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

	@Override
	public String toString() {
		return "QestionsEntity [id=" + id + ", question=" + question
				+ ", attachment=" + attachment + ", questionType="
				+ questionType + ", courseId=" + courseId + ", answerA="
				+ answerA + ", answerB=" + answerB + ", answerC=" + answerC
				+ ", answerD=" + answerD + ", answer=" + answer
				+ ", difficulty=" + difficulty + ", analysis=" + analysis
				+ ", keywords=" + keywords + ", operateUserId=" + operateUserId
				+ ", operateTime=" + operateTime + ", state=" + state
				+ ", memo=" + memo + ", courseName=" + courseName + "]";
	}

}