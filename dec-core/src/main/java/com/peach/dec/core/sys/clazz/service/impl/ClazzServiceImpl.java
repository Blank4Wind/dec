package com.peach.dec.core.sys.clazz.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peach.dec.core.sys.clazz.dao.ClazzDao;
import com.peach.dec.core.sys.clazz.dao.impl.QueryParam;
import com.peach.dec.core.sys.clazz.entity.ClassEntity;
import com.peach.dec.core.sys.clazz.service.ClazzService;
import com.peach.dec.util.util.PageModel;

@Service
@SuppressWarnings("all")
public class ClazzServiceImpl implements ClazzService {

	@Resource
	private ClazzDao clazzDao;

	@Override
	public List<Object> listClazzName() {
		return clazzDao.listClazzName();
	}

	@Override
	public boolean addClass(ClassEntity classEntity) {
		return clazzDao.addClass(classEntity);
	}

	@Override
	public String getCourseId(String name) {
		return clazzDao.getCourseId(name);
	}

	@Override
	public String getUserId() {
		return clazzDao.getUserId();
	}

	@Override
	public Date getCurrentTime() {
		return clazzDao.getCurrentTime();
	}

	@Override
	public ClassEntity getClassResult(String id) {
		return clazzDao.getClassResult(id);
	}

	@Override
	public List<Object> listClazz(String hql, int pageSize, int page,
			List params) {
		return clazzDao.listClazz(hql, pageSize, page, params);
	}

	@Override
	public PageModel<Object> getPageModel(QueryParam queryParam) {
		return clazzDao.getPageModel(queryParam);
	}

	@Override
	public boolean setState(String id, short state) {
		return clazzDao.setState(id, state);
	}

	@Override
	public boolean updateClassEntity(ClassEntity classEntity) {
		return clazzDao.updateClassEntity(classEntity);
	}

	/**
	 * 查询出所有的班级
	 * 
	 * @return
	 */
	@Override
	public List<ClassEntity> classlist() {
		return clazzDao.classlist();
	}

	@Override
	public boolean checkAccount(String name) {
		return clazzDao.checkAccount(name);
	}

}
