package com.peach.dec.core.qes.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.peach.dec.core.common.dao.impl.BaseHibernateDao;
import com.peach.dec.core.qes.dao.QestionsDao;
import com.peach.dec.core.qes.entity.ExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperQueryCondition;
import com.peach.dec.core.qes.entity.QestionsEntity;
import com.peach.dec.core.qes.entity.QestionsQueryCondition;
import com.peach.dec.core.sys.course.entity.CourseEntity;
import com.peach.dec.util.constant.Constant;
import com.peach.dec.util.util.PageModel;
import com.peach.dec.util.util.StringUtil;

/**
 * dao：试题的相关操作
 * 
 * @author peach
 * @date 2015年5月23日
 */
@Repository
@SuppressWarnings("all")
public class QestionsDaoImpl extends BaseHibernateDao<Object, String> implements
		QestionsDao {
	// @Resource
	private SessionFactory sessionFactory;

	/**
	 * 查询所有的试题
	 * 
	 * @return
	 */
	@Override
	public List<QestionsEntity> list() {
		String hql = "SELECT q.id,q.question,q.questionType,q.difficulty,q.state,c.name FROM QestionsEntity q,CourseEntity c WHERE q.courseId=c.id";
		@SuppressWarnings("unchecked")
		List<Object[]> objectlist = getSession().createQuery(hql).list();
		List<QestionsEntity> qeslist = new ArrayList<QestionsEntity>();
		if (objectlist != null) {
			for (Object[] objs : objectlist) {
				QestionsEntity qestion = new QestionsEntity();
				qestion.setId((String) objs[0]);
				qestion.setQuestion((String) objs[1]);
				qestion.setQuestionType((Short) objs[2]);
				qestion.setDifficulty((Short) objs[3]);
				qestion.setState((Short) objs[4]);
				String courseName = (String) objs[5];
				qestion.setCourseName(courseName);
				qeslist.add(qestion);
			}
		}
		for (QestionsEntity qestionsEntity : qeslist) {
		}
		return qeslist;

	}

	/**
	 * 新增试题
	 * 
	 * @param qestionsEntity
	 * @return
	 */
	@Override
	public boolean insertQestion(QestionsEntity qestionsEntity) {
		boolean result = true;
		String id = (String) getSession().save(qestionsEntity);
		getSession().flush();
		if (id == null) {
			result = false;
		}
		return result;
	}

	/*
	 * 修改前的查询
	 */
	public QestionsEntity updateList(String id) {
		String hql = "SELECT q,c.name FROM QestionsEntity q,CourseEntity c WHERE q.courseId=c.id AND q.id=?";
		Object[] objects = (Object[]) getSession().createQuery(hql)
				.setString(0, id).uniqueResult();
		QestionsEntity qestionsEntity = (QestionsEntity) objects[0];
		qestionsEntity.setCourseName((String) objects[1]);
		return qestionsEntity;
	}

	/*
	 * 执行修改
	 */
	@Override
	public boolean sureUpdate(QestionsEntity qestionsEntity) {
		boolean result = true;
		String hql = "UPDATE QestionsEntity  SET question=?,attachment=?,questionType=?,courseId=?,answerA=?,answerB=?,answerC=?,answerD=?,answer=?,difficulty=?,analysis=?,keywords=?,operateUserId=?,operateTime=?,state=?,memo=? WHERE id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, qestionsEntity.getQuestion());
		query.setParameter(1, qestionsEntity.getAttachment());
		query.setParameter(2, qestionsEntity.getQuestionType());
		query.setParameter(3, qestionsEntity.getCourseId());
		query.setParameter(4, qestionsEntity.getAnswerA());
		query.setParameter(5, qestionsEntity.getAnswerB());
		query.setParameter(6, qestionsEntity.getAnswerC());
		query.setParameter(7, qestionsEntity.getAnswerD());
		query.setParameter(8, qestionsEntity.getAnswer());
		query.setParameter(9, qestionsEntity.getDifficulty());
		query.setParameter(10, qestionsEntity.getAnalysis());
		query.setParameter(11, qestionsEntity.getKeywords());
		query.setParameter(12, qestionsEntity.getOperateUserId());
		query.setParameter(13, qestionsEntity.getOperateTime());
		query.setParameter(14, qestionsEntity.getState());
		query.setParameter(15, qestionsEntity.getMemo());
		query.setParameter(16, qestionsEntity.getId());
		int number = query.executeUpdate();
		if (number != 1) {
			result = false;
		}
		return result;
	}

	/**
	 * 通过id改变试题的状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@Override
	public boolean changeState(String id, short status) {
		boolean result = true;
		String hql = "UPDATE QestionsEntity SET state=? WHERE id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, status);
		query.setParameter(1, id);
		int number = query.executeUpdate();
		if (number != 1) {
			result = false;
		}
		return result;
	}

	/**
	 * 试卷分页查询
	 */
	@Override
	public PageModel<Object> getQesExamEntity(
			QesExamPaperQueryCondition condition) {
		PageModel<Object> pageModel = new PageModel<Object>();
		StringBuffer str = new StringBuffer(
				"SELECT epe.id,epe.name as ename,cu.name as cname,epe.totalPoint,epe.passPoint,epe.totalMinutes,epe.state,ue.name,epe.paperType,epe.singleOptionNumber,epe.singleOptionEachPoint,epe.singleOptionDifficulty,epe.multiOptionNumber,epe.multiOptionEachPoint,epe.multiOptionDifficulty,epe.judgeNumber,epe.judgeEachPoint,epe.judgeDifficulty,epe.opreateTime,epe.memo FROM ExamPaperEntity epe,CourseEntity cu,UserEntity ue WHERE epe.courseId=cu.id AND epe.opreateUserId=ue.id");
		// StringBuffer str = new
		// StringBuffer("SELECT new ExamPaperEntity(epe.id,epe.name,epe.totalPoint,epe.passPoint,epe.totalMinutes,epe.state),new CourseEntity(cu.id,cu.name) FROM ExamPaperEntity epe inner join CourseEntity cu WHERE epe.courseId=cu.id ");
		StringBuffer str1 = new StringBuffer(
				"FROM ExamPaperEntity epe,CourseEntity cu WHERE epe.courseId=cu.id");
		List<Object> params = new ArrayList<Object>();
		if (StringUtil.isNotBlank(condition.getName())) {
			str.append(" AND epe.name LIKE '%" + condition.getName() + "%'");
			str1.append(" AND epe.name LIKE '%" + condition.getName() + "%'");
		}
		if (StringUtil.isNotBlank(condition.getCourseName())) {
			str.append(" AND epe.courseId = '" + condition.getCourseId() + "'");
			str1.append(" AND epe.courseId = '" + condition.getCourseId() + "'");
		}
		if (condition.getState() != 0) {
			str.append(" AND epe.state = ?");
			str1.append(" AND epe.state = ?");
			params.add(condition.getState());
		}
		List<Object[]> list = super.queryListAllPage(str.toString(),
				condition.getPageSize(), condition.getPage(), params);
		String hql = "select count(*) " + str1;
		long num = super.count2(hql, params);
		int count = (int) num;
		List<Object> listobj = new ArrayList<Object>();
		// ue.name,epe.paperType,epe.singleOptionNumber,epe.singleOptionEachPoint,epe.singleOptionDifficulty,epe.multiOptionNumber,epe.multiOptionEachPoint,epe.multiOptionDifficulty,epe.judgeNumber,epe.judgeEachPoint,epe.judgeDifficulty,epe.opreateTime,epe.memo
		for (int i = 0; i < list.size(); i++) {
			Object[] object = list.get(i);
			ExamPaperEntity examPaperEntity = new ExamPaperEntity();
			examPaperEntity.setId((String) object[0]);
			examPaperEntity.setName((String) object[1]);
			examPaperEntity.setCourseName((String) object[2]);
			examPaperEntity.setTotalPoint((Integer) object[3]);
			examPaperEntity.setPassPoint((Integer) object[4]);
			examPaperEntity.setTotalMinutes((Integer) object[5]);
			examPaperEntity.setState((Short) object[6]);
			examPaperEntity.setUserName((String) object[7]);
			examPaperEntity.setPaperType((Short) object[8]);
			examPaperEntity.setSingleOptionNumber((Short) object[9]);
			examPaperEntity.setSingleOptionEachPoint((Short) object[10]);
			examPaperEntity.setSingleOptionDifficulty((Short) object[11]);
			examPaperEntity.setMultiOptionNumber((Short) object[12]);
			examPaperEntity.setMultiOptionEachPoint((Short) object[13]);
			examPaperEntity.setMultiOptionDifficulty((Short) object[14]);
			examPaperEntity.setJudgeNumber((Short) object[15]);
			examPaperEntity.setJudgeEachPoint((Short) object[16]);
			examPaperEntity.setJudgeDifficulty((Short) object[17]);
			examPaperEntity.setOpreateTime((Date) object[18]);
			examPaperEntity.setMemo((String) object[19]);
			listobj.add(examPaperEntity);
		}
		pageModel.setList(listobj);
		pageModel.setTotalRecord(count);
		pageModel.setPageNum(condition.getPage());
		pageModel.setPageSize(Constant.PAGE_SIZE);

		return pageModel;
	}

	@Override
	public List<Object> listClazzName() {
		String id = this.getCourseId("全部课程");
		String hql = "SELECT name FROM CourseEntity WHERE PID='" + id + "'";
		List<Object> listClazzName = super.list(hql, null);
		return listClazzName;
	}

	/**
	 * 得到课程id
	 */
	public String getCourseId(String name) {
		String hql = "SELECT new CourseEntity(id )FROM CourseEntity WHERE name=:name";
		List<CourseEntity> listCourseEntity = super.getSession()
				.createQuery(hql).setParameter("name", name).list();
		String id = listCourseEntity.get(0).getId();
		return id;
	}

	/**
	 * 根据id修改状态
	 */
	@Override
	public boolean setState(String id, short state) {
		boolean result = false;
		String hql = "UPDATE ExamPaperEntity SET state=? WHERE id=? ";
		int a = super.executeUpdate(hql, state, id);
		if (a == 1) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * 试卷生成
	 */
	@Override
	public boolean addExamPaper(ExamPaperEntity examPaperEntity) {
		// TODO
		List<QestionsEntity> listSingle = new ArrayList<QestionsEntity>();
		List<QestionsEntity> listMulti;
		List<QestionsEntity> listJudge;
		if (null != examPaperEntity.getSingleOptionNumber()
				|| examPaperEntity.getSingleOptionNumber() > 0) {
			List<Object[]> list = new ArrayList<Object[]>();
			StringBuffer singlestr = new StringBuffer(
					"SELECT id,question,attachment,question_type,course_id,answer_a,answer_b,answer_c,answer_d,answer,difficulty,analysis,keywords,operate_user_id,operate_time,state,memo FROM (SELECT * FROM qes_qestions ORDER BY dbms_random.value) WHERE state=1 AND question_type=1 ");
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
			list = super.getSession().createSQLQuery(singlestr.toString())
					.list();

			for (int i = 0; i < list.size(); i++) {
				QestionsEntity qestionsEntity = new QestionsEntity();
				qestionsEntity.setId((String) list.get(i)[0]);
				qestionsEntity.setQuestion((String) list.get(i)[1]);
				qestionsEntity.setAttachment((String) list.get(i)[2]);
				qestionsEntity.setQuestionType((Short) list.get(i)[3]);
				qestionsEntity.setCourseId((String) list.get(i)[4]);
				qestionsEntity.setAnswerA((String) list.get(i)[5]);
				qestionsEntity.setAnswerB((String) list.get(i)[6]);
				qestionsEntity.setAnswerC((String) list.get(i)[7]);
				qestionsEntity.setAnswerD((String) list.get(i)[8]);
				qestionsEntity.setAnswer((String) list.get(i)[9]);
				qestionsEntity.setDifficulty((Short) list.get(i)[10]);
				qestionsEntity.setAnalysis((String) list.get(i)[11]);
				qestionsEntity.setKeywords((String) list.get(i)[12]);
				qestionsEntity.setOperateUserId((String) list.get(i)[13]);
				qestionsEntity.setOperateTime((Date) list.get(i)[14]);
				qestionsEntity.setState((Short) list.get(i)[15]);
				qestionsEntity.setMemo((String) list.get(i)[16]);
				listSingle.add(qestionsEntity);
			}
			// listSingle =
			// super.getSession().createQuery(singlestr.toString()).list();
		}
		if (null != examPaperEntity.getMultiOptionNumber()
				|| examPaperEntity.getMultiOptionNumber() > 0) {
			StringBuffer multistr = new StringBuffer(
					"SELECT * FROM (SELECT * FROM qes_qestions ORDER BY dbms_random.value) WHERE state=1 AND question_type=2 ");
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
			listMulti = super.getSession().createSQLQuery(multistr.toString())
					.list();
		}
		if (null != examPaperEntity.getJudgeNumber()
				|| examPaperEntity.getJudgeNumber() > 0) {
			StringBuffer judgestr = new StringBuffer(
					"SELECT * FROM (SELECT * FROM qes_qestions ORDER BY dbms_random.value) WHERE state=1 AND question_type=3 ");
			if (examPaperEntity.getJudgeDifficulty() != null
					|| examPaperEntity.getJudgeDifficulty() > 0) {
				judgestr.append(" AND rownum <="
						+ examPaperEntity.getJudgeNumber() + " AND difficulty="
						+ examPaperEntity.getJudgeDifficulty());
			} else {
				judgestr.append(" AND rownum <="
						+ examPaperEntity.getJudgeNumber());
			}
			listJudge = super.getSession().createSQLQuery(judgestr.toString())
					.list();
		}
		return false;
	}

	@Override
	public boolean detail(QestionsEntity qestionsEntity) {
		boolean result = true;
		String hql = "FROM QestionsEntity WHERE id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, qestionsEntity.getQuestion());
		query.setParameter(1, qestionsEntity.getAttachment());
		query.setParameter(2, qestionsEntity.getQuestionType());
		// CourseService courseService = new CourseServiceImpl();
		// String courseId =
		// courseService.select(qestionsEntity.getCourseName());
		// query.setParameter(3, qestionsEntity.getCourseId());
		query.setParameter(3, qestionsEntity.getAnswerA());
		query.setParameter(4, qestionsEntity.getAnswerB());
		query.setParameter(5, qestionsEntity.getAnswerC());
		query.setParameter(6, qestionsEntity.getAnswerD());
		query.setParameter(7, qestionsEntity.getAnswer());
		query.setParameter(8, qestionsEntity.getDifficulty());
		query.setParameter(9, qestionsEntity.getAnalysis());
		query.setParameter(10, qestionsEntity.getKeywords());
		query.setParameter(9, qestionsEntity.getState());
		query.setParameter(10, qestionsEntity.getMemo());
		query.setParameter(11, qestionsEntity.getId());
		int number = query.executeUpdate();
		if (number != 1) {
			result = false;
		}
		return result;
	}

	/**
	 * 获取试题list
	 */
	@Override
	public List<Object> listQestion(String hql, int pageSize, int page,
			List params) {
		List<Object> listQestion = super.queryListObjectAllForPage(hql,
				pageSize, page, params);
		return listQestion;
	}

	/**
	 * 分页
	 */
	@Override
	public PageModel<Object> getPageModel(QestionsQueryCondition queryParam) {
		PageModel<Object> pageModel = new PageModel<Object>();
		StringBuffer str = new StringBuffer(" FROM QestionsEntity WHERE 1=?");
		QestionsEntity qestionsEntity = null;
		// StringBuffer str = new
		// StringBuffer("SELECT q.id,q.question,q.attachment,q.questionType,q.difficulty,q.state,c.name FROM QestionsEntity q,CourseEntity c WHERE q.courseId=c.id");
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		if (StringUtil.isNotBlank(queryParam.getQuestion())) {
			str.append(" AND question like '%" + queryParam.getQuestion()
					+ "%'");
		}
		if (queryParam.getQuestionType() != 0) {
			str.append(" AND questionType = ? ");
			params.add(queryParam.getQuestionType());
		}
		if (queryParam.getDifficulty() != 0) {
			str.append(" AND difficulty = ? ");
			params.add(queryParam.getDifficulty());
		}
		if (queryParam.getState() != null) {
			str.append(" AND state = ? ");
			params.add(queryParam.getState());
		}
		str.append("ORDER BY operateTime DESC");
		List<Object> list = super.queryListObjectAllForPage(str.toString(),
				queryParam.getPageSize(), queryParam.getPage(), params);
		if (list != null) {
			for (Object object : list) {
				qestionsEntity = (QestionsEntity) object;
				String hql3 = "SELECT name FROM CourseEntity WHERE id=?";
				Query query1 = getSession().createQuery(hql3);
				query1.setParameter(0, qestionsEntity.getCourseId());
				String courseName = (String) query1.uniqueResult();
				qestionsEntity.setCourseName(courseName);
			}
		}
		String hql2 = "select count(*) " + str;
		long num = super.count2(hql2, params);
		int count = (int) num;

		pageModel.setList(list);
		pageModel.setTotalRecord(count);
		pageModel.setPageNum(queryParam.getPage());
		pageModel.setPageSize(Constant.PAGE_SIZE);
		return pageModel;
	}

	@Override
	public Date getCurrentTime() {
		return null;
	}

	@Override
	public String getUserId() {
		return null;
	}

}
