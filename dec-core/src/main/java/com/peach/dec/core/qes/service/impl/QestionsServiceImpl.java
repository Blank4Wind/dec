package com.peach.dec.core.qes.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peach.dec.core.qes.dao.QestionsDao;
import com.peach.dec.core.qes.entity.ExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperQueryCondition;
import com.peach.dec.core.qes.entity.QestionsEntity;
import com.peach.dec.core.qes.entity.QestionsQueryCondition;
import com.peach.dec.core.qes.service.QestionsService;
import com.peach.dec.util.util.PageModel;

/**
 * 业务层：试题的相关操作
 * 
 * @author peach
 * @date 2015年5月23日
 */
@Service
@SuppressWarnings("all")
public class QestionsServiceImpl implements QestionsService {
	@Resource
	private QestionsDao qestionsDao;

	/**
	 * 查询所有的试题
	 * 
	 * @return
	 */
	@Override
	// public List<QestionsEntity> list(QestionsEntity qestionsEntity) {
	public List<QestionsEntity> list() {
		return qestionsDao.list();
	}

	/**
	 * 新增试题
	 * 
	 * @param qestionsEntity
	 * @return
	 */
	@Override
	public boolean insertQestion(QestionsEntity qestionsEntity) {
		return qestionsDao.insertQestion(qestionsEntity);
	}

	/*
	 * 试题修改前的查询
	 */

	@Override
	public QestionsEntity updateList(String id) {

		return qestionsDao.updateList(id);
	}

	/*
	 * 执行修改
	 */
	@Override
	public boolean sureUpdate(QestionsEntity qestionsEntity) {
		return qestionsDao.sureUpdate(qestionsEntity);
	}

	/**
	 * 通过id改变试题的状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@Override
	public boolean changeState(String id, short status) {
		return qestionsDao.changeState(id, status);
	}

	/**
	 * 得到课程列表
	 * 
	 * @return
	 */
	@Override
	public List<Object> listClazzName() {
		return qestionsDao.listClazzName();
	}

	/**
	 * 得到课程id
	 * 
	 * @return
	 */
	@Override
	public String getCourseId(String name) {
		return qestionsDao.getCourseId(name);
	}

	/**
	 * 根据id修改试卷状态
	 */
	@Override
	public boolean setState(String id, short state) {
		return qestionsDao.setState(id, state);
	}

	@Override
	public boolean addExamPaper(ExamPaperEntity examPaperEntity) {
		return qestionsDao.addExamPaper(examPaperEntity);
	}

	@Override
	public Date getCurrentTime() {
		return qestionsDao.getCurrentTime();
	}

	@Override
	public String getUserId() {
		return qestionsDao.getUserId();
	}

	@Override
	public boolean detail(QestionsEntity qestionsEntity) {
		return qestionsDao.detail(qestionsEntity);
	}

	/**
	 * 获取试题list
	 */
	@Override
	public List<Object> listQestion(String hql, int pageSize, int page,
			List params) {
		return qestionsDao.listQestion(hql, pageSize, page, params);
	}

	@Override
	public PageModel<Object> getPageModel(QestionsQueryCondition queryParam) {
		return qestionsDao.getPageModel(queryParam);
	}

	@Override
	public PageModel<Object> getQesExamEntity(
			QesExamPaperQueryCondition condition) {
		return qestionsDao.getQesExamEntity(condition);

	}

}
