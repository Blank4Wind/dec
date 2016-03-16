package com.peach.dec.core.assist.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peach.dec.core.assist.dao.AssistDao;
import com.peach.dec.core.assist.entity.MessageEntity;
import com.peach.dec.core.assist.service.AssistService;
import com.peach.dec.core.stu.entity.MessageDetailsEntity;
import com.peach.dec.util.util.PageModel;

@Service
public class AssistServiceImpl implements AssistService {
	@Resource
	private AssistDao assistDao;

	@Override
	public PageModel<MessageDetailsEntity> getAssistMessage(
			MessageDetailsEntity messageDetails, int pageSize, int page,
			String beOverdue) {
		return assistDao.getAssistMessage(messageDetails, pageSize, page,
				beOverdue);
	}

	@Override
	public boolean addAssistMessage(MessageEntity message) {
		return assistDao.addAssistMessage(message);
	}

	@Override
	public boolean updateAssistMessage(MessageEntity messageDetails) {

		return assistDao.updateAssistMessage(messageDetails);

	}

	@Override
	public MessageDetailsEntity getAssistEntity(MessageEntity message) {
		return assistDao.getAssistEntity(message);
	}

	@Override
	public boolean updateAssistMessages(MessageDetailsEntity messageDetails) {
		return assistDao.updateAssistMessages(messageDetails);
	}

}
