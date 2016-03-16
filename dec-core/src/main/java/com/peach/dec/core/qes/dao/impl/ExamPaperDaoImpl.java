package com.peach.dec.core.qes.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.peach.dec.core.common.dao.impl.BaseHibernateDao;
import com.peach.dec.core.qes.dao.ExamPaperDao;
import com.peach.dec.core.qes.entity.ExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperQueryCondition;
import com.peach.dec.core.qes.entity.QestionsEntity;
import com.peach.dec.core.sys.course.entity.CourseEntity;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.constant.Constant;
import com.peach.dec.util.util.PageModel;
import com.peach.dec.util.util.StringUtil;

/**
 * @author guosong
 * @version 2015年5月28日 上午10:30:042015年5月28日
 * @explain dao：试卷的相关操作
 */

@Repository
@SuppressWarnings("all")
public class ExamPaperDaoImpl extends BaseHibernateDao<Object, String>
		implements ExamPaperDao {

	/**
	 * 试卷分页查询
	 */
	@Override
	public PageModel<Object> getQesExamEntity(
			QesExamPaperQueryCondition condition) {
		PageModel<Object> pageModel = new PageModel<Object>();
		StringBuffer str = new StringBuffer(
				"SELECT epe.id,epe.name as ename,cu.name as cname,epe.totalPoint,epe.passPoint,epe.totalMinutes,epe.state,ue.name,epe.paperType,epe.singleOptionNumber,epe.singleOptionEachPoint,epe.singleOptionDifficulty,epe.multiOptionNumber,epe.multiOptionEachPoint,epe.multiOptionDifficulty,epe.judgeNumber,epe.judgeEachPoint,epe.judgeDifficulty,epe.opreateTime,epe.memo FROM ExamPaperEntity epe,CourseEntity cu,UserEntity ue WHERE epe.courseId=cu.id AND epe.opreateUserId=ue.id");
		StringBuffer str1 = new StringBuffer(
				"FROM ExamPaperEntity epe,CourseEntity cu,UserEntity ue WHERE epe.courseId=cu.id AND epe.opreateUserId=ue.id");
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
	public void addExamPaper(ExamPaperEntity examPaperEntity) {

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
			list = super.getSession().createSQLQuery(singlestr.toString())
					.list();
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
			list = super.getSession().createSQLQuery(multistr.toString())
					.list();
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
			list = super.getSession().createSQLQuery(judgestr.toString())
					.list();
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
		super.getSession().saveOrUpdate(examPaperEntity);
		super.getSession().flush();
	}

	/**
	 * 获取当前userID
	 */
	public String getUserId() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userId;
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		userId = userEntity.getId();
		return userId;
	}

	/**
	 * 获取当前时间
	 */
	public Date getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			date = new Date();
			e.printStackTrace();
		}
		return date;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getQesExamPaperEntity(String id) {
		List<Object> listobj = new ArrayList<Object>();
		String hql = "SELECT paperUrl FROM ExamPaperEntity WHERE id=:id";
		String hql1 = "SELECT singleOptionNumber,singleOptionEachPoint,multiOptionNumber,multiOptionEachPoint,judgeNumber,judgeEachPoint FROM ExamPaperEntity WHERE id=:id";
		ExamPaperEntity examPaperEntity = new ExamPaperEntity();
		String path = (String) super.getSession().createQuery(hql)
				.setParameter("id", id).uniqueResult();
		File file1 = new File(path);
		if (!file1.exists()) {
			this.reGetQesExamPaperEntity(id);
		}
		String hqla = "select paperUrl from ExamPaperEntity where id=:id";
		String patha = (String) super.getSession().createQuery(hqla)
				.setParameter("id", id).uniqueResult();
		File file = new File(patha);
		Scanner scanner = null;
		StringBuilder builder = new StringBuilder();
		try {
			scanner = new Scanner(file, "utf-8");
			while (scanner.hasNextLine()) {
				builder.append(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		String str = builder.toString();
		QesExamPaperEntity entity = JSON.parseObject(str,
				QesExamPaperEntity.class);
		List<Object[]> list = new ArrayList<Object[]>();
		list = super.getSession().createQuery(hql1).setParameter("id", id)
				.list();
		examPaperEntity.setSingleOptionNumber((Short) list.get(0)[0]);
		examPaperEntity.setSingleOptionEachPoint((Short) list.get(0)[1]);
		examPaperEntity.setMultiOptionNumber((Short) list.get(0)[2]);
		examPaperEntity.setMultiOptionEachPoint((Short) list.get(0)[3]);
		examPaperEntity.setJudgeNumber((Short) list.get(0)[4]);
		examPaperEntity.setJudgeEachPoint((Short) list.get(0)[5]);

		listobj.add(entity);
		listobj.add(examPaperEntity);
		return listobj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reGetQesExamPaperEntity(String id) {
		String hql = "SELECT e.id,e.name,e.paperUrl,e.paperType,e.courseId,e.totalPoint,e.passPoint,e.totalMinutes,e.singleOptionNumber,e.singleOptionEachPoint,e.singleOptionDifficulty,e.multiOptionNumber,e.multiOptionEachPoint,e.multiOptionDifficulty,e.judgeNumber,e.judgeEachPoint,e.judgeDifficulty,e.opreateUserId,e.opreateTime,e.state,e.memo,c.name FROM ExamPaperEntity e,CourseEntity c WHERE e.id=:id AND c.id=e.courseId";
		List<Object[]> listobj = new ArrayList<Object[]>();
		ExamPaperEntity entity = new ExamPaperEntity();
		listobj = super.getSession().createQuery(hql).setParameter("id", id)
				.list();
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
	 * 开始考试
	 */
	@Override
	public QesExamPaperEntity exam(String id) {
		String hql = "SELECT e.paperUrl FROM PlanEntity p,ExamPaperEntity e WHERE p.examPaperId=e.id AND p.id=?";
		String paperUrl = (String) getSession().createQuery(hql)
				.setParameter(0, id).uniqueResult();
		File file = new File(paperUrl);
		FileInputStream fileInput = null;
		String strFile = null;
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
		String hql2 = "SELECT e.singleOptionNumber,e.singleOptionEachPoint,e.multiOptionNumber,e.multiOptionEachPoint,e.judgeNumber,e.judgeEachPoint FROM PlanEntity p,ExamPaperEntity e WHERE p.examPaperId=e.id AND p.id=?";
		Object[] list = (Object[]) getSession().createQuery(hql2)
				.setParameter(0, id).uniqueResult();
		ExamPaperEntity examPaperEntity = new ExamPaperEntity();
		if (list != null) {
			short singleOptionNumber = (Short) list[0];
			short singleOptionEachPoint = (Short) list[1];
			short multiOptionNumber = (Short) list[2];
			short multiOptionEachPoint = (Short) list[3];
			short judgeNumber = (Short) list[4];
			short judgeEachPoint = (Short) list[5];
			examPaperEntity.setSingleOptionNumber(singleOptionNumber);
			examPaperEntity.setSingleOptionEachPoint(singleOptionEachPoint);
			examPaperEntity.setMultiOptionNumber(multiOptionNumber);
			examPaperEntity.setMultiOptionEachPoint(multiOptionEachPoint);
			examPaperEntity.setJudgeNumber(judgeNumber);
			examPaperEntity.setJudgeEachPoint(judgeEachPoint);
		}
		qPaperEntity.setExamPaperEntity(examPaperEntity);
		return qPaperEntity;
	}

	@Override
	public Long calcSingleNumber(ExamPaperEntity examPaperEntity) {
		String hql = "SELECT COUNT(*) from QestionsEntity WHERE questionType=1";
		Long number = (Long) getSession().createQuery(hql).uniqueResult();
		return number;
	}

	public Long calcMultiNumber(ExamPaperEntity examPaperEntity) {
		String hql = "SELECT COUNT(*) from QestionsEntity WHERE questionType=2";
		Long number = (Long) getSession().createQuery(hql).uniqueResult();
		return number;
	}

	public Long calcJudgeNumber(ExamPaperEntity examPaperEntity) {
		String hql = "SELECT COUNT(*) from QestionsEntity WHERE questionType=3";
		Long number = (Long) getSession().createQuery(hql).uniqueResult();
		return number;
	}
}
