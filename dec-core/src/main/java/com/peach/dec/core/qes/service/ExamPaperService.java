package com.peach.dec.core.qes.service;

import java.util.Date;
import java.util.List;

import com.peach.dec.core.qes.entity.ExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperQueryCondition;
import com.peach.dec.util.util.PageModel;

/**
 * @author guosong
 * @version 2015年5月28日 上午10:20:362015年5月28日
 * @explain 业务接口：试卷的相关操作
 */

public interface ExamPaperService {
	/**
	 * 试卷分页查询
	 * 
	 * @return
	 */
	PageModel<Object> getQesExamEntity(QesExamPaperQueryCondition condition);

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
	void addExamPaper(ExamPaperEntity examPaperEntity);

	/**
	 * 获取当前时间
	 */
	Date getCurrentTime();

	/**
	 * 获取当前用户id
	 */
	String getUserId();

	/**
	 * 试卷预览
	 * 得到试题
	 */
	List<Object> getQesExamPaperEntity(String id);

	/**
	 * 开始考试
	 * 
	 * @param id
	 * @return
	 */
	QesExamPaperEntity exam(String id);

	/**
	 * 试卷预览
	 * 得到试题
	 * 重新生成试卷
	 */
	void reGetQesExamPaperEntity(String id);

	Long calcSingleNumber(ExamPaperEntity examPaperEntity);

	Long calcMultiNumber(ExamPaperEntity examPaperEntity);

	Long calcJudgeNumber(ExamPaperEntity examPaperEntity);

}
