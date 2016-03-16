package com.peach.dec.core.sys.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peach.dec.core.sys.role.dao.RoleDao;
import com.peach.dec.core.sys.role.entity.PowerEntity;
import com.peach.dec.core.sys.role.entity.RoleEntity;
import com.peach.dec.core.sys.role.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	/*
	 * 角色查询
	 */
	@Override
	public List<RoleEntity> list(RoleEntity roleEntity) {
		return roleDao.list(roleEntity);
	}

	/*
	 * 角色增加
	 */
	@Override
	public RoleEntity roleAdd(RoleEntity roleEntity) {
		return roleDao.roleAdd(roleEntity);
	}

	/*
	 * 角色修改前的查询
	 */

	@Override
	public RoleEntity updateList(String id) {

		return roleDao.updateList(id);
	}

	/*
	 * 执行修改
	 */
	@Override
	public boolean sureUpdate(RoleEntity roleEntity) {
		return roleDao.sureUpdate(roleEntity);
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
		return roleDao.statusChange(id, status2);
	}

	@Override
	public List<RoleEntity> getRole() {
		return roleDao.getRole();
	}

	/**
	 * 验证角色名的唯一性
	 * 
	 * @param roleEntity
	 * @return
	 */
	@Override
	public boolean isRoleExist(RoleEntity roleEntity) {
		return roleDao.isRoleExist(roleEntity);
	}

	/**
	 * 查询所有权限
	 */
	@Override
	public PowerEntity queryAllPower(String roleId) {
		return roleDao.queryAllPower(roleId);
	}
	
	@Override
	public String queryPowers(String roleId) {
		return roleDao.queryPowers(roleId);
	}

	@Override
	public boolean updateMGR(String ids) {
		return roleDao.updateMGR(ids);
	}
	

	@Override
	public String getRoleId(String roleName) {
		return roleDao.getRoleId(roleName);
	}

	@Override
	public void setPower(String id) {
		roleDao.setPower(id);
	}
}
