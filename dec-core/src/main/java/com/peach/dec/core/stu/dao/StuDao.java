package com.peach.dec.core.stu.dao;

import java.util.List;

import com.peach.dec.core.stu.entity.PlanDetailEntity;
import com.peach.dec.core.stu.entity.RecordsDetailsEntity;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.util.PageModel;

public interface StuDao {

	public List<PlanDetailEntity> getExamPlan(UserEntity user1);

	public PageModel<RecordsDetailsEntity> getExamRecords(UserEntity user1,
			int pageSize, int page, RecordsDetailsEntity recordsDetails);

	public List<Object> getAssistMessage(UserEntity user1);
}
