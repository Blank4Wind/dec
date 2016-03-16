package com.peach.dec.core.common.service;

import java.util.List;

import com.peach.dec.core.sys.clazz.entity.ClassEntity;
import com.peach.dec.core.sys.role.entity.PowerEntity;
import com.peach.dec.core.sys.user.entity.UserEntity;

/**
 * 业务接口：公共模块的相关操作
 * 
 * @author peach
 * @date 2015年5月18日
 */
public interface CommonService {

	/**
	 * 登录
	 * 
	 * @param code
	 * @param password
	 * @return
	 */
	UserEntity login(String code, String password);

	/**
	 * 根据id修改个人资料
	 * 
	 * @param user
	 */

	UserEntity updateInfo(UserEntity user);

	/**
	 * 验证原密码
	 * 
	 * @param id
	 * @param oldpassword
	 * @return
	 */
	boolean isPasswdTrue(String id, String oldpassword);

	/**
	 * 根据id修改密码
	 * 
	 * @param id
	 * @param newpwd
	 * @return
	 */
	boolean updatePwd(String id, String newpwd);

	/**
	 * 根据班级id查询用户
	 * 
	 * @param classEntity
	 * @return
	 */
	List<UserEntity> getUser(ClassEntity classEntity);
	
	/**
	 * 登录时根据用户名和密码查询用户roleId
	 */
	String queryRoleId(String code,String password);
	
	/**
	 * 根据roleId查询用户拥有权限
	 */
	PowerEntity queryPower(String roleId);
	
}
