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
 * 站内消息实体类
 * 
 * @author peach
 * @date 2015年5月17日
 */
@Component
@Entity
@Table(name = "assist_message")
@DynamicUpdate
public class MessageEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column
	private String title;
	@Column
	private String content;
	@Column(name = "send_time")
	private Date sendTime;
	@Column(name = "sender_id")
	private String senderId;
	@Column(name = "receiver_user_ids")
	private String receiverUserIds;
	@Column(name = "receiver_class_ids")
	private String receiverClassIds;
	@Column(name = "expire_time")
	private Date expireTime;
	@Column
	private Short state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverUserIds() {
		return receiverUserIds;
	}

	public void setReceiverUserIds(String receiverUserIds) {
		this.receiverUserIds = receiverUserIds;
	}

	public String getReceiverClassIds() {
		return receiverClassIds;
	}

	public void setReceiverClassIds(String receiverClassIds) {
		this.receiverClassIds = receiverClassIds;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "MessageEntity [id=" + id + ", title=" + title + ", content="
				+ content + ", sendTime=" + sendTime + ", senderId=" + senderId
				+ ", receiverUserIds=" + receiverUserIds
				+ ", receiverClassIds=" + receiverClassIds + ", expireTime="
				+ expireTime + ", state=" + state + "]";
	}

}