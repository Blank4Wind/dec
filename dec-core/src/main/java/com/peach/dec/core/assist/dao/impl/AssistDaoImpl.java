package com.peach.dec.core.assist.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.peach.dec.core.assist.dao.AssistDao;
import com.peach.dec.core.assist.entity.MessageEntity;
import com.peach.dec.core.common.dao.impl.BaseHibernateDao;
import com.peach.dec.core.stu.entity.MessageDetailsEntity;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.util.PageModel;
import com.peach.dec.util.util.StringUtil;

@Repository
public class AssistDaoImpl extends BaseHibernateDao<Object, String> implements
		AssistDao {

	@Override
	public PageModel<MessageDetailsEntity> getAssistMessage(
			MessageDetailsEntity messageDetails, int pageSize, int page,
			String beOverdue) {
		PageModel<MessageDetailsEntity> pageModel = new PageModel<MessageDetailsEntity>();
		List<Object> parm = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"FROM MessageDetailsEntity WHERE 1=1");
		if (messageDetails != null) {
			if (StringUtil.isNotBlank(messageDetails.getTitle())) {
				hql.append(" AND title like ?");
				parm.add("%" + messageDetails.getTitle() + "%");
			}

			if ("true".equals(messageDetails.getUname())) {
				hql.append(" AND senderId = ?");
				parm.add(messageDetails.getSenderId());
			} else {
				hql.append(" AND senderId <> ?");
				parm.add(messageDetails.getSenderId());
			}
			String rname = messageDetails.getRname();
			if (StringUtil.isNotBlank(rname)) {
				hql.append(" AND rname = ?");
				parm.add(messageDetails.getRname());
			}
			if (messageDetails.getState() != null
					&& messageDetails.getState() != 0) {
				hql.append(" AND state = ?");
				parm.add(messageDetails.getState());
			}
			if (StringUtil.isNotBlank(beOverdue)) {
				// 是否过期
				if ("否".equals(beOverdue)) {
					hql.append(" AND expireTime > current_timestamp()");
				}
				if ("是".equals(beOverdue)) {
					hql.append(" AND expireTime <= current_timestamp()");
				}
			}
		}
		hql.append("ORDER BY sendTime DESC");
		List<Object> object = super.queryListObjectAllForPage(hql.toString(),
				pageSize, page, parm);
		List<MessageDetailsEntity> list = new ArrayList<MessageDetailsEntity>();
		for (Object plan : object) {
			MessageDetailsEntity plan1 = (MessageDetailsEntity) plan;
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
	public boolean addAssistMessage(MessageEntity message) {
		boolean result = true;
		String flag = null;
		if (StringUtil.isBlank(message.getReceiverClassIds())) {
			message.setReceiverClassIds("");
		}
		if (StringUtil.isBlank(message.getReceiverUserIds())) {
			message.setReceiverUserIds("");
		}
		flag = (String) super.save(message);
		getSession().flush();
		if (flag == null) {
			result = false;
		}
		return result;
	}

	@Override
	public boolean updateAssistMessage(MessageEntity messageDetails) {
		boolean result = true;
		String hql = "UPDATE MessageDetailsEntity SET state=? WHERE id=?";
		int num = super.executeUpdate(hql, messageDetails.getState(),
				messageDetails.getId());
		if (num == 0) {
			result = false;
		}
		return result;
	}

	@Override
	public MessageDetailsEntity getAssistEntity(MessageEntity message) {
		String hql = "FROM MessageDetailsEntity WHERE id=?";
		MessageDetailsEntity messEntity = (MessageDetailsEntity) super.list(
				hql, message.getId()).get(0);
		if (messEntity != null) {
			String stusId = messEntity.getReceiverUserIds();
			if (StringUtil.isNotBlank(stusId)) {
				String[] stus = stusId.split(",");
				String hql2 = "FROM UserEntity WHERE id IN (:args)";
				Query query = getSession().createQuery(hql2);
				query.setParameterList("args", stus);
				@SuppressWarnings("unchecked")
				List<UserEntity> userName = query.list();
				messEntity.setUser2(userName);
			}
		}
		return messEntity;
	}

	@Override
	public boolean updateAssistMessages(MessageDetailsEntity messageDetails) {
		boolean result = true;
		String hql = "UPDATE MessageDetailsEntity SET title=?,content=?,receiverUserIds=?,receiverClassIds=?,expireTime=?,sendTime=?,state=? WHERE id=?";
		if (StringUtil.isBlank(messageDetails.getReceiverClassIds())) {
			messageDetails.setReceiverClassIds("");
		}
		if (StringUtil.isBlank(messageDetails.getReceiverUserIds())) {
			messageDetails.setReceiverUserIds("");
		}
		int num = super.executeUpdate(hql, messageDetails.getTitle(),
				messageDetails.getContent(),
				messageDetails.getReceiverUserIds(),
				messageDetails.getReceiverClassIds(),
				messageDetails.getExpireTime(), messageDetails.getSendTime(),
				messageDetails.getState(), messageDetails.getId());


		// super.list(hql, params)

	//	return false;


		
		if (num == 0) {
			result = false;
		}
		return result;


	}
}
