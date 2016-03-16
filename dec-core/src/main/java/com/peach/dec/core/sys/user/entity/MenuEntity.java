package com.peach.dec.core.sys.user.entity;

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
 * 菜单实体类
 * 
 * @author peach
 * @date 2015年5月17日
 */
@Component
@Entity
@Table(name = "sys_menu")
@DynamicUpdate
public class MenuEntity {
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
	@Column
	private String url;
	@Column(name = "opreate_user_id")
	private String opreateUserId;
	@Column(name = "operate_time")
	private Date operateTime;
	@Column
	private Short state;
	@Column
	private String memo;

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

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOpreateUserId() {
		return opreateUserId;
	}

	public void setOpreateUserId(String opreateUserId) {
		this.opreateUserId = opreateUserId;
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

	@Override
	public String toString() {
		return "MenuEntity [id=" + id + ", pid=" + pid + ", isLeaf=" + isLeaf
				+ ", name=" + name + ", url=" + url + ", opreateUserId="
				+ opreateUserId + ", operateTime=" + operateTime + ", state="
				+ state + ", memo=" + memo + "]";
	}

}