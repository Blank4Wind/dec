package com.peach.dec.core.exam.entity;

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
 * 考试记录实体类
 * 
 * @author peach=
 * @date 2015年5月17日
 */

@Component
@Entity
@Table(name = "exam_records")
@DynamicUpdate
public class RecordsEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "exam_plan_id")
	private String examPlanId;
	@Column(name = "get_point")
	private Integer getPoint;
	@Column(name = "submit_time")
	private Date submitTime;
	@Column(name = "start_time")
	private Date startTime;
	@Column(name = "is_pass")
	private Short isPass;

	// 扩展字段
	@Transient
	private String userName;
	@Transient
	private String paperName;
	@Transient
	private Integer totalPoint;
	@Transient
	private String name;
	@Transient
	private Long y;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getExamPlanId() {
		return examPlanId;
	}

	public void setExamPlanId(String examPlanId) {
		this.examPlanId = examPlanId;
	}

	public Integer getGetPoint() {
		return getPoint;
	}

	public void setGetPoint(Integer getPoint) {
		this.getPoint = getPoint;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Short getIsPass() {
		return isPass;
	}

	public void setIsPass(Short isPass) {
		this.isPass = isPass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Integer getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getY() {
		return y;
	}

	public void setY(Long y) {
		this.y = y;
	}

}