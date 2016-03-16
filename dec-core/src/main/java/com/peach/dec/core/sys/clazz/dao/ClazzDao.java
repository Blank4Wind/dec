package com.peach.dec.core.sys.clazz.dao;

import java.util.Date;
import java.util.List;

import com.peach.dec.core.sys.clazz.dao.impl.QueryParam;
import com.peach.dec.core.sys.clazz.entity.ClassEntity;
import com.peach.dec.util.util.PageModel;

@SuppressWarnings("all")
public interface ClazzDao {
	List<Object> listClazzName();

	List<Object> listClazz(String hql, int pageSize, int page, List params);

	boolean addClass(ClassEntity classEntity);

	String getCourseId(String name);

	String getUserId();

	Date getCurrentTime();

	ClassEntity getClassResult(String id);

	PageModel<Object> getPageModel(QueryParam queryParam);

	boolean setState(String id, short state);

	boolean updateClassEntity(ClassEntity classEntity);

	List<ClassEntity> classlist();

	/**
	 * 简称用户名是否存在
	 */

	boolean checkAccount(String name);
}
