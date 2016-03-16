package com.peach.dec.core.sys.course.service;

import java.util.List;

import com.peach.dec.core.sys.course.entity.CourseEntity;

/**
 * 业务接口：课程的相关操作
 * 
 * @author peach
 * @date 2015年5月21日
 */
public interface CourseService {

	/**
	 * 查询出所有的课程
	 * 
	 * @return
	 */
	List<CourseEntity> list();

	/**
	 * 新增课程
	 * 
	 * @param id
	 * @param courseEntity
	 * @param parentName
	 * @return
	 */
	String insertCourse(String id, CourseEntity courseEntity, String parentName);

	/**
	 * 修改前的查询
	 * 
	 * @param id
	 * @return
	 */
	CourseEntity query(String id);

	/**
	 * 通过id修改课程信息
	 * 
	 * @param courseEntity
	 * @param parentName
	 * @return
	 */
	int updateCourse(CourseEntity courseEntity, String parentName);

	/**
	 * 点击左侧树形显示具体的课程
	 * 
	 * @return
	 */
	List<CourseEntity> courseData(String id);

	/**
	 * 修改状态
	 * 
	 * @param id
	 * @param status2
	 * @return
	 */
	boolean statusChange(String id, short status2);

	/**
	 * 显示所有的课程，无跳转
	 * 
	 * @return
	 */
	List<CourseEntity> show();

	/**
	 * 通过id过滤不要的
	 * 
	 * @param id
	 * @return
	 */
	List<CourseEntity> findCondition(String id);
	
	/**
	 * 根据课程名查询出id
	 * @param coureName
	 * @return
	 */
	String select(String coureName);

	/**
	 * 判断课程名的唯一性
	 * 
	 * @param courseEntity
	 * @return
	 */
	boolean isCourseExist(CourseEntity courseEntity);

}
