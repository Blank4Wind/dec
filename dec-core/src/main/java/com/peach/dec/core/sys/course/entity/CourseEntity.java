package com.peach.dec.core.sys.course.entity;

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
 * 课程实体类
 * 
 * @author peach
 * @date 2015年5月17日
 */
@Component
@Entity
@Table(name = "sys_course")
@DynamicUpdate
public class CourseEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column
	private String pid;
	@Column(name = "is_leaf")
	private Short isLeaf;
	@Column
	private String name;
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
	private String url;
	@Transient
	private String target;
	@Transient
	private Boolean isParent;
	@Transient
	private String userName;
	@Transient
	private String parentName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Short getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Short isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public CourseEntity(String id) {
		super();
		this.id = id;
	}

	public CourseEntity() {
		super();
	}

}