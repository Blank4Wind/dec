package com.peach.dec.core.sys.course.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.net.aso.q;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.peach.dec.core.common.dao.impl.BaseHibernateDao;
import com.peach.dec.core.sys.course.dao.CourseDao;
import com.peach.dec.core.sys.course.entity.CourseEntity;

/**
 * daop层：课程的相关操作
 * 
 * @author peach
 * @date 2015年5月21日
 */
@Repository
@SuppressWarnings("all")
public class CourseDaoImpl extends BaseHibernateDao<CourseEntity, String>
		implements CourseDao {
	/**
	 * 查询出所有的课程
	 * 
	 * @return
	 */
	@Override
	public List<CourseEntity> list() {

		String hql = "FROM CourseEntity WHERE state=1";
		List<CourseEntity> courseEntities = getSession().createQuery(hql)
				.list();
		for (CourseEntity courseEntity : courseEntities) {
			courseEntity.setTarget("dataShow");
			courseEntity.setUrl("sys/course_courseData.action?id="
					+ courseEntity.getId());
		}
		return courseEntities;
	}

	/**
	 * 新增课程
	 * 
	 * @param id
	 * @param courseEntity
	 * @return
	 */
	@Override
	public String insertCourse(String id, CourseEntity courseEntity,
			String parentId) {
		String name = courseEntity.getName();
		String memo = courseEntity.getMemo();

		CourseEntity courseEntity2 = new CourseEntity();
		courseEntity2.setName(name);
		courseEntity2.setPid(parentId);
		courseEntity2.setIsLeaf((short) 2);
		courseEntity2.setOperateUserId(id);
		courseEntity2.setOperateTime(new Date());
		courseEntity2.setState((short) 1);
		courseEntity2.setMemo(memo);
		String number = (String) super.save(courseEntity2);
		getSession().flush();
		return number;
	}

	/**
	 * 修改前的查询
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CourseEntity query(String id) {
		String hql = "SELECT c,o.name FROM CourseEntity c,CourseEntity o WHERE c.pid=o.id AND c.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		Object[] objects = (Object[]) query.uniqueResult();
		CourseEntity courseEntity = null;
		if (objects != null) {
			courseEntity = (CourseEntity) objects[0];
			String parentName = (String) objects[1];
			courseEntity.setParentName(parentName);
		}
		return courseEntity;
	}

	/**
	 * 通过id查询对应的叶子节点
	 * 
	 * @param courseId
	 * @return
	 */
	@Override
	public CourseEntity queryIsLeaf(String courseId) {
		CourseEntity courseEntity = (CourseEntity) getSession().get(
				CourseEntity.class, courseId);
		return courseEntity;
	}

	/**
	 * 修改叶子节点
	 * 
	 * @param courseId
	 */
	@Override
	public void updateIsLeaf(String courseId) {
		String hql = "UPDATE CourseEntity SET isLeaf=2 WHERE id=?";
		int number = super.executeUpdate(hql, courseId);
	}

	/**
	 * 通过id修改课程信息
	 * 
	 * @param courseEntity
	 * @return
	 */
	@Override
	public int updateCourse(CourseEntity courseEntity, String parentId) {
		String hql = "UPDATE CourseEntity SET name=?,pid=?,operateUserId=?,operateTime=?,state=?,memo=? WHERE id=?";
		return super.executeUpdate(hql, courseEntity.getName(), parentId,
				courseEntity.getOperateUserId(), courseEntity.getOperateTime(),
				courseEntity.getState(), courseEntity.getMemo(),
				courseEntity.getId());
	}

	/**
	 * 点击左侧树形显示具体的课程
	 * 
	 * @return
	 */
	@Override
	public List<CourseEntity> courseData(String id) {
		String hql = "SELECT c,o.name,u.name FROM CourseEntity c,CourseEntity o,UserEntity u WHERE c.pid=o.id AND c.operateUserId=u.id AND (c.pid = ? OR c.id = ?) AND c.state = 1";

		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		query.setParameter(1, id);
		List<Object[]> object = query.list();
		List<CourseEntity> courseEntities = new ArrayList<CourseEntity>();
		CourseEntity courseEntity = new CourseEntity();
		if (object != null) {
			for (Object[] objects : object) {
				courseEntity = (CourseEntity) objects[0];
				String parentName = (String) objects[1];
				String userName = (String) objects[2];
				courseEntity.setParentName(parentName);
				courseEntity.setUserName(userName);
				courseEntities.add(courseEntity);
			}
		}
		return courseEntities;
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
		boolean result = true;
		String hql = "UPDATE CourseEntity SET state=? WHERE id=?";
		int number = super.executeUpdate(hql, status2, id);
		if (number != 1) {
			result = false;
		}
		return result;
	}

	/**
	 * 显示所有的课程，无跳转
	 * 
	 * @return
	 */
	@Override
	public List<CourseEntity> show() {
		String hql = "FROM CourseEntity WHERE state=1";
		List<CourseEntity> courseEntities = super.list(hql, null);
		return courseEntities;
	}

	/**
	 * 根据姓名查询出id
	 * 
	 * @param parentName
	 * @return
	 */
	@Override
	public String select(String parentName) {
		String hql = "SELECT id FROM CourseEntity WHERE name=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, parentName);
		String parentId = (String) query.uniqueResult();
		return parentId;
	}

	/**
	 * 通过id过滤
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<CourseEntity> findCondition(String id) {
		String hql = "FROM CourseEntity WHERE id<>? AND pid<>?";
		List<CourseEntity> courseList = super.list(hql, id, id);
		return courseList;
	}

	/**
	 * 判断课程名的唯一性
	 * 
	 * @param courseEntity
	 * @return
	 */
	@Override
	public boolean isCourseExist(CourseEntity courseEntity) {
		boolean result = true;
		String hql = "SELECT name FROM CourseEntity";
		List<String> strList = getSession().createQuery(hql).list();
		for (String string : strList) {
			if (courseEntity.getName().equals(string)) {
				result = false;
			}
		}
		return result;
	}
}
