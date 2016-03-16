package com.peach.dec.core.assist.entity;

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
 * 数据备份实体
 * 
 * @author peach
 * @date 2015年5月18日
 */
@Component
@Entity
@Table(name = "assit_db_data")
@DynamicUpdate
public class DbDataEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column(name = "bak_url")
	private String bakUrl;
	@Column(name = "bak_time")
	private Date bakTime;
	@Column(name = "bak_memo")
	private String bakMemo;
	@Column(name = "restore_time")
	private Date restoreTime;
	@Column(name = "restore_memo")
	private String restoreMemo;
	@Column(name = "operate_user_id")
	private String operateUserId;
	@Column(name = "is_new_data")
	private Short isNewData;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBakUrl() {
		return bakUrl;
	}

	public void setBakUrl(String bakUrl) {
		this.bakUrl = bakUrl;
	}

	public Date getBakTime() {
		return bakTime;
	}

	public void setBakTime(Date bakTime) {
		this.bakTime = bakTime;
	}

	public String getBakMemo() {
		return bakMemo;
	}

	public void setBakMemo(String bakMemo) {
		this.bakMemo = bakMemo;
	}

	public Date getRestoreTime() {
		return restoreTime;
	}

	public void setRestoreTime(Date restoreTime) {
		this.restoreTime = restoreTime;
	}

	public String getRestoreMemo() {
		return restoreMemo;
	}

	public void setRestoreMemo(String restoreMemo) {
		this.restoreMemo = restoreMemo;
	}

	public String getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}

	public Short getIsNewData() {
		return isNewData;
	}

	public void setIsNewData(Short isNewData) {
		this.isNewData = isNewData;
	}

}
