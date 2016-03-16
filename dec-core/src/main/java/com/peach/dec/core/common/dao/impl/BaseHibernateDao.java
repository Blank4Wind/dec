package com.peach.dec.core.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * hibernate公共的dao
 * 
 * @author peach
 * @date 2015年5月20日
 */
@SuppressWarnings("all")
public class BaseHibernateDao<T, PK extends Serializable> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> entityClass;

	/**
	 * 通过反射获取T的Class类型
	 * 
	 * @return
	 */
	private Class<T> getCurrentEntityClass() {
		ParameterizedType pt = (ParameterizedType) getClass()
				.getGenericSuperclass();
		return (Class<T>) pt.getActualTypeArguments()[0];
	}

	/**
	 * 得到session
	 * 
	 * @return
	 */
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 保存一个对象
	 * 
	 * @param t
	 * @return
	 */
	protected Serializable save(T t) {
		return this.getSession().save(t);
	}

	/**
	 * 删除一个对象
	 * 
	 * @param t
	 */
	protected void delete(T t) {
		this.getSession().delete(t);
	}

	/**
	 * 删除一个对象（通过id）
	 * 
	 * @param id
	 */
	protected void delete(PK id) {
		this.getSession().delete(get(id));
	}

	/**
	 * 更新一个对象
	 * 
	 * @param t
	 */
	protected void update(T t) {
		this.getSession().update(t);
	}

	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 */
	protected void saveOrUpdate(T t) {
		this.getSession().saveOrUpdate(t);
	}

	/**
	 * 获得一个对象
	 * 
	 * @param id
	 * @return
	 */
	protected T get(PK id) {
		return (T) this.getSession().get(entityClass, id);
	}

	/**
	 * 获得一个对象
	 * 
	 * @param id
	 * @return
	 */
	protected T load(PK id) {
		return (T) this.getSession().load(entityClass, id);
	}

	/**
	 * 执行hql更新语句返回行数影响
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	protected int executeUpdate(String hql, Object... params) {
		Query query = this.getSession().createQuery(hql);
		this.generateParams(query, params);
		return query.executeUpdate();
	}

	/**
	 * 执行hql语句返回一个对象
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	protected T uniqueResult(String hql, Object... params) {
		Query query = this.getSession().createQuery(hql);
		this.generateParams(query, params);
		return (T) query.uniqueResult();
	}

	/**
	 * 执行hql语句返回一个list
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	protected List<T> list(String hql, Object... params) {
		Query query = this.getSession().createQuery(hql);
		this.generateParams(query, params);
		return query.list();
	}

	/**
	 * HQL语句 pageSize每页显示多少条 page当前所在页 返回一个对象
	 * 
	 * @param hql
	 * @param pageSize
	 * @param page
	 * @param params
	 * @return
	 */
	public List<Object> queryListObjectAllForPage(String hql, int pageSize,
			int page, List<Object> params) {
		Query query = this.getSession().createQuery(hql);
		this.generateParamsList(query, params);
		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Object> list = query.list();
		return list;
	}
	public List<Object[]> queryListObjectAllForPages(String hql, int pageSize,
			int page, List<Object> params) {
		Query query = this.getSession().createQuery(hql);
		this.generateParamsList(query, params);
		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Object[]> list = query.list();
		return list;
	}

	/**
	 * HQL语句 pageSize每页显示多少条 page当前所在页 返回一个数组
	 * 
	 * @param hql
	 * @param pageSize
	 * @param page
	 * @param params
	 * @return
	 */
	public List<Object[]> queryListAllPage(String hql, int pageSize, int page,
			List<Object> params) {
		Query query = this.getSession().createQuery(hql);
		this.generateParamsList(query, params);
		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Object[]> list = query.list();
		return list;
	}

	/**
	 * select count(*) from 类(无参数时)
	 * 
	 * @param hql
	 * @return 记录数
	 */
	protected Long count(String hql) {
		Query query = this.getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	/**
	 * select count(*) from 类(有参数时)
	 * 
	 * @param hql
	 * @return 记录数
	 */
	protected Long count(String hql, Object... params) {
		Query query = this.getSession().createQuery(hql);
		this.generateParams(query, params);
		return (Long) query.uniqueResult();
	}

	/**
	 * select count(*) from 类(有参数时) 条件查询
	 * 
	 * @param hql
	 * @return 记录数
	 */
	protected Long count2(String hql, List<Object> params) {
		Query query = this.getSession().createQuery(hql);
		this.generateParamsList(query, params);
		return (Long) query.uniqueResult();
	}
	protected Long count3(String hql, List<Object> params) {
		Query query = this.getSession().createQuery(hql);
		this.generateParamsList1(query, params);
		return (Long) query.uniqueResult();
	}

	/**
	 * 给参数赋值的公共方法
	 * 
	 * @param query
	 * @param params
	 */
	private void generateParams(Query query, Object[] params) {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}

	}

	/**
	 * 给参数赋值的公共方法 主要条件List传值
	 * 
	 * @param query
	 * @param params
	 */
	private void generateParamsList(Query query, List<Object> params) {
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}

	}
	private void generateParamsList1(Query query, List<Object> params) {
		if (params != null) {
			for (int i = 0; i < params.size()-1; i++) {
				query.setParameter(i, params.get(i));
			}
		}

	}
}
