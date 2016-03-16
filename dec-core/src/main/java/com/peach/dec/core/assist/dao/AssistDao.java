package com.peach.dec.core.assist.dao;

import com.peach.dec.core.assist.entity.MessageEntity;
import com.peach.dec.core.stu.entity.MessageDetailsEntity;
import com.peach.dec.util.util.PageModel;

public interface AssistDao {
	public PageModel<MessageDetailsEntity> getAssistMessage(
			MessageDetailsEntity messageDetails, int pageSize, int page,
			String beOverdue);

	public boolean addAssistMessage(MessageEntity message);

	public boolean updateAssistMessage(MessageEntity messageDetails);

	public MessageDetailsEntity getAssistEntity(MessageEntity message);

	public boolean updateAssistMessages(MessageDetailsEntity messageDetails);

}
