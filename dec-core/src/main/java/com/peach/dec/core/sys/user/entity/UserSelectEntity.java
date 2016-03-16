package com.peach.dec.core.sys.user.entity;

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
 * 用户实体类
 * 
 * @author peach
 * @date 2015年5月17日
 */
@Component
@Entity
@Table(name = "sys_user")
@DynamicUpdate
public class UserSelectEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column
	private String code;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private Short sex;
	@Column
	private String phone;
	@Column
	private String email;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "operate_user_id")
	private String operateUserId;
	@Column(name = "operate_time")
	private Date operateTime;
	@Column(name = "role_id")
	private String roleId;
	@Column(name = "class_id")
	private String classId;
	@Column
	private Short state;
	@Column
	private String memo;

	// 扩展字段
	@Transient
	private String roleName;
	@Transient
	private String clazzName;

	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Short getSex() {
		return sex;
	}



	public void setSex(Short sex) {
		this.sex = sex;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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



	public String getRoleId() {
		return roleId;
	}



	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}



	public String getClassId() {
		return classId;
	}



	public void setClassId(String classId) {
		this.classId = classId;
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



	public String getRoleName() {
		return roleName;
	}



	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	public String getClazzName() {
		return clazzName;
	}



	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}



	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", code=" + code + ", password="
				+ password + ", name=" + name + ", sex=" + sex + ", phone="
				+ phone + ", email=" + email + ", createTime=" + createTime
				+ ", operateUserId=" + operateUserId + ", operateTime="
				+ operateTime + ", roleId=" + roleId + ", classId=" + classId
				+ ", state=" + state + ", memo=" + memo + ", roleName="
				+ roleName + ", clazzName=" + clazzName + "]";
	}

}