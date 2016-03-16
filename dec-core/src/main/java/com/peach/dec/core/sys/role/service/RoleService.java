package com.peach.dec.core.sys.role.service;

import java.util.List;

import com.peach.dec.core.sys.role.entity.PowerEntity;
import com.peach.dec.core.sys.role.entity.RoleEntity;

/**
 * 业务接口层：角色的相关操作
 * 
 * @author peach
 * @date 2015年5月20日
 */

public interface RoleService {

	List<RoleEntity> list(RoleEntity roleEntity);

	RoleEntity roleAdd(RoleEntity roleEntity);

	RoleEntity updateList(String id);

	boolean sureUpdate(RoleEntity roleEntity);

	/**
	 * 修改状态
	 * 
	 * @param id
	 * @param status2
	 * @return
	 */
	boolean statusChange(String id, short status2);

	List<RoleEntity> getRole();

	/**
	 * 验证角色名的唯一性
	 * 
	 * @param roleEntity
	 * @return
	 */
	boolean isRoleExist(RoleEntity roleEntity);

	/**
	 * 根据roleId查询所有权限
	 */
	PowerEntity queryAllPower(String roleId);

	/**
	 * @param roleId
	 * @return
	 */
	String queryPowers(String roleId);

	/**
	 * 获取角色id
	 * 
	 * @return
	 */
	String getRoleId(String roleName);

	/**
	 * 赋权限
	 */

	void setPower(String id);

	boolean updateMGR(String ids);

}
