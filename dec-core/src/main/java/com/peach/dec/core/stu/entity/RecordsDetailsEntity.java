package com.peach.dec.core.stu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

/**
 * 考试记录实体类
 * 
 * @author peach
 * @date 2015年5月17日
 */

@Component
@Entity
@Table(name = "RECORDS_DETAILS")
@DynamicUpdate
public class RecordsDetailsEntity {

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
	@Column
	private String qname;
	@Column
	private Short tpoint;

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

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public Short getTpoint() {
		return tpoint;
	}

	public void setTpoint(Short tpoint) {
		this.tpoint = tpoint;
	}

}