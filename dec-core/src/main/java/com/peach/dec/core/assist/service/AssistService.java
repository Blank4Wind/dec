package com.peach.dec.core.assist.service;

import com.peach.dec.core.assist.entity.MessageEntity;
import com.peach.dec.core.stu.entity.MessageDetailsEntity;
import com.peach.dec.util.util.PageModel;

public interface AssistService {

	PageModel<MessageDetailsEntity> getAssistMessage(
			MessageDetailsEntity messageDetails, int pageSize, int page,
			String beOverdue);

	boolean addAssistMessage(MessageEntity message);

	boolean updateAssistMessage(MessageEntity messageDetails);

	MessageDetailsEntity getAssistEntity(MessageEntity message);

	boolean updateAssistMessages(MessageDetailsEntity messageDetails);

}
