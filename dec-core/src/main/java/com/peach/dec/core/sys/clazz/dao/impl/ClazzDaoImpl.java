package com.peach.dec.core.sys.clazz.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.peach.dec.core.common.dao.impl.BaseHibernateDao;
import com.peach.dec.core.sys.clazz.dao.ClazzDao;
import com.peach.dec.core.sys.clazz.entity.ClassEntity;
import com.peach.dec.core.sys.course.entity.CourseEntity;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.constant.Constant;
import com.peach.dec.util.util.PageModel;
import com.peach.dec.util.util.StringUtil;

@Repository
@SuppressWarnings("all")
public class ClazzDaoImpl extends BaseHibernateDao<Object, String> implements
		ClazzDao {

	private PageModel<Object> pageModel;

	/**
	 * 得到课程名称
	 */
	@Override
	public List<Object> listClazzName() {
		String id = this.getCourseId("全部课程");
		String hql = "SELECT name FROM CourseEntity WHERE PID='" + id + "'";
		List<Object> listClazzName = super.list(hql, null);
		return listClazzName;
	}

	/**
	 * 获取班级list
	 */
	@Override
	public List<Object> listClazz(String hql, int pageSize, int page,
			List params) {
		List<Object> listClazz = super.queryListObjectAllForPage(hql, pageSize,
				page, params);
		return listClazz;
	}

	/**
	 * 添加班级
	 */
	@Override
	public boolean addClass(ClassEntity classEntity) {
		boolean result = true;
		String id = (String) super.getSession().save(classEntity);
		super.getSession().flush();
		if (id == null) {
			result = false;
		}
		return result;
	}

	/**
	 * 得到课程id
	 */
	public String getCourseId(String name) {
		String hql = "SELECT new CourseEntity(id )FROM CourseEntity WHERE name=:name";
		List<CourseEntity> listCourseEntity = super.getSession()
				.createQuery(hql).setParameter("name", name).list();
		String id = listCourseEntity.get(0).getId();
		return id;
	}

	/**
	 * 获取当前userID
	 */
	public String getUserId() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userId;
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		userId = userEntity.getId();
		return userId;
	}

	/**
	 * 获取当前时间
	 */
	@Override
	public Date getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			date = new Date();
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 得到班级list
	 */
	@Override
	public ClassEntity getClassResult(String id) {
		ClassEntity classEntity = new ClassEntity();
		String hql = "SELECT cl.id,cl.name,cl.operateTime,co.id,co.name,u.id,u.name,cl.state,cl.memo FROM ClassEntity cl,CourseEntity co,UserEntity u WHERE cl.courseId=co.id AND cl.operateUserId=u.id AND cl.id=:id";
		Query q = super.getSession().createQuery(hql).setParameter("id", id);
		Object[] listObject = (Object[]) q.uniqueResult();

		if (listObject.length == 9) {
			classEntity.setId((String) listObject[0]);
			classEntity.setName((String) listObject[1]);
			classEntity.setOperateTime((Date) listObject[2]);
			classEntity.setCourseId((String) listObject[3]);
			classEntity.setCourseName((String) listObject[4]);
			classEntity.setOperateUserId((String) listObject[5]);
			classEntity.setUserName((String) listObject[6]);
			classEntity.setState((Short) listObject[7]);
			classEntity.setMemo((String) listObject[8]);
		}
		return classEntity;
	}

	/**
	 * 分页
	 */
	@Override
	public PageModel<Object> getPageModel(QueryParam queryParam) {
		pageModel = new PageModel<Object>();
		StringBuffer str = new StringBuffer(" FROM ClassEntity WHERE 1=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		if (StringUtil.isNotBlank(queryParam.getName())) {
			str.append(" AND name like '%" + queryParam.getName() + "%'");
		}
		if (!("".equals(queryParam.getStartTime()) || queryParam.getStartTime() == null)) {
			str.append(" AND operateTime > ? ");
			params.add(queryParam.getStartTime());
		}
		if (!("".equals(queryParam.getEndTime()) || queryParam.getEndTime() == null)) {
			str.append(" AND operateTime < ? ");
			params.add(queryParam.getEndTime());
		}
		if (queryParam.getState() != null) {
			str.append(" AND state = ? ");
			params.add(queryParam.getState());
		}
		str.append("ORDER BY operateTime DESC");
		List<Object> list = super.queryListObjectAllForPage(str.toString(),
				queryParam.getPageSize(), queryParam.getPage(), params);
		String hql2 = "select count(*) " + str;
		long num = super.count2(hql2, params);
		int count = (int) num;

		pageModel.setList(list);
		pageModel.setTotalRecord(count);
		pageModel.setPageNum(queryParam.getPage());
		pageModel.setPageSize(Constant.PAGE_SIZE);
		return pageModel;
	}

	/**
	 * 修改状态
	 */
	@Override
	public boolean setState(String id, short state) {
		boolean result = false;
		String hql = "UPDATE ClassEntity SET state=? WHERE id=? ";
		int a = super.executeUpdate(hql, state, id);
		if (a == 1) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * 修改资料
	 */
	@Override
	public boolean updateClassEntity(ClassEntity classEntity) {
		super.getSession().update(classEntity);
		super.getSession().flush();
		return true;
	}

	/**
	 * 查询出所有的班级
	 */
	@Override
	public List<ClassEntity> classlist() {
		String hql = "FROM ClassEntity";
		List<ClassEntity> classlist = getSession().createQuery(hql).list();
		return classlist;
	}

	/**
	 * 检查班级名是否重复
	 */
	@Override
	public boolean checkAccount(String name) {
		String hql = "FROM ClassEntity WHERE name=:name";
		List result = new ArrayList();
		result = super.getSession().createQuery(hql).setParameter("name", name)
				.list();
		if (result == null || 0 == result.size()) {
			return true;
		} else {
			return false;
		}
	}

}
