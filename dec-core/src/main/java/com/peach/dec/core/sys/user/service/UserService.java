package com.peach.dec.core.sys.user.service;

import java.util.Date;
import java.util.List;

import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.util.PageModel;

public interface UserService {

	PageModel<UserEntity> query(int pageSize, int pageNo, UserEntity user2);

	List<Object> select();

	List<Object> getInfo();

	boolean insert(UserEntity user);

	String getClassId(String name);

	String getRoleId(String name);

	public Date getCurrentTime();

	public String getUserId();

	UserEntity getUserEntity(String id);

	String getRoleName(String roleId);

	boolean statusChange(String id, short status2);

	boolean toUserPassword(String password1, String id);

	boolean sureUpdate(UserEntity user);

	/**
	 * 判断用户的唯一性
	 * 
	 * @param user
	 * @return
	 */
	boolean isUserExist(UserEntity user);

	PageModel<UserEntity> recordsList(UserEntity user2, int pageNo,
			int pageSize, String roleId);

	String findRoleName(String roleId);

}
