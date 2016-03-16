package com.peach.dec.core.sys.course.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peach.dec.core.sys.course.dao.CourseDao;
import com.peach.dec.core.sys.course.entity.CourseEntity;
import com.peach.dec.core.sys.course.service.CourseService;

/**
 * 业务层：课程的相关操作
 * 
 * @author peach
 * @date 2015年5月21日
 */
@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseDao courseDao;

	/**
	 * 查询出所有的课程
	 * 
	 * @return
	 */
	@Override
	public List<CourseEntity> list() {
		return courseDao.list();
	}

	/**
	 * 新增课程
	 * 
	 * @param id
	 * @param courseEntity
	 * @param parentName
	 * @return
	 */
	@Override
	public String insertCourse(String id, CourseEntity courseEntity,
			String parentName) {
		String parentId = courseDao.select(parentName);
		CourseEntity courseEntity2 = courseDao.queryIsLeaf(parentId);
		if (courseEntity2 != null) {
			short isLeaf = courseEntity2.getIsLeaf();
			if (isLeaf == 1) {
				courseDao.updateIsLeaf(parentId);
			}
		}
		return courseDao.insertCourse(id, courseEntity, parentId);
	}

	/**
	 * 修改前的查询
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CourseEntity query(String id) {
		return courseDao.query(id);
	}

	/**
	 * 通过id修改课程信息
	 * 
	 * @param courseEntity
	 * @param parentName
	 * @return
	 */
	@Override
	public int updateCourse(CourseEntity courseEntity, String parentName) {
		String parentId = courseDao.select(parentName);
		CourseEntity courseEntity2 = courseDao.queryIsLeaf(parentId);
		if (courseEntity2 != null) {
			short isLeaf = courseEntity2.getIsLeaf();
			if (isLeaf == 1) {
				courseDao.updateIsLeaf(parentId);
			}
		}
		return courseDao.updateCourse(courseEntity, parentId);
	}

	/**
	 * 点击左侧树形显示具体的课程
	 * 
	 * @return
	 */
	@Override
	public List<CourseEntity> courseData(String id) {
		return courseDao.courseData(id);
	}

	/**
	 * 修改状态
	 * 
	 * @param id
	 * @param status2
	 * @return
	 */
	@Override
	public boolean statusChange(String id, short status2) {
		return courseDao.statusChange(id, status2);
	}

	/**
	 * 显示所有的课程，无跳转
	 * 
	 * @return
	 */
	@Override
	public List<CourseEntity> show() {
		return courseDao.show();
	}

	/**
	 * 通过id过滤不要的
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<CourseEntity> findCondition(String id) {
		return courseDao.findCondition(id);
	}

	/**
	 * 判断课程名的唯一性
	 * 
	 * @param courseEntity
	 * @return
	 */
	@Override
	public boolean isCourseExist(CourseEntity courseEntity) {
		return courseDao.isCourseExist(courseEntity);
	}

	@Override
	public String select(String coureName) {
		return courseDao.select(coureName);
	}

}
