package com.peach.dec.core.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peach.dec.core.exam.dao.PlanDao;
import com.peach.dec.core.exam.entity.PlanEntity;
import com.peach.dec.core.exam.service.PlanService;
import com.peach.dec.core.qes.entity.ExamPaperEntity;
import com.peach.dec.util.util.PageModel;

/**
 * 业务层：考试安排的相关操作
 * 
 * @author peach
 * @date 2015年5月24日
 */
@Service
public class PlanServiceImpl implements PlanService {

	@Resource
	private PlanDao planDao;

	/**
	 * 考试安排查询
	 * 
	 * @param id
	 * @param planEntity
	 * @param pageSize
	 * @param pageNo
	 * @param timeout
	 * @param type
	 * @return
	 */
	@Override
	public PageModel<PlanEntity> planList(String id, int pageNo, int pageSize,
			PlanEntity planEntity, String type, String timeout) {
		return planDao
				.planList(id, pageNo, pageSize, planEntity, type, timeout);
	}

	/**
	 * 查询出所有的试卷
	 * 
	 * @return
	 */
	@Override
	public List<ExamPaperEntity> paperList() {
		return planDao.paperList();
	}

	/**
	 * 添加考试安排
	 * 
	 * @param planEntity
	 * @return
	 */
	@Override
	public String insert(PlanEntity planEntity) {
		return planDao.insert(planEntity);
	}

	/**
	 * 修改前的查询
	 * 
	 * @param planEntity
	 * @return
	 */
	@Override
	public PlanEntity beforeUpdate(PlanEntity planEntity) {
		return planDao.beforeUpdate(planEntity);
	}

	/**
	 * 修改考试安排
	 * 
	 * @param planEntity
	 * @return
	 */
	@Override
	public boolean update(PlanEntity planEntity) {
		return planDao.updatePlan(planEntity);
	}

	/**
	 * 修改状态
	 * 
	 * @param planEntity
	 * @return
	 */
	@Override
	public boolean changeState(PlanEntity planEntity) {
		return planDao.changeState(planEntity);
	}

	/**
	 * 查询出试卷的考试时间
	 * 
	 * @param paperId
	 * @param planEntity
	 * @return
	 */
	@Override
	public String queryEndTime(String paperId, PlanEntity planEntity) {
		return planDao.queryEndTime(paperId, planEntity);
	}
}
