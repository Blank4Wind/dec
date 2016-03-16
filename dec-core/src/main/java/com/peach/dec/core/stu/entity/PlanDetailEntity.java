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
 * 考试安排视图实体类
 * 
 * @author 赵伟
 * @date 2015年5月17日
 */
@Component
@Entity
@Table(name = "PLAN_DETAIL")
@DynamicUpdate
public class PlanDetailEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column(name = "exam_time_start")
	private Date examTimeStart;
	@Column(name = "exam_time_stop")
	private Date examTimeStop;
	@Column(name = "exam_classroom")
	private String examClassroom;
	@Column(name = "exam_paper_id")
	private String examPaperId;
	@Column(name = "to_user_ids")
	private String toUserIds;
	@Column(name = "to_class_id")
	private String toClassId;
	@Column(name = "operate_user_id")
	private String operateUserId;
	@Column(name = "operate_time")
	private Date operateTime;
	@Column
	private Short state;
	@Column
	private String memo;
	@Column
	private String qname;
	@Column
	private String uname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getExamTimeStart() {
		return examTimeStart;
	}
	public void setExamTimeStart(Date examTimeStart) {
		this.examTimeStart = examTimeStart;
	}
	public Date getExamTimeStop() {
		return examTimeStop;
	}
	public void setExamTimeStop(Date examTimeStop) {
		this.examTimeStop = examTimeStop;
	}
	public String getExamClassroom() {
		return examClassroom;
	}
	public void setExamClassroom(String examClassroom) {
		this.examClassroom = examClassroom;
	}
	public String getExamPaperId() {
		return examPaperId;
	}
	public void setExamPaperId(String examPaperId) {
		this.examPaperId = examPaperId;
	}
	public String getToUserIds() {
		return toUserIds;
	}
	public void setToUserIds(String toUserIds) {
		this.toUserIds = toUserIds;
	}
	public String getToClassId() {
		return toClassId;
	}
	public void setToClassId(String toClassId) {
		this.toClassId = toClassId;
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
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}

	
}