package com.peach.dec.core.stu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.peach.dec.core.common.dao.impl.BaseHibernateDao;
import com.peach.dec.core.stu.dao.StuDao;
import com.peach.dec.core.stu.entity.PlanDetailEntity;
import com.peach.dec.core.stu.entity.RecordsDetailsEntity;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.util.PageModel;
import com.peach.dec.util.util.StringUtil;

@Repository
public class StuDaoImpl extends BaseHibernateDao<Object, String> implements
		StuDao {
	@Override
	public List<PlanDetailEntity> getExamPlan(UserEntity user1) {
		String hql = "FROM PlanDetailEntity WHERE (toUserIds like '%"
				+ user1.getId()
				+ "%' OR toClassId=?) AND examTimeStop >= current_timestamp() AND state=1 ";
		String hql2 ="FROM RecordsDetailsEntity WHERE userId=?";
		List<RecordsDetailsEntity> list = new ArrayList<RecordsDetailsEntity>();
		List<Object> records = super.list(hql2, user1.getId());
		for (Object plan : records) {
			RecordsDetailsEntity plan1 = (RecordsDetailsEntity) plan;
			list.add(plan1);
		}
		
		List<PlanDetailEntity> plan = new ArrayList<PlanDetailEntity>();
		List<Object> list2 = super.list(hql, user1.getClassId());
		for (Object plan2 : list2) {
			PlanDetailEntity plan1 = (PlanDetailEntity) plan2;
			plan.add(plan1);
		}
//		for (PlanDetailEntity plan2 : plan) {
//			for (RecordsDetailsEntity records2 : list) {
//				if(records2.getSubmitTime()!=null&&records2.getExamPlanId().equals(plan2.getId())){
//					plan.remove(plan2);
//				}
//			}
//			
//		}
		for (int i=0; i<plan.size();i++) {
		for (int j=0; j<list.size();j++) {
			
			if((list.get(j)).getSubmitTime()!=null&&(list.get(j)).getExamPlanId().equals(plan.get(i).getId())){
				plan.remove(plan.get(i));
					
			}
		}
		
	}	
		
		return plan;
	}

	@Override
	public PageModel<RecordsDetailsEntity> getExamRecords(UserEntity user2,
			int pageSize, int page, RecordsDetailsEntity recordsDetails) {
		PageModel<RecordsDetailsEntity> pageModel = new PageModel<RecordsDetailsEntity>();
		List<Object> parm = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"FROM RecordsDetailsEntity WHERE userId=?");
		parm.add(user2.getId());
		if (recordsDetails != null) {
			if (StringUtil.isNotBlank(recordsDetails.getQname())) {
				hql.append(" AND qname like ?");
				parm.add("%" + recordsDetails.getQname() + "%");
			}
			if (recordsDetails.getIsPass() != 0
					&& recordsDetails.getIsPass() != null) {
				hql.append(" AND isPass = ?");
				parm.add(recordsDetails.getIsPass());
			}
		}
		hql.append("ORDER BY submitTime DESC");
		List<Object> object = super.queryListObjectAllForPage(hql.toString(),
				pageSize, page, parm);
		List<RecordsDetailsEntity> list = new ArrayList<RecordsDetailsEntity>();
		for (Object plan : object) {
			RecordsDetailsEntity plan1 = (RecordsDetailsEntity) plan;
			list.add(plan1);
		}

		String hql2 = "select count(*) " + hql;
		long count = super.count2(hql2, parm);
		int num = (int) count;
		pageModel.setList(list);
		pageModel.setPageNum(page);
		pageModel.setPageSize(pageSize);
		pageModel.setTotalRecord(num);
		return pageModel;
	}

	@Override
	public List<Object> getAssistMessage(UserEntity user1) {
		String hql = "FROM MessageDetailsEntity WHERE (receiverUserIds LIKE '%"
				+ user1.getId()
				+ "%' OR receiverClassIds=?) AND expireTime >= current_timestamp() AND sendTime<=current_timestamp() ORDER BY expireTime DESC";
		List<Object> message = super.list(hql, user1.getId());
		return message;
	}

}
