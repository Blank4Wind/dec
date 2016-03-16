package com.peach.dec.core.sys.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peach.dec.core.sys.user.dao.UserDao;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.core.sys.user.service.UserService;
import com.peach.dec.util.util.PageModel;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Override
	public List<Object> select() {
		return userDao.select();
	}

	@Override
	public List<Object> getInfo() {
		return userDao.getInfo();
	}

	@Override
	public boolean insert(UserEntity user) {
		return userDao.insert(user);
	}

	@Override
	public String getClassId(String name) {
		return userDao.getClassId(name);
	}

	@Override
	public String getRoleId(String name) {
		return userDao.getRoleId(name);
	}

	@Override
	public Date getCurrentTime() {
		return userDao.getCurrentTime();
	}

	@Override
	public String getUserId() {
		return userDao.getUserId();
	}

	@Override
	public UserEntity getUserEntity(String id) {
		return userDao.getUserEntity(id);
	}

	@Override
	public String getRoleName(String roleId) {
		return userDao.getRoleName(roleId);
	}

	@Override
	public boolean statusChange(String id, short status2) {
		return userDao.statusChange(id, status2);
	}

	@Override
	public boolean toUserPassword(String password1, String id) {
		return userDao.toUserPassword(password1, id);
	}

	@Override
	public boolean sureUpdate(UserEntity user) {
		return userDao.sureUpdate(user);
	}

	@Override
	public PageModel<UserEntity> query(int pageSize, int pageNo,
			UserEntity user2) {
		if (user2 == null) {
			return userDao.query(pageSize, pageNo, new UserEntity());
		} else {
			return userDao.query(pageSize, pageNo, user2);
		}
	}

	/**
	 * 判断用户的唯一性
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public boolean isUserExist(UserEntity user) {
		return userDao.isUserExist(user);
	}

	@Override
	public PageModel<UserEntity> recordsList(UserEntity user2, int pageNo,
			int pageSize, String roleId) {
		return userDao.recordsList(user2, pageNo, pageSize, roleId);
	}

	@Override
	public String findRoleName(String roleId) {
		return userDao.findRoleName(roleId);
	}

}
