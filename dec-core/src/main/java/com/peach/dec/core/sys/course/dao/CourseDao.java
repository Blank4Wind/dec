package com.peach.dec.core.sys.course.dao;

import java.util.List;

import com.peach.dec.core.sys.course.entity.CourseEntity;

/**
 * dao接口层：课程的相关操作
 * 
 * @author peach
 * @date 2015年5月21日
 */
public interface CourseDao {

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
	 * 通过id查询对应的叶子节点
	 * 
	 * @param courseId
	 * @return
	 */
	CourseEntity queryIsLeaf(String courseId);

	/**
	 * 修改叶子节点
	 * 
	 * @param courseId
	 */
	void updateIsLeaf(String courseId);

	/**
	 * 通过id修改课程信息
	 * 
	 * @param courseEntity
	 * @param parentId
	 * @return
	 */
	int updateCourse(CourseEntity courseEntity, String parentId);

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
	 * 根据姓名查询出id
	 * 
	 * @param parentName
	 * @return
	 */
	String select(String parentName);

	/**
	 * 通过id过滤不要的
	 * 
	 * @param id
	 * @return
	 */
	List<CourseEntity> findCondition(String id);

	/**
	 * 判断课程名的唯一性
	 * 
	 * @param courseEntity
	 * @return
	 */
	boolean isCourseExist(CourseEntity courseEntity);

}
