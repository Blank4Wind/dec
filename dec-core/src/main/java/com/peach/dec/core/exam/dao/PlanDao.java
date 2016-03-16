package com.peach.dec.core.exam.dao;

import java.util.List;

import com.peach.dec.core.exam.entity.PlanEntity;
import com.peach.dec.core.qes.entity.ExamPaperEntity;
import com.peach.dec.util.util.PageModel;

/**
 * dao：考试安排的相关操作
 * 
 * @author peach
 * @date 2015年5月24日
 */
public interface PlanDao {
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
	PageModel<PlanEntity> planList(String id, int pageNo, int pageSize,
			PlanEntity planEntity, String type, String timeout);

	/**
	 * 查询出所有的试卷
	 * 
	 * @return
	 */
	List<ExamPaperEntity> paperList();

	/**
	 * 添加考试安排
	 * 
	 * @param planEntity
	 * @return
	 */
	String insert(PlanEntity planEntity);

	/**
	 * 修改前的查询
	 * 
	 * @param planEntity
	 * @return
	 */
	PlanEntity beforeUpdate(PlanEntity planEntity);

	/**
	 * 修改考试安排
	 * 
	 * @param planEntity
	 * @return
	 */
	boolean updatePlan(PlanEntity planEntity);

	/**
	 * 修改状态
	 * 
	 * @param planEntity
	 * @return
	 */
	boolean changeState(PlanEntity planEntity);

	/**
	 * 查询出试卷的考试时间
	 * 
	 * @param paperId
	 * @param planEntity
	 * @return
	 */
	String queryEndTime(String paperId, PlanEntity planEntity);

}
