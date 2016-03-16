package com.peach.dec.core.stu.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import com.peach.dec.core.sys.user.entity.UserEntity;

/**
 * 站内消息实体类
 * 
 * @author peach
 * @date 2015年5月17日
 */
@Component
@Entity
@Table(name = "MESSAGE_DETAILS")
@DynamicUpdate
public class MessageDetailsEntity {

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
	@Column
	private String uname;
	@Column
	private String rname;
	@Transient
	private List<UserEntity> user2;

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

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public List<UserEntity> getUser2() {
		return user2;
	}

	public void setUser2(List<UserEntity> user2) {
		this.user2 = user2;
	}

}