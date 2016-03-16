package com.peach.dec.core.qes.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peach.dec.core.qes.dao.ExamPaperDao;
import com.peach.dec.core.qes.entity.ExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperQueryCondition;
import com.peach.dec.core.qes.service.ExamPaperService;
import com.peach.dec.util.util.PageModel;

/**
 * @author guosong
 * @version 2015年5月28日 上午10:21:122015年5月28日
 * @explain 业务层：试卷的相关操作
 */
@Service
public class ExamPaperServiceImpl implements ExamPaperService {
	@Resource
	private ExamPaperDao examPaperDao;

	/**
	 * 试卷分页查询
	 * 
	 * @return
	 */
	@Override
	public PageModel<Object> getQesExamEntity(
			QesExamPaperQueryCondition condition) {
		return examPaperDao.getQesExamEntity(condition);
	}

	/**
	 * 得到课程列表
	 * 
	 * @return
	 */
	@Override
	public List<Object> listClazzName() {
		return examPaperDao.listClazzName();
	}

	/**
	 * 得到课程id
	 * 
	 * @return
	 */
	@Override
	public String getCourseId(String name) {
		return examPaperDao.getCourseId(name);
	}

	/**
	 * 根据id修改试卷状态
	 */
	@Override
	public boolean setState(String id, short state) {
		return examPaperDao.setState(id, state);
	}

	/**
	 * 添加试卷
	 */
	@Override
	public void addExamPaper(ExamPaperEntity examPaperEntity) {
		examPaperDao.addExamPaper(examPaperEntity);
	}

	/**
	 * 获取当前时间
	 */
	@Override
	public Date getCurrentTime() {
		return examPaperDao.getCurrentTime();
	}

	/**
	 * 获取当前用户id
	 */
	@Override
	public String getUserId() {
		return examPaperDao.getUserId();
	}

	/**
	 * 得到试卷
	 */
	@Override
	public List<Object> getQesExamPaperEntity(String id) {
		return examPaperDao.getQesExamPaperEntity(id);
	}

	/**
	 * 开始考试
	 */
	@Override
	public QesExamPaperEntity exam(String id) {
		return examPaperDao.exam(id);
	}

	@Override
	public void reGetQesExamPaperEntity(String id) {
		examPaperDao.reGetQesExamPaperEntity(id);
	}

	@Override
	public Long calcSingleNumber(ExamPaperEntity examPaperEntity) {
		return examPaperDao.calcSingleNumber(examPaperEntity);
	}

	@Override
	public Long calcMultiNumber(ExamPaperEntity examPaperEntity) {
		return examPaperDao.calcMultiNumber(examPaperEntity);
	}

	@Override
	public Long calcJudgeNumber(ExamPaperEntity examPaperEntity) {
		return examPaperDao.calcJudgeNumber(examPaperEntity);
	}
}
