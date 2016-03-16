package com.peach.dec.core.sys.user.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.peach.dec.core.common.dao.impl.BaseHibernateDao;
import com.peach.dec.core.sys.user.dao.UserDao;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.util.PageModel;
import com.peach.dec.util.util.StringUtil;

@Repository
@SuppressWarnings("all")
public class UserDaoImpl extends BaseHibernateDao<Object, String> implements
		UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PageModel<UserEntity> query(int pageSize, int pageNo,
			UserEntity user2) {
		PageModel<UserEntity> userPageModel = new PageModel<UserEntity>();
		List<Object> list = new ArrayList<Object>();
		UserEntity userEntity = null;
		StringBuffer hql = new StringBuffer(
				"SELECT u FROM UserEntity u WHERE 1=1");
		if (user2 != null) {
			if (StringUtil.isNotBlank(user2.getName())) {
				hql.append(" AND u.name like ?");
				list.add("%" + user2.getName() + "%");
			}

			if (StringUtil.isNotBlank(user2.getRoleName())) {
				String roleId = this.getRoleId(user2.getRoleName());
				hql.append(" AND u.roleId = ?");
				list.add(roleId);
			}
			if (StringUtil.isNotBlank(user2.getClazzName())) {

				String classId = this.getClassId(user2.getClazzName());
				hql.append(" AND u.classId = ?");
				list.add(classId);
			}
			if (user2.getState() != null) {
				hql.append(" AND u.state = ?");
				list.add(user2.getState());
			}
		}

		hql.append(" ORDER BY u.operateTime DESC");
		List<Object> userList = super.queryListObjectAllForPage(hql.toString(),
				pageSize, pageNo, list);

		List<UserEntity> list1 = new ArrayList<UserEntity>();

		if (userList != null) {

			for (Object objects : userList) {
				userEntity = (UserEntity) objects;
				String hql3 = "SELECT name FROM RoleEntity WHERE id=? AND state = 1";
				Query query1 = getSession().createQuery(hql3);
				query1.setParameter(0, userEntity.getRoleId());
				String roleName = (String) query1.uniqueResult();
				userEntity.setRoleName(roleName);

				if (userEntity.getClassId() != null
						&& !("".equals(userEntity.getClassId()))) {
					String hqls = "SELECT name FROM ClassEntity WHERE id=? AND state = 1";
					Query query = getSession().createQuery(hqls);
					query.setParameter(0, userEntity.getClassId());
					String className = (String) query.uniqueResult();
					userEntity.setClazzName(className);
				}

				list1.add(userEntity);
			}
			String hql2 = "select count(*) FROM UserEntity WHERE state=1";
			long count = super.count3(hql2, list);
			int num = (int) count;
			userPageModel.setList(list1);
			userPageModel.setPageNum(pageNo);
			userPageModel.setPageSize(pageSize);
			userPageModel.setTotalRecord(num);
		}
		return userPageModel;
	}

	/**
	 * 查询数据库所有角色
	 */
	@Override
	public List<Object> select() {
		String hql = "select name from RoleEntity where state=1 ";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	/**
	 * 查询数据库所有班级
	 */
	@Override
	public List<Object> getInfo() {
		String hql = " select name from ClassEntity where state=1 ";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	/**
	 * 新增
	 */
	@Override
	public boolean insert(UserEntity user) {
		boolean result = true;
		String id = (String) super.getSession().save(user);
		super.getSession().flush();
		if (id == null) {
			result = false;
		}
		return result;
	}

	/**
	 * 根据角色名查询对应的角色id
	 */
	@Override
	public String getClassId(String name) {
		String hql = "SELECT c.id FROM ClassEntity c WHERE c.name=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, name);
		String id = (String) query.uniqueResult();

		return id;

	}

	/**
	 * 根据班级名查询对应的班级id
	 */
	@Override
	public String getRoleId(String name) {

		String hql = "SELECT id FROM RoleEntity WHERE name=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, name);
		String id = (String) query.uniqueResult();

		return id;
	}

	/**
	 * 获取当前时间
	 */
	@Override
	public Date getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
	 * 获取当前操作者id
	 */
	@Override
	public String getUserId() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userId;
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		userId = userEntity.getId();
		return userId;
	}

	/**
	 * 修改前查询
	 */
	@Override
	public UserEntity getUserEntity(String id) {
		UserEntity user1 = null;
		String hql = "FROM UserEntity WHERE id =?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		user1 = (UserEntity) query.uniqueResult();

		String hql1 = "SELECT name FROM RoleEntity WHERE id=?";
		String roleId = user1.getRoleId();
		Query query1 = getSession().createQuery(hql1);
		query1.setParameter(0, roleId);
		String roleName = (String) query1.uniqueResult();
		user1.setRoleName(roleName);

		String hql2 = "SELECT name FROM ClassEntity WHERE id=?";
		Query query2 = getSession().createQuery(hql2);
		String classId = user1.getClassId();
		query2.setParameter(0, classId);
		String className = (String) query2.uniqueResult();
		user1.setClazzName(className);
		return user1;
	}

	/**
	 * 通过角色id得到角色名
	 */
	@Override
	public String getRoleName(String roleId) {
		String hql = "SELECT name FROM RoleEntity WHERE id =?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, roleId);
		String roleName = (String) query.uniqueResult();

		return roleName;
	}

	/**
	 * 状态修改
	 */
	@Override
	public boolean statusChange(String id, short status2) {
		boolean result = true;
		String hql = "UPDATE UserEntity SET state=? WHERE id=?";
		int number = super.executeUpdate(hql, status2, id);
		if (number != 1) {
			result = false;
		}
		return result;
	}

	/**
	 * 重置密码
	 */
	@Override
	public boolean toUserPassword(String password1, String id) {
		boolean flag = true;
		String hql = "UPDATE UserEntity SET password=? WHERE id=?";
		int number = super.executeUpdate(hql, password1, id);
		if (number != 1) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 执行修改
	 */
	@Override
	public boolean sureUpdate(UserEntity user) {
		boolean flag = true;
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE UserEntity  SET name=?,sex=?,phone=?,email=?,roleId=?,classId=?,state=?,memo=? WHERE id=?";
		int ss = session.createQuery(hql).setString(0, user.getName())
				.setShort(1, user.getSex()).setString(2, user.getPhone())
				.setString(3, user.getEmail()).setString(4, user.getRoleId())
				.setString(5, user.getClassId()).setShort(6, user.getState())
				.setString(7, user.getMemo()).setString(8, user.getId())
				.executeUpdate();
		session.flush();
		if (ss != 1) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断用户的唯一性
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public boolean isUserExist(UserEntity user) {
		boolean result = true;
		String hql = "SELECT code FROM UserEntity";
		List<String> strList = getSession().createQuery(hql).list();
		for (String code : strList) {
			if (user.getCode().equals(code)) {
				result = false;
			}
		}
		return result;
	}

	@Override
	public PageModel<UserEntity> recordsList(UserEntity user2, int pageNo,
			int pageSize, String roleId) {
		PageModel<UserEntity> pageModel = new PageModel<UserEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"SELECT u,r.name from UserEntity u,RoleEntity r where u.roleId=r.id");

		if (user2 != null) {
			if (StringUtil.isNotBlank(user2.getName())) {
				hql.append(" AND u.name LIKE ?");
				params.add("%" + user2.getName() + "%");
			}
			if (StringUtil.isNotBlank(roleId)) {
				hql.append(" AND r.id=?");
				params.add(roleId);
			}

			if (user2.getState() != null && user2.getState() != 0) {
				hql.append(" AND u.state = ?");
				params.add(user2.getState());
			}
		}
		hql.append(" ORDER BY u.id DESC");
		List<Object[]> objects = super.queryListAllPage(hql.toString(),
				pageSize, pageNo, params);
		List<UserEntity> userEntities = new ArrayList<UserEntity>();
		if (objects != null) {
			for (Object[] objects2 : objects) {
				UserEntity userEntity = (UserEntity) objects2[0];
				userEntity.setRoleName((String) objects2[1]);
				if (StringUtil.isNotBlank(userEntity.getClassId())) {
					String hqls = "SELECT name FROM ClassEntity WHERE id=?";
					Query query = getSession().createQuery(hqls);
					query.setParameter(0, userEntity.getClassId());
					String className = (String) query.uniqueResult();
					userEntity.setClazzName(className);
				}
				userEntities.add(userEntity);
			}
		}
		pageModel.setList(userEntities);
		pageModel.setPageNum(pageNo);
		pageModel.setPageSize(pageSize);
		pageModel.setTotalRecord((int) this.getTotalRecords(user2, roleId));
		return pageModel;
	}

	/**
	 * 查询出总记录数
	 * 
	 * @return
	 */
	public long getTotalRecords(UserEntity user2, String roleId) {
		long number = 0;
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"SELECT count(*) from UserEntity u,RoleEntity r where u.roleId=r.id");

		if (user2 != null) {
			if (StringUtil.isNotBlank(user2.getName())) {
				hql.append(" AND u.name LIKE ?");
				params.add("%" + user2.getName() + "%");
			}
			if (StringUtil.isNotBlank(roleId)) {
				hql.append(" AND r.id=?");
				params.add(roleId);
			}

			if (user2.getState() != null && user2.getState() != 0) {
				hql.append(" AND u.state = ?");
				params.add(user2.getState());
			}
		}
		Query query = getSession().createQuery(hql.toString());
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i, params.get(i));
		}
		number = (Long) query.uniqueResult();
		return number;
	}

	@Override
	public String findRoleName(String roleId) {
		String roleName = "";
		String hql = "SELECT name from RoleEntity where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, roleId);
		roleName = (String) query.uniqueResult();
		return roleName;
	}

}
