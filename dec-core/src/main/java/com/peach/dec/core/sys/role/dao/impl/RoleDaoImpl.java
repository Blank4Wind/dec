package com.peach.dec.core.sys.role.dao.impl;

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

import com.alibaba.fastjson.JSON;
import com.peach.dec.core.common.dao.impl.BaseHibernateDao;
import com.peach.dec.core.sys.role.dao.RoleDao;
import com.peach.dec.core.sys.role.entity.PowerEntity;
import com.peach.dec.core.sys.role.entity.PowerParam;
import com.peach.dec.core.sys.role.entity.RoleEntity;
import com.peach.dec.core.sys.user.entity.MenuEntity;
import com.peach.dec.util.util.StringUtil;
import com.peach.dec.core.sys.user.entity.UserEntity;

@Repository
@SuppressWarnings("all")
public class RoleDaoImpl extends BaseHibernateDao<RoleEntity, String> implements
		RoleDao {
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * 查询角色
	 */
	@Override
	public List<RoleEntity> list(RoleEntity roleEntity) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"SELECT r.id,r.name,r.operateTime,r.state,r.memo,u.name from RoleEntity r,UserEntity u where r.operateUserId=u.id");
		if (roleEntity != null) {
			if (roleEntity.getState() != null) {
				hql.append(" AND r.state=? ");
				params.add(roleEntity.getState());
			}
		}
		hql.append(" order by r.operateTime desc");
		Query query = getSession().createQuery(hql.toString());
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i, params.get(i));
		}

		List<Object[]> objList = query.list();
		List<RoleEntity> roleList = new ArrayList<RoleEntity>();
		if (objList != null) {
			for (Object[] objs : objList) {
				RoleEntity role = new RoleEntity();
				role.setId((String) objs[0]);
				role.setName((String) objs[1]);
				role.setOperateTime((Date) objs[2]);
				role.setState((Short) objs[3]);
				role.setMemo((String) objs[4]);
				role.setUserName((String) objs[5]);
				role.setPowerEntity(this.queryAllPower((String)objs[0]));
				roleList.add(role);
			}
		}
		return roleList;

	}

	/*
	 * 新增角色
	 */
	@Override
	public RoleEntity roleAdd(RoleEntity roleEntity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(roleEntity);
		session.flush();
		return roleEntity;
	}

	/*
	 * 修改前的查询
	 */
	// @Override
	public RoleEntity updateList(String id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM RoleEntity WHERE id=?";
		RoleEntity roleEntity = (RoleEntity) session.createQuery(hql)
				.setString(0, id).uniqueResult();

		return roleEntity;

	}

	/*
	 * 执行修改
	 */
	@Override
	public boolean sureUpdate(RoleEntity roleEntity) {
		boolean result = true;
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE RoleEntity  SET name=?,operateUserId=?,operateTime=?,state=?,memo=? WHERE id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, roleEntity.getName());
		query.setParameter(1, roleEntity.getOperateUserId());
		query.setParameter(2, roleEntity.getOperateTime());
		query.setParameter(3, roleEntity.getState());
		query.setParameter(4, roleEntity.getMemo());
		query.setParameter(5, roleEntity.getId());
		int number = query.executeUpdate();
		if (number != 1) {
			result = false;
		}
		return result;
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
		String hql = "UPDATE RoleEntity SET state=? WHERE id=?";
		int number = super.executeUpdate(hql, status2, id);
		if (number != 1) {
			result = false;
		}
		return result;
	}

	/**
	 * 站内查询 查询全部角色
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleEntity> getRole() {
		String hql = "FROM RoleEntity";
		return super.getSession().createQuery(hql).list();
	}

	/**
	 * 验证角色名是否存在
	 * 
	 * @param roleEntity
	 * @return
	 */
	@Override
	public boolean isRoleExist(RoleEntity roleEntity) {
		boolean result = true;
		String hql = "SELECT name FROM RoleEntity";
		List<String> strList = getSession().createQuery(hql).list();
		for (String name : strList) {
			if (roleEntity.getName().equals(name)) {
				result = false;
			}
		}
		return result;
	}
	
	/**
	 * 查询所有权限
	 */
	@Override
	public PowerEntity queryAllPower(String roleId) {
		PowerEntity powerEntity = new PowerEntity();
		List<Object[]> listobj = new ArrayList<Object[]>();
		List<MenuEntity> baseModel = new ArrayList<MenuEntity>();
		List<MenuEntity> qesModel = new ArrayList<MenuEntity>();
		List<MenuEntity> examModel = new ArrayList<MenuEntity>();
		List<MenuEntity> assistModel = new ArrayList<MenuEntity>();
		List<MenuEntity> stuModel = new ArrayList<MenuEntity>();
		String hql = "SELECT id,pid,opreateUserId,name,url FROM MenuEntity WHERE isLeaf=0 AND opreateUserId=:roleId";
		listobj = super.getSession().createQuery(hql).setParameter("roleId", roleId.trim()).list();
		for(int i=0;i<listobj.size();i++){
			MenuEntity menuEntity = new MenuEntity();
			menuEntity.setId((String)listobj.get(i)[0]);
			menuEntity.setPid((String)listobj.get(i)[1]);
			menuEntity.setOpreateUserId((String)listobj.get(i)[2]);
			menuEntity.setName((String)listobj.get(i)[3]);
			menuEntity.setUrl((String)listobj.get(i)[4]);
			if(((String)listobj.get(i)[1]).equals("111111111111111111111eeaeqwezbdf")){
				baseModel.add(menuEntity);
			}else if(((String)listobj.get(i)[1]).equals("222222222222222222222eeaeqwezbdf")){
				qesModel.add(menuEntity);
			}else if(((String)listobj.get(i)[1]).equals("333333333333333333333eeaeqwezbdf")){
				examModel.add(menuEntity);
			}else if(((String)listobj.get(i)[1]).equals("444444444444444444444eeaeqwezbdf")){
				assistModel.add(menuEntity);
			}else if(((String)listobj.get(i)[1]).equals("555555555555555555555eeaeqwezbdf")){
				stuModel.add(menuEntity);
			}
		}
		powerEntity.setRoleId(roleId);
		powerEntity.setBaseModel(baseModel);
		powerEntity.setQesModel(qesModel);
		powerEntity.setExamModel(examModel);
		powerEntity.setAssistModel(assistModel);
		powerEntity.setStuModel(stuModel);
		return powerEntity;
	}
	
	/**
	 * 根据id查询用户所有权限
	 */
	@Override
	public String queryPowers(String opreateUserId){
		String hql = "SELECT id,pid,name,state FROM MenuEntity WHERE opreateUserId=:opreateUserId AND isLeaf=0 OR isLeaf=1";
		List<PowerParam> plist = new ArrayList<PowerParam>();
		List<Object[]> list = new ArrayList<Object[]>();
		list = super.getSession().createQuery(hql).setParameter("opreateUserId", opreateUserId).list();
		for(int i=0; i<list.size();i++){
			PowerParam param = new PowerParam();
			param.setId(list.get(i)[0].toString().trim());
			param.setPid(list.get(i)[1].toString().trim());
			param.setName(list.get(i)[2].toString().trim());
			if((Short)list.get(i)[3]==1&&!((param.getPid().trim())).equals("0")){
				param.setChecked(true);
			}else{
				param.setChecked(false);
			}
			if(list.get(i)[1].toString().trim().equals("0")){
				param.setOpen(true);
			}else{
				param.setOpen(false);
			}
			plist.add(param);
		}
		String treeCode = JSON.toJSONString(plist);
		
		return treeCode;
	}

	@Override
	public boolean updateMGR(String ids) {
		boolean result = true;
		String[] stus = ids.split(",");
		String hql1="UPDATE MenuEntity SET state=2  WHERE opreateUserId=? AND isLeaf=0";
		Query query1 = this.getSession().createQuery(hql1);
		query1.setParameter(0, stus[stus.length-1]);
		int num2 = query1.executeUpdate();
		if (num2 == 0) {
			result = false;
		}
		if(stus.length>1){
		String hql="UPDATE MenuEntity SET state=1  WHERE isLeaf=0 AND id IN (:args)";
		Query query = this.getSession().createQuery(hql);
//		query.setParameter(0, stus[stus.length-1]);
//		String[] b = new String[stus.length -1];
//    	System.arraycopy(stus, 0, b, 0, stus.length-1);
//    	stus= b;
		query.setParameterList("args", stus);
		int num = query.executeUpdate();
		if (num == 0) {
			result = false;
		}
		}
		return result;
	}

	@Override
	public String getRoleId(String roleName) {
		String hql = "SELECT id FROM RoleEntity WHERE name=:name";
		String roleId = (String) super.getSession().createQuery(hql).setParameter("name", roleName).uniqueResult();
		return roleId;
	}

	@Override
	public void setPower(String id) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		List<Object[]> list = new ArrayList<Object[]>();
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		String userId = userEntity.getId();
		String hql2 = "select roleId from UserEntity where id=:id";
		String hql = "SELECT id,pid,isLeaf,name,url,opreateUserId,operateTime,state,memo FROM MenuEntity WHERE opreateUserId=:opreateUserId AND isLeaf=0 ";
		String roleId = (String) super.getSession().createQuery(hql2).setParameter("id", userId).uniqueResult();
		list = super.getSession().createQuery(hql).setParameter("opreateUserId", roleId).list();
		
		for(int i=0;i<list.size();i++){
			MenuEntity entity = new MenuEntity();
			entity.setIsLeaf((Short) list.get(i)[2]);
			entity.setMemo(null);
			entity.setName((String)list.get(i)[3]);
			entity.setOperateTime(new Date());
			entity.setOpreateUserId(id);
			entity.setPid((String)list.get(i)[1]);
			entity.setState((short)2);
			entity.setUrl((String)list.get(i)[4]);
			super.getSession().save(entity);
			super.getSession().flush();
		}
	}

}
