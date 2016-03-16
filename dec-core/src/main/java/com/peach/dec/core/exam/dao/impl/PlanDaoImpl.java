package com.peach.dec.core.exam.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.peach.dec.core.common.dao.impl.BaseHibernateDao;
import com.peach.dec.core.exam.dao.PlanDao;
import com.peach.dec.core.exam.entity.ExamAnswer;
import com.peach.dec.core.exam.entity.ExamPlanAnswer;
import com.peach.dec.core.exam.entity.PlanEntity;
import com.peach.dec.core.qes.entity.ExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperEntity;
import com.peach.dec.core.qes.entity.QestionsEntity;
import com.peach.dec.core.sys.course.entity.CourseEntity;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.util.DateUtil;
import com.peach.dec.util.util.PageModel;
import com.peach.dec.util.util.StringUtil;

/**
 * dao:考试安排的相关操作
 * 
 * @author peach
 * @date 2015年5月24日
 */
@Repository
@SuppressWarnings("all")
public class PlanDaoImpl extends BaseHibernateDao<PlanEntity, String> implements
		PlanDao {
	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 考试安排查询
	 * 
	 * @param id
	 * @param planEntity
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	@Override
	public PageModel<PlanEntity> planList(String id, int pageNo, int pageSize,
			PlanEntity planEntity, String type, String timeout) {
		PageModel<PlanEntity> pageModel = new PageModel<PlanEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"SELECT p,q.name FROM PlanEntity p,ExamPaperEntity q,UserEntity u WHERE p.examPaperId=q.id  AND p.operateUserId=u.id");
		if (planEntity != null) {
			if (StringUtil.isNotBlank(type)) {
				if ("true".equals(type)) {
					hql.append(" AND p.operateUserId=?");
					params.add(id);
				} else {
					hql.append(" AND p.operateUserId<>?");
					params.add(id);
				}
			}
			if (planEntity.getState() != null) {
				hql.append(" AND p.state = ?");
				params.add(planEntity.getState());
			}

			if (StringUtil.isNotBlank(timeout)) {
				if ("1".equals(timeout)) {
					hql.append(" AND p.examTimeStart <= current_timestamp()");
				}
				if ("2".equals(timeout)) {
					hql.append(" AND p.examTimeStart > current_timestamp()");
				}
			}

		}
		hql.append(" ORDER BY p.operateTime DESC");
		List<Object[]> object = super.queryListAllPage(hql.toString(),
				pageSize, pageNo, params);
		List<PlanEntity> planList = new ArrayList<PlanEntity>();
		for (Object[] objects : object) {
			PlanEntity planEntity2 = (PlanEntity) objects[0];
			planEntity2.setPaperName((String) objects[1]);
			planList.add(planEntity2);
		}
		pageModel.setList(planList);
		pageModel.setPageNum(pageNo);
		pageModel.setPageSize(pageSize);
		pageModel.setTotalRecord((int) this.getTotalRecords(id, planEntity,
				type, timeout));
		return pageModel;
	}

	/**
	 * 查询出总记录数
	 * 
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @param planEntity
	 * @param type
	 * @param timeout
	 * @return
	 */
	public long getTotalRecords(String id, PlanEntity planEntity, String type,
			String timeout) {
		long number = 0;
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(
				"SELECT COUNT(*) FROM PlanEntity p,ExamPaperEntity q,UserEntity u WHERE p.examPaperId=q.id  AND p.operateUserId=u.id");
		if (planEntity != null) {
			if (StringUtil.isNotBlank(type)) {
				if ("true".equals(type)) {
					hql.append(" AND p.operateUserId=?");
					params.add(id);
				} else {
					hql.append(" AND p.operateUserId<>?");
					params.add(id);
				}
			}
			if (planEntity.getState() != null) {
				hql.append(" AND p.state = ?");
				params.add(planEntity.getState());
			}

			if (StringUtil.isNotBlank(timeout)) {
				if ("1".equals(timeout)) {
					hql.append(" AND p.examTimeStart <= current_timestamp()");
				}
				if ("2".equals(timeout)) {
					hql.append(" AND p.examTimeStart > current_timestamp()");
				}
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
	 * 查询出所有的试卷
	 * 
	 * @return
	 */
	@Override
	public List<ExamPaperEntity> paperList() {
		String hql = "FROM ExamPaperEntity WHERE state=1";
		List<ExamPaperEntity> paperlist = getSession().createQuery(hql).list();
		return paperlist;
	}

	/**
	 * 添加考试安排
	 * 
	 * @param planEntity
	 * @return
	 */
	@Override
	public String insert(PlanEntity planEntity) {
		String result = (String) super.save(planEntity);
		List<ExamAnswer> qList = new ArrayList<ExamAnswer>();
		ExamAnswer examAnswer = null;
		String hql = "SELECT e.paperUrl,e.singleOptionEachPoint,e.multiOptionEachPoint,e.judgeEachPoint FROM ExamPaperEntity e WHERE e.id=?";
		Object[] list = (Object[]) getSession().createQuery(hql)
				.setParameter(0, planEntity.getExamPaperId()).uniqueResult();
		String paperUrl = null;
		short singleOptionEachPoint = 0;
		short multiOptionEachPoint = 0;
		short judgeEachPoint = 0;
		if (list != null) {
			paperUrl = (String) list[0];
			singleOptionEachPoint = (Short) list[1];
			multiOptionEachPoint = (Short) list[2];
			judgeEachPoint = (Short) list[3];
		}
		File file1 = new File(paperUrl);
		FileInputStream fileInput = null;
		String strFile = null;
		if (!file1.exists()) {
			this.reGetQesExamPaperEntity(planEntity.getExamPaperId());
		}
		String hqla = "select paperUrl from ExamPaperEntity where id=:id";
		String patha = (String) super.getSession().createQuery(hqla)
				.setParameter("id", planEntity.getExamPaperId()).uniqueResult();
		File file = new File(patha);
		try {
			fileInput = new FileInputStream(file);
			byte[] b = new byte[10024];
			int length = 0;
			while ((length = fileInput.read(b)) != -1) {
				String str = new String(b, 0, length);
				strFile = str;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					fileInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		QesExamPaperEntity qPaperEntity = JSON.parseObject(strFile,
				QesExamPaperEntity.class);
		List<QestionsEntity> singleQestions = qPaperEntity.getListSingle();
		for (QestionsEntity qestionsEntity1 : singleQestions) {
			examAnswer = new ExamAnswer();
			String id = qestionsEntity1.getId();
			String answer = qestionsEntity1.getAnswer();
			examAnswer.setId(id);
			examAnswer.setAnswer(answer);
			examAnswer.setSingleOptionEachPoint(singleOptionEachPoint);
			qList.add(examAnswer);
		}
		List<QestionsEntity> multiQestions = qPaperEntity.getListMulti();
		for (QestionsEntity qestionsEntity2 : multiQestions) {
			examAnswer = new ExamAnswer();
			String id = qestionsEntity2.getId();
			String answer = qestionsEntity2.getAnswer();
			examAnswer.setId(id);
			examAnswer.setAnswer(answer);
			examAnswer.setMultiOptionEachPoint(multiOptionEachPoint);
			qList.add(examAnswer);
		}
		List<QestionsEntity> judgeQestions = qPaperEntity.getListJudge();
		for (QestionsEntity qestionsEntity3 : judgeQestions) {
			examAnswer = new ExamAnswer();
			String id = qestionsEntity3.getId();
			String answer = qestionsEntity3.getAnswer();
			examAnswer.setId(id);
			examAnswer.setAnswer(answer);
			examAnswer.setJudgeEachPoint(judgeEachPoint);
			qList.add(examAnswer);
		}
		getSession().flush();
		ExamPlanAnswer.answerMap.put(result, qList);
		return result;
	}

	/**
	 * 修改前的查询
	 * 
	 * @param planEntity
	 * @return
	 */
	@Override
	public PlanEntity beforeUpdate(PlanEntity planEntity) {
		String hql = "SELECT p,u.name FROM PlanEntity p,ExamPaperEntity e,UserEntity u WHERE p.examPaperId=e.id AND p.operateUserId=u.id AND p.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, planEntity.getId());
		Object[] objects = (Object[]) query.uniqueResult();
		PlanEntity planEntity2 = null;
		if (objects != null) {
			planEntity2 = (PlanEntity) objects[0];
			String userIds = planEntity2.getToUserIds();
			if (userIds != null) {
				String[] args = userIds.split(",");
				String hql2 = "FROM UserEntity WHERE id in (:args)";
				Query query2 = getSession().createQuery(hql2);
				query2.setParameterList("args", args);
				List<UserEntity> obList = query2.list();
				planEntity2.setUser(obList);
			}
			planEntity2.setOpoinName((String) objects[1]);
		}
		return planEntity2;
	}

	/**
	 * 修改考试安排
	 * 
	 * @param planEntity
	 * @return
	 */
	@Override
	public boolean updatePlan(PlanEntity planEntity) {
		boolean result = true;
		String hql = "UPDATE PlanEntity SET examTimeStart=?,examTimeStop=?,examClassroom=?,examPaperId=?,toUserIds=?,toClassId=?,operateUserId=?,operateTime=?,state=?,memo=? WHERE id=?";
		int number = super.executeUpdate(hql, planEntity.getExamTimeStart(),
				planEntity.getExamTimeStop(), planEntity.getExamClassroom(),
				planEntity.getExamPaperId(), planEntity.getToUserIds(),
				planEntity.getToClassId(), planEntity.getOperateUserId(),
				planEntity.getOperateTime(), planEntity.getState(),
				planEntity.getMemo(), planEntity.getId());
		if (number != 1) {
			result = false;
		}
		return result;
	}

	/**
	 * 修改状态
	 * 
	 * @param planEntity
	 * @return
	 */
	@Override
	public boolean changeState(PlanEntity planEntity) {
		boolean result = true;
		String hql = "UPDATE PlanEntity SET state=? WHERE id=?";
		int number = super.executeUpdate(hql, planEntity.getState(),
				planEntity.getId());
		if (number != 1) {
			result = false;
		}
		return result;
	}

	/**
	 * 查询出试卷的考试时间
	 * 
	 * @param paperId
	 * @param planEntity
	 * @return
	 */
	@Override
	public String queryEndTime(String paperId, PlanEntity planEntity) {
		String resultTime = null;
		long endTime = 0;
		String hql = "SELECT totalMinutes FROM ExamPaperEntity WHERE id=?";
		Integer totalMinutes = (Integer) getSession().createQuery(hql)
				.setParameter(0, paperId).uniqueResult();
		long result = 0;
		if (totalMinutes != null) {
			result = totalMinutes;
			long total = result * 60000;
			long startTime = 0;
			if (planEntity.getExamTimeStart() != null) {
				startTime = planEntity.getExamTimeStart().getTime();
			}
			endTime = total + startTime;
		}
		Date date = new Date(endTime);
		if (date != null) {
			resultTime = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
		}
		return resultTime;
	}

	/**
	 * 重新生成
	 * 
	 * @param id
	 */
	public void reGetQesExamPaperEntity(String id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT e.id,e.name,e.paperUrl,e.paperType,e.courseId,e.totalPoint,e.passPoint,e.totalMinutes,e.singleOptionNumber,e.singleOptionEachPoint,e.singleOptionDifficulty,e.multiOptionNumber,e.multiOptionEachPoint,e.multiOptionDifficulty,e.judgeNumber,e.judgeEachPoint,e.judgeDifficulty,e.opreateUserId,e.opreateTime,e.state,e.memo,c.name FROM ExamPaperEntity e,CourseEntity c WHERE e.id=:id AND c.id=e.courseId";
		List<Object[]> listobj = new ArrayList<Object[]>();
		ExamPaperEntity entity = new ExamPaperEntity();
		listobj = session.createQuery(hql).setParameter("id", id).list();
		entity.setId((String) listobj.get(0)[0]);
		entity.setName((String) listobj.get(0)[1]);
		entity.setPaperUrl((String) listobj.get(0)[2]);
		entity.setPaperType((Short) listobj.get(0)[3]);
		entity.setCourseId((String) listobj.get(0)[4]);
		entity.setTotalPoint((Integer) listobj.get(0)[5]);
		entity.setPassPoint((Integer) listobj.get(0)[6]);
		entity.setTotalMinutes((Integer) listobj.get(0)[7]);
		entity.setSingleOptionNumber((Short) listobj.get(0)[8]);
		entity.setSingleOptionEachPoint((Short) listobj.get(0)[9]);
		entity.setSingleOptionDifficulty((Short) listobj.get(0)[10]);
		entity.setMultiOptionNumber((Short) listobj.get(0)[11]);
		entity.setMultiOptionEachPoint((Short) listobj.get(0)[12]);
		entity.setMultiOptionDifficulty((Short) listobj.get(0)[13]);
		entity.setJudgeNumber((Short) listobj.get(0)[14]);
		entity.setJudgeEachPoint((Short) listobj.get(0)[15]);
		entity.setJudgeDifficulty((Short) listobj.get(0)[16]);
		entity.setOpreateUserId((String) listobj.get(0)[17]);
		entity.setOpreateTime((Date) listobj.get(0)[18]);
		entity.setState((Short) listobj.get(0)[19]);
		entity.setMemo((String) listobj.get(0)[20]);
		entity.setCourseName((String) listobj.get(0)[21]);
		File file = new File(entity.getPaperUrl());
		if (file.exists()) {
			if (!file.isFile()) {
				file.delete();
			} else {
				file.delete();
			}
		}
		entity.setPaperUrl(null);
		this.addExamPaper(entity);
	}

	/**
	 * 试卷生成
	 */
	public void addExamPaper(ExamPaperEntity examPaperEntity) {
		Session session = sessionFactory.getCurrentSession();
		QesExamPaperEntity paperEntity = new QesExamPaperEntity();
		List<QestionsEntity> listSingle = new ArrayList<QestionsEntity>();
		List<QestionsEntity> listMulti = new ArrayList<QestionsEntity>();
		List<QestionsEntity> listJudge = new ArrayList<QestionsEntity>();
		if (null != examPaperEntity.getSingleOptionNumber()
				|| examPaperEntity.getSingleOptionNumber() > 0) {
			List<Object[]> list = new ArrayList<Object[]>();
			StringBuffer singlestr = new StringBuffer(
					"SELECT question,answer_a,answer_b,answer_c,answer_d,answer,keywords,id FROM (SELECT * FROM qes_qestions ORDER BY dbms_random.value) WHERE state=1 AND question_type=1");
			if (null != examPaperEntity.getSingleOptionDifficulty()
					|| examPaperEntity.getSingleOptionDifficulty() > 0) {
				singlestr.append(" AND rownum <="
						+ examPaperEntity.getSingleOptionNumber()
						+ " AND difficulty="
						+ examPaperEntity.getSingleOptionDifficulty());
			} else {
				singlestr.append(" AND rownum <="
						+ examPaperEntity.getSingleOptionNumber());
			}
			Query query = session.createSQLQuery(singlestr.toString());
			list = query.list();
			// list = getSession().createSQLQuery(singlestr.toString()).list();
			for (int i = 0; i < list.size(); i++) {
				QestionsEntity qestionsEntity = new QestionsEntity();
				qestionsEntity.setQuestion((String) list.get(i)[0]);
				qestionsEntity.setAnswerA((String) list.get(i)[1]);
				qestionsEntity.setAnswerB((String) list.get(i)[2]);
				qestionsEntity.setAnswerC((String) list.get(i)[3]);
				qestionsEntity.setAnswerD((String) list.get(i)[4]);
				qestionsEntity.setAnswer((String) list.get(i)[5]);
				qestionsEntity.setKeywords((String) list.get(i)[6]);
				qestionsEntity.setId((String) list.get(i)[7]);
				listSingle.add(qestionsEntity);
			}
		}
		if (null != examPaperEntity.getMultiOptionNumber()
				|| examPaperEntity.getMultiOptionNumber() > 0) {
			List<Object[]> list = new ArrayList<Object[]>();
			StringBuffer multistr = new StringBuffer(
					"SELECT question,answer_a,answer_b,answer_c,answer_d,answer,keywords,id FROM (SELECT * FROM qes_qestions ORDER BY dbms_random.value) WHERE state=1 AND question_type=2");
			if (null != examPaperEntity.getMultiOptionDifficulty()
					|| examPaperEntity.getMultiOptionDifficulty() > 0) {
				multistr.append(" AND rownum <="
						+ examPaperEntity.getMultiOptionNumber()
						+ " AND difficulty="
						+ examPaperEntity.getMultiOptionDifficulty());
			} else {
				multistr.append(" AND rownum <="
						+ examPaperEntity.getMultiOptionNumber());
			}
			Query query = session.createSQLQuery(multistr.toString());
			list = query.list();
			// list = getSession().createSQLQuery(multistr.toString()).list();
			for (int i = 0; i < list.size(); i++) {
				QestionsEntity qestionsEntity = new QestionsEntity();
				qestionsEntity.setQuestion((String) list.get(i)[0]);
				qestionsEntity.setAnswerA((String) list.get(i)[1]);
				qestionsEntity.setAnswerB((String) list.get(i)[2]);
				qestionsEntity.setAnswerC((String) list.get(i)[3]);
				qestionsEntity.setAnswerD((String) list.get(i)[4]);
				qestionsEntity.setAnswer((String) list.get(i)[5]);
				qestionsEntity.setKeywords((String) list.get(i)[6]);
				qestionsEntity.setId((String) list.get(i)[7]);
				listMulti.add(qestionsEntity);
			}
		}
		if (null != examPaperEntity.getJudgeNumber()
				|| examPaperEntity.getJudgeNumber() > 0) {
			List<Object[]> list = new ArrayList<Object[]>();
			StringBuffer judgestr = new StringBuffer(
					"SELECT question,answer_a,answer_b,answer_c,answer_d,answer,keywords,id FROM (SELECT * FROM qes_qestions ORDER BY dbms_random.value) WHERE state=1 AND question_type=3");
			if (examPaperEntity.getJudgeDifficulty() != null
					|| examPaperEntity.getJudgeDifficulty() > 0) {
				judgestr.append(" AND rownum <="
						+ examPaperEntity.getJudgeNumber() + " AND difficulty="
						+ examPaperEntity.getJudgeDifficulty());
			} else {
				judgestr.append(" AND rownum <="
						+ examPaperEntity.getJudgeNumber());
			}
			Query query = session.createSQLQuery(judgestr.toString());
			list = query.list();
			// list = getSession().createSQLQuery(judgestr.toString()).list();
			for (int i = 0; i < list.size(); i++) {
				QestionsEntity qestionsEntity = new QestionsEntity();
				qestionsEntity.setQuestion((String) list.get(i)[0]);
				qestionsEntity.setAnswerA((String) list.get(i)[1]);
				qestionsEntity.setAnswerB((String) list.get(i)[2]);
				qestionsEntity.setAnswerC((String) list.get(i)[3]);
				qestionsEntity.setAnswerD((String) list.get(i)[4]);
				qestionsEntity.setAnswer((String) list.get(i)[5]);
				qestionsEntity.setKeywords((String) list.get(i)[6]);
				qestionsEntity.setId((String) list.get(i)[7]);
				listJudge.add(qestionsEntity);
			}
		}
		paperEntity.setCourseId(this.getCourseId(examPaperEntity
				.getCourseName()));
		paperEntity.setCourseName(examPaperEntity.getCourseName());
		paperEntity.setName(examPaperEntity.getName());
		paperEntity.setPassPoint(examPaperEntity.getPassPoint());
		paperEntity.setTotalMinutes(examPaperEntity.getTotalMinutes());
		paperEntity.setTotalPoint(examPaperEntity.getTotalPoint());
		paperEntity.setUserId(examPaperEntity.getId());
		paperEntity.setPaperType(examPaperEntity.getPaperType());
		paperEntity.setListSingle(listSingle);
		paperEntity.setListMulti(listMulti);
		paperEntity.setListJudge(listJudge);
		examPaperEntity.setCourseId(this.getCourseId(examPaperEntity
				.getCourseName()));
		String exam = JSON.toJSONString(paperEntity);
		String path = this.saveObject(exam);
		examPaperEntity.setPaperUrl(path);
		examPaperEntity.setCourseId(this.getCourseId(examPaperEntity
				.getCourseName()));
		session.saveOrUpdate(examPaperEntity);
		session.flush();
	}

	/**
	 * 得到课程id
	 */
	public String getCourseId(String name) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT new CourseEntity(id )FROM CourseEntity WHERE name=:name";
		List<CourseEntity> listCourseEntity = session.createQuery(hql)
				.setParameter("name", name).list();
		String id = listCourseEntity.get(0).getId();
		return id;
	}

	/**
	 * 把json字符串保存在本地
	 * 
	 * @throws ParseException
	 */

	public String saveObject(String str) {
		long now = new Date().getTime();
		File a = new File("D:" + File.separator + "devroot");
		if (!a.exists() || a.isDirectory()) {
			a.mkdir();
		}
		String fileName = "D:" + File.separator + "devroot" + File.separator
				+ now + ".json";
		File file = new File(fileName);
		try {
			FileWriter fw;
			fw = new FileWriter(file);
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
}
