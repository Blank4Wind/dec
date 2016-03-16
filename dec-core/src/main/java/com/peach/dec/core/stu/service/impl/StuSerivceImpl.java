package com.peach.dec.core.stu.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.peach.dec.core.stu.dao.StuDao;
import com.peach.dec.core.stu.entity.MessageDetailsEntity;
import com.peach.dec.core.stu.entity.PlanDetailEntity;
import com.peach.dec.core.stu.entity.RecordsDetailsEntity;
import com.peach.dec.core.stu.service.StuService;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.util.PageModel;

@Service
public class StuSerivceImpl implements StuService {
	@Resource
	private StuDao stuDao;

	@Override
	public List<PlanDetailEntity> getExamPlan(UserEntity user1) {
		return stuDao.getExamPlan(user1);
	}

	@Override
	public PageModel<RecordsDetailsEntity> getExamRecords(UserEntity user1,
			int pageSize, int page, RecordsDetailsEntity recordsDetails) {
		return stuDao.getExamRecords(user1, pageSize, page, recordsDetails);
	}

	@Override
	public List<MessageDetailsEntity> getAssistMessage(UserEntity user1) {
		List<Object> object = stuDao.getAssistMessage(user1);
		if (object == null) {
			return null;
		}
		;
		List<MessageDetailsEntity> list = new ArrayList<MessageDetailsEntity>();
		for (Object plan : object) {
			MessageDetailsEntity plan1 = (MessageDetailsEntity) plan;
			list.add(plan1);
		}
		return list;
	}
}
