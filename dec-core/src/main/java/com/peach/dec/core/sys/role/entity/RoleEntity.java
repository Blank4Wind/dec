package com.peach.dec.core.sys.role.entity;

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
 * 角色实体类
 * 
 * @date 2015年5月17日
 */
@Component
@Entity
@Table(name = "sys_role")
@DynamicUpdate
public class RoleEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column
	private String name;
	@Column(name = "menu_ids")
	private String menuIds;
	@Column(name = "operate_user_id")
	private String operateUserId;
	@Column(name = "operate_time")
	private Date operateTime;
	@Column
	private Short state;
	@Column
	private String memo;

	@Transient
	private String userName;

	@Transient
	private PowerEntity powerEntity;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
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

	public PowerEntity getPowerEntity() {
		return powerEntity;
	}

	public void setPowerEntity(PowerEntity powerEntity) {
		this.powerEntity = powerEntity;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", name=" + name + ", menuIds="
				+ menuIds + ", operateUserId=" + operateUserId
				+ ", operateTime=" + operateTime + ", state=" + state
				+ ", memo=" + memo + "]";
	}

}