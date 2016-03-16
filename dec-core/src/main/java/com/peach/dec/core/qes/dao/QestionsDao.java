package com.peach.dec.core.qes.dao;

import java.util.Date;
import java.util.List;

import com.peach.dec.core.qes.entity.ExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperQueryCondition;
import com.peach.dec.core.qes.entity.QestionsEntity;
import com.peach.dec.core.qes.entity.QestionsQueryCondition;
import com.peach.dec.util.util.PageModel;

/**
 * dao：试题的相关操作
 * 
 * @author peach
 * @date 2015年5月23日
 */
@SuppressWarnings("all")
public interface QestionsDao {
	/**
	 * 查询所有的试题
	 * 
	 * @return
	 */
	// List<QestionsEntity> list(QestionsEntity qestionsEntity);
	List<QestionsEntity> list();

	/**
	 * 新增试题
	 * 
	 * @param qestionsEntity
	 * @return
	 */
	boolean insertQestion(QestionsEntity qestionsEntity);

	/**
	 * @return 修改试题——修改前查询
	 */
	QestionsEntity updateList(String id);

	/**
	 * @return 执行试题修改
	 * @throws Exception
	 */
	boolean sureUpdate(QestionsEntity qestionsEntity);

	/**
	 * 通过id改变试题的状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	boolean changeState(String id, short status);

	/**
	 * 查询所有课程
	 * 
	 * @return
	 */
	List<Object> listClazzName();

	/**
	 * 得到课程id
	 * 
	 * @return
	 */
	String getCourseId(String name);

	/**
	 * 根据id修改状态
	 */

	boolean setState(String id, short state);

	/**
	 * 试卷生成
	 */
	boolean addExamPaper(ExamPaperEntity examPaperEntity);

	/**
	 * 获取当前时间
	 */
	Date getCurrentTime();

	/**
	 * 获取当前用户id
	 */
	String getUserId();

	/**
	 * 获取试题list
	 */
	List<Object> listQestion(String hql, int pageSize, int page, List params);

	boolean detail(QestionsEntity qestionsEntity);

	PageModel<Object> getPageModel(QestionsQueryCondition queryParam);

	PageModel<Object> getQesExamEntity(QesExamPaperQueryCondition condition);
}
