package com.peach.dec.core.exam.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.peach.dec.core.common.dao.impl.BaseHibernateDao;
import com.peach.dec.core.exam.dao.RecordsDao;
import com.peach.dec.core.exam.entity.RecordsEntity;
import com.peach.dec.util.util.PageModel;
import com.peach.dec.util.util.StringUtil;

/**
 * dap：考试记录的相关操作
 * 
 * @author peach
 * @date 2015年5月26日
 */
@Repository
public class RecordsDaoImpl extends BaseHibernateDao<RecordsEntity, String>
		implements RecordsDao {
	/**
	 * 对考试记录的条件查询和分页
	 * 
	 * @param recordsEntity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageModel<RecordsEntity> recordsList(RecordsEntity recordsEntity,
			int pageNo, int pageSize, String clazzId) {
		PageModel<RecordsEntity> pageModel = new PageModel<RecordsEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"SELECT r,u.name,p.name,p.totalPoint FROM UserEntity u,RecordsEntity r,PlanEntity e,ExamPaperEntity p,RoleEntity o,ClassEntity c WHERE r.userId=u.id AND r.examPlanId=e.id AND e.examPaperId=p.id AND u.roleId=o.id AND u.classId=c.id AND o.name=?");
		params.add("学员");
		if (recordsEntity != null) {
			if (StringUtil.isNotBlank(recordsEntity.getUserName())) {
				hql.append(" AND u.name LIKE ?");
				params.add("%" + recordsEntity.getUserName() + "%");
			}
			if (StringUtil.isNotBlank(recordsEntity.getPaperName())) {
				hql.append(" AND p.name LIKE ?");
				params.add("%" + recordsEntity.getPaperName() + "%");
			}
			if (StringUtil.isNotBlank(clazzId)) {
				hql.append(" AND c.id=?");
				params.add(clazzId);
			}
			if (recordsEntity.getGetPoint() != null
					&& recordsEntity.getGetPoint() != 0) {
				Integer getPoint = recordsEntity.getGetPoint();
				if (1 == getPoint) {
					hql.append(" AND r.getPoint < ?");
					params.add(60);
				} else if (2 == getPoint) {
					hql.append(" AND r.getPoint >= ? AND r.getPoint < ?");
					params.add(60);
					params.add(70);
				} else if (3 == getPoint) {
					hql.append(" AND r.getPoint >= ? AND r.getPoint < ?");
					params.add(70);
					params.add(80);
				} else if (4 == getPoint) {
					hql.append(" AND r.getPoint >= ? AND r.getPoint < ?");
					params.add(80);
					params.add(100);
				} else if (5 == getPoint) {
					hql.append(" AND r.getPoint >= ?");
					params.add(100);
				}

			}
			if (recordsEntity.getIsPass() != null
					&& recordsEntity.getIsPass() != 0) {
				hql.append(" AND r.isPass = ?");
				params.add(recordsEntity.getIsPass());
			}
		}
		hql.append(" ORDER BY r.id DESC");
		List<Object[]> objects = super.queryListAllPage(hql.toString(),
				pageSize, pageNo, params);
		List<RecordsEntity> recordsEntities = new ArrayList<RecordsEntity>();
		if (objects != null) {
			for (Object[] objects2 : objects) {
				RecordsEntity recordsEntity2 = (RecordsEntity) objects2[0];
				recordsEntity2.setUserName((String) objects2[1]);
				recordsEntity2.setPaperName((String) objects2[2]);
				recordsEntity2.setTotalPoint((Integer) objects2[3]);
				recordsEntities.add(recordsEntity2);
			}
		}
		pageModel.setList(recordsEntities);
		pageModel.setPageNum(pageNo);
		pageModel.setPageSize(pageSize);
		pageModel.setTotalRecord((int) this.getTotalRecords(recordsEntity,
				clazzId));
		return pageModel;
	}

	/**
	 * 查询出总记录数
	 * 
	 * @return
	 */
	public long getTotalRecords(RecordsEntity recordsEntity, String clazzId) {
		long number = 0;
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"SELECT COUNT(*) FROM UserEntity u,RecordsEntity r,PlanEntity e,ExamPaperEntity p,RoleEntity o,ClassEntity c WHERE r.userId=u.id AND r.examPlanId=e.id AND e.examPaperId=p.id AND u.roleId=o.id AND u.classId=c.id AND o.name=?");
		params.add("学员");
		if (recordsEntity != null) {
			if (StringUtil.isNotBlank(recordsEntity.getUserName())) {
				hql.append(" AND u.name LIKE ?");
				params.add("%" + recordsEntity.getUserName() + "%");
			}
			if (StringUtil.isNotBlank(recordsEntity.getPaperName())) {
				hql.append(" AND p.name LIKE ?");
				params.add("%" + recordsEntity.getPaperName() + "%");
			}
			if (StringUtil.isNotBlank(clazzId)) {
				hql.append(" AND c.id=?");
				params.add(clazzId);
			}
			if (recordsEntity.getGetPoint() != null
					&& recordsEntity.getGetPoint() != 0) {
				Integer getPoint = recordsEntity.getGetPoint();
				if (1 == getPoint) {
					hql.append(" AND r.getPoint < ?");
					params.add(60);
				} else if (2 == getPoint) {
					hql.append(" AND r.getPoint >= ? AND r.getPoint < ?");
					params.add(60);
					params.add(70);
				} else if (3 == getPoint) {
					hql.append(" AND r.getPoint >= ? AND r.getPoint < ?");
					params.add(70);
					params.add(80);
				} else if (4 == getPoint) {
					hql.append(" AND r.getPoint >= ? AND r.getPoint < ?");
					params.add(80);
					params.add(100);
				} else if (5 == getPoint) {
					hql.append(" AND r.getPoint >= ?");
					params.add(100);
				}

			}
			if (recordsEntity.getIsPass() != null
					&& recordsEntity.getIsPass() != 0) {
				hql.append(" AND r.isPass = ?");
				params.add(recordsEntity.getIsPass());
			}
		}
		Query query = getSession().createQuery(hql.toString());
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i, params.get(i));
		}
		number = (Long) query.uniqueResult();
		return number;
	}

	/**
	 * 对查询的考试记录进行统计
	 * 
	 * @return
	 */
	public List<RecordsEntity> getGroupCount(RecordsEntity recordsEntity,
			String clazzId) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"SELECT r.isPass,COUNT(*) FROM UserEntity u,RecordsEntity r,PlanEntity e,ExamPaperEntity p,RoleEntity o,ClassEntity c WHERE r.userId=u.id AND r.examPlanId=e.id AND e.examPaperId=p.id AND u.roleId=o.id AND u.classId=c.id AND o.name=?");
		params.add("学员");
		if (recordsEntity != null) {
			if (StringUtil.isNotBlank(recordsEntity.getUserName())) {
				hql.append(" AND u.name LIKE ?");
				params.add("%" + recordsEntity.getUserName() + "%");
			}
			if (StringUtil.isNotBlank(recordsEntity.getPaperName())) {
				hql.append(" AND p.name LIKE ?");
				params.add("%" + recordsEntity.getPaperName() + "%");
			}
			if (StringUtil.isNotBlank(clazzId)) {
				hql.append(" AND c.id=?");
				params.add(clazzId);
			}
			if (recordsEntity.getGetPoint() != null
					&& recordsEntity.getGetPoint() != 0) {
				Integer getPoint = recordsEntity.getGetPoint();
				if (1 == getPoint) {
					hql.append(" AND r.getPoint < ?");
					params.add(60);
				} else if (2 == getPoint) {
					hql.append(" AND r.getPoint >= ? AND r.getPoint < ?");
					params.add(60);
					params.add(70);
				} else if (3 == getPoint) {
					hql.append(" AND r.getPoint >= ? AND r.getPoint < ?");
					params.add(70);
					params.add(80);
				} else if (4 == getPoint) {
					hql.append(" AND r.getPoint >= ? AND r.getPoint < ?");
					params.add(80);
					params.add(100);
				} else if (5 == getPoint) {
					hql.append(" AND r.getPoint >= ?");
					params.add(100);
				}

			}
			if (recordsEntity.getIsPass() != null
					&& recordsEntity.getIsPass() != 0) {
				hql.append(" AND r.isPass = ?");
				params.add(recordsEntity.getIsPass());
			}
		}
		hql.append(" GROUP BY r.isPass");
		Query query = getSession().createQuery(hql.toString());
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i, params.get(i));
		}
		List<RecordsEntity> rList2 = new ArrayList<RecordsEntity>();
		long getTotal = this.getTotalRecords(recordsEntity, clazzId);
		@SuppressWarnings("unchecked")
		List<Object[]> rList = query.list();
		if (rList != null) {
			for (Object[] objects : rList) {
				RecordsEntity recordsEntity2 = new RecordsEntity();
				short count = (Short) objects[0];
				long countNumber = (Long) objects[1];
				if (count == 1) {
					recordsEntity2.setName("通过");
				}
				if (count == 2) {
					recordsEntity2.setName("未通过");
				}
				if (count == 3) {
					recordsEntity2.setName("未提交或超时");
				}
				recordsEntity2.setY(countNumber * 100 / getTotal);

				rList2.add(recordsEntity2);
			}
		}
		return rList2;
	}

	/**
	 * 新增考试记录
	 * 
	 * @param recordsEntity
	 * @return
	 */
	@Override
	public String insertRecords(RecordsEntity recordsEntity) {
		String number = (String) super.save(recordsEntity);
		getSession().flush();
		return number;
	}
	/**
	 * 修改考试记录
	 * 
	 * @param recordsEntity
	 * @return
	 */
	@Override
	public int updateRecords(RecordsEntity recordsEntity) {
		String hql="UPDATE RecordsEntity SET getPoint=?,isPass=?,submitTime=? WHERE examPlanId=? AND userId=?";
		int number =  super.executeUpdate(hql,recordsEntity.getGetPoint(),recordsEntity.getIsPass(),recordsEntity.getSubmitTime(),recordsEntity.getExamPlanId(),recordsEntity.getUserId());
		getSession().flush();
		return number;
	}
	/**
	 * 查询考试记录
	 * 
	 * @param recordsEntity
	 * @return
	 */
	@Override
	public boolean getRecordsEntity(RecordsEntity recordsEntity) {
		boolean flag=true;
		String hql="FROM RecordsEntity WHERE examPlanId=? AND userId=?";
		RecordsEntity records =  super.uniqueResult(hql,recordsEntity.getExamPlanId(),recordsEntity.getUserId());
		if(records==null){
			flag=false;
		}
		getSession().flush();
		return flag;
	}
}
