package com.peach.dec.core.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peach.dec.core.common.dao.CommonDao;
import com.peach.dec.core.common.service.CommonService;
import com.peach.dec.core.sys.clazz.entity.ClassEntity;
import com.peach.dec.core.sys.role.entity.PowerEntity;
import com.peach.dec.core.sys.user.entity.UserEntity;

/**
 * 业务接口实现：公共模块的相关操作
 * 
 * @author peach
 * @date 2015年5月18日
 */
@Service
public class CommonServiceImpl implements CommonService {

	@Resource
	private CommonDao commonDao;

	/**
	 * 登录
	 * 
	 * @param code
	 * @param password
	 * @return
	 */
	@Override
	public UserEntity login(String code, String password) {
		return commonDao.login(code, password);
	}

	/**
	 * 根据id修改个人资料
	 * 
	 * @param user
	 * @return
	 */
	public UserEntity updateInfo(UserEntity user) {

		return commonDao.updateInfo(user);

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
		return commonDao.isPasswdTrue(id, oldpassword);
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
		return commonDao.updatePwd(id, newpwd);
	}

	/**
	 * 根据id查询用户
	 * 
	 * @return
	 */
	@Override
	public List<UserEntity> getUser(ClassEntity classEntity) {
		return commonDao.getUser(classEntity);
	}
	/**
	 * 根据条件查询用户所属角色id
	 */
	@Override
	public String queryRoleId(String code, String password) {
		return commonDao.queryRoleId(code, password);
	}
	/**
	 * 根据roleId查询拥有的权限
	 */
	@Override
	public PowerEntity queryPower(String roleId) {
		return commonDao.queryPower(roleId);
	}

}
