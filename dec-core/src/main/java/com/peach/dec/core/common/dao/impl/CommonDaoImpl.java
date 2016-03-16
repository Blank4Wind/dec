package com.peach.dec.core.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.peach.dec.core.common.dao.CommonDao;
import com.peach.dec.core.sys.clazz.entity.ClassEntity;
import com.peach.dec.core.sys.role.entity.PowerEntity;
import com.peach.dec.core.sys.user.entity.MenuEntity;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.util.MD5Util;

/**
 * dao接口实现：公共模块的相关操作
 * 
 * @author peach
 * @date 2015年5月18日
 */
@Repository
@SuppressWarnings("all")
public class CommonDaoImpl extends BaseHibernateDao<UserEntity, String>
		implements CommonDao {
	/**
	 * 登陆
	 * 
	 * @param password
	 * @param code
	 * @return
	 */
	@Override
	public UserEntity login(String code, String password) {
		String hql = "SELECT u,r.name FROM UserEntity u,RoleEntity r WHERE u.roleId=r.id AND u.state=1 AND r.state=1 AND u.code=? AND u.password=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, code);
		query.setParameter(1, MD5Util.toMD5(password));
		Object[] object = (Object[]) query.uniqueResult();
		UserEntity user = null;
		String roleName = null;
		if (object != null) {
			user = (UserEntity) object[0];
			roleName = (String) object[1];
			user.setRoleName(roleName);
			if (roleName.equals("学员")) {
				String hql2 = "SELECT u,c.name FROM UserEntity u,RoleEntity r,ClassEntity c WHERE u.roleId=r.id AND u.classId=c.id AND c.state=1 AND u.state=1 AND r.state=1 AND u.code=? AND u.password=?";
				Query query2 = getSession().createQuery(hql2);
				query2.setParameter(0, code);
				query2.setParameter(1, MD5Util.toMD5(password));
				Object[] objects = (Object[]) query2.uniqueResult();
				if (objects != null) {
					String className = (String) objects[1];
					user.setClazzName(className);
				} else {
					user = null;
				}
			}
		}

		return user;

	}

	/**
	 * 根据id修改个人资料
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public UserEntity updateInfo(UserEntity user) {
		String hql = "UPDATE UserEntity SET phone=?,email=? WHERE id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, user.getPhone());
		query.setParameter(1, user.getEmail());
		query.setParameter(2, user.getId());
		query.executeUpdate();
		return user;
	}

	/**
	 * 验证原密码
	 * 
	 * @param id
	 * @param oldpassword
	 * @return
	 */
	@Override
	public boolean isPasswdTrue(String id, String oldpassword) {
		boolean result = true;
		String hql = "SELECT password FROM UserEntity WHERE id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		String password = (String) query.uniqueResult();
		if (!(password.equals(MD5Util.toMD5(oldpassword)))) {
			result = false;
		}
		return result;
	}

	/**
	 * 根据id修改密码
	 * 
	 * @param id
	 * @param newpwd
	 * @return
	 */
	@Override
	public boolean updatePwd(String id, String newpwd) {
		boolean result = true;
		String hql = "UPDATE UserEntity SET password=? WHERE id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, MD5Util.toMD5(newpwd));
		query.setParameter(1, id);
		int number = query.executeUpdate();
		if (number != 1) {
			result = false;
		}
		return result;
	}

	@Override
	public List<UserEntity> getUser(ClassEntity clazz) {
		String hql = "FROM UserEntity WHERE classId=?";
		List<UserEntity> userlist = super.list(hql, clazz.getId());
		return userlist;
	}

	/**
	 * 登录时根据用户名和密码查询用户roleId
	 */
	@Override
	public String queryRoleId(String code, String password) {
		String hql = "SELECT roleId FROM UserEntity WHERE state=1 AND code=? AND password=?";
		Query query = super.getSession().createQuery(hql);
		query.setParameter(0, code);
		query.setParameter(1, MD5Util.toMD5(password).trim());
		String roleId = (String) query.uniqueResult();
		return roleId;
	}

	/**
	 * 根据用户roleId查询用户的权限
	 */
	@Override
	public PowerEntity queryPower(String roleId) {
		PowerEntity powerEntity = new PowerEntity();
		List<Object[]> listobj = new ArrayList<Object[]>();
		List<MenuEntity> baseModel = new ArrayList<MenuEntity>();
		List<MenuEntity> qesModel = new ArrayList<MenuEntity>();
		List<MenuEntity> examModel = new ArrayList<MenuEntity>();
		List<MenuEntity> assistModel = new ArrayList<MenuEntity>();
		List<MenuEntity> stuModel = new ArrayList<MenuEntity>();
		String hql = "SELECT id,pid,opreateUserId,name,url FROM MenuEntity WHERE state=1 AND isLeaf=0 AND opreateUserId=:roleId";
		listobj = super.getSession().createQuery(hql)
				.setParameter("roleId", roleId.trim()).list();
		if (listobj != null) {
			for (int i = 0; i < listobj.size(); i++) {
				MenuEntity menuEntity = new MenuEntity();
				menuEntity.setId((String) listobj.get(i)[0]);
				menuEntity.setPid((String) listobj.get(i)[1]);
				menuEntity.setOpreateUserId((String) listobj.get(i)[2]);
				menuEntity.setName((String) listobj.get(i)[3]);
				menuEntity.setUrl((String) listobj.get(i)[4]);
				if (((String) listobj.get(i)[1])
						.equals("111111111111111111111eeaeqwezbdf")) {
					baseModel.add(menuEntity);
				} else if (((String) listobj.get(i)[1])
						.equals("222222222222222222222eeaeqwezbdf")) {
					qesModel.add(menuEntity);
				} else if (((String) listobj.get(i)[1])
						.equals("333333333333333333333eeaeqwezbdf")) {
					examModel.add(menuEntity);
				} else if (((String) listobj.get(i)[1])
						.equals("444444444444444444444eeaeqwezbdf")) {
					assistModel.add(menuEntity);
				} else if (((String) listobj.get(i)[1])
						.equals("555555555555555555555eeaeqwezbdf")) {
					stuModel.add(menuEntity);
				}
			}
		}
		powerEntity.setBaseModel(baseModel);
		powerEntity.setQesModel(qesModel);
		powerEntity.setExamModel(examModel);
		powerEntity.setAssistModel(assistModel);
		powerEntity.setStuModel(stuModel);
		return powerEntity;
	}

}
