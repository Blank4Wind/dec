package com.peach.dec.core.stu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.peach.dec.core.exam.entity.ExamAnswer;
import com.peach.dec.core.exam.entity.ExamPlanAnswer;
import com.peach.dec.core.exam.entity.RecordsEntity;
import com.peach.dec.core.exam.service.RecordsService;
import com.peach.dec.core.qes.entity.ExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperEntity;
import com.peach.dec.core.qes.service.ExamPaperService;
import com.peach.dec.core.stu.entity.MessageDetailsEntity;
import com.peach.dec.core.stu.entity.PlanDetailEntity;
import com.peach.dec.core.stu.entity.RecordsDetailsEntity;
import com.peach.dec.core.stu.service.StuService;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.constant.Constant;
import com.peach.dec.util.util.PageModel;

/**
 * 控制层：学生模块的相关操作
 * 
 * @author zhaowei
 * @date 2015年5月21日
 */

@Controller
@Scope("prototype")
public class StuAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private StuService stuService;
	@Resource
	private RecordsService recordsService;
	private UserEntity user;
	List<PlanDetailEntity> plan;
	List<MessageDetailsEntity> assist;
	PageModel<RecordsDetailsEntity> records;
	RecordsDetailsEntity recordsDetails;
	int page = Constant.PAGE_FIRST;
	HttpServletRequest request = ServletActionContext.getRequest();
	UserEntity user1 = (UserEntity) request.getSession().getAttribute("user");
	private ExamPaperEntity examPaperEntity;
	private QesExamPaperEntity qesExamPaperEntity;
	private String id;
	private ExamAnswer examAnswer;
	@Resource
	private ExamPaperService examPaperService;
	static List<ExamAnswer> examAnswers = new ArrayList<ExamAnswer>();
	private List<ExamAnswer> examAnswerList;
	static Date date;
	private int minutes;
	private int mm;

	public String getExamPlan() throws Exception {
		plan = stuService.getExamPlan(user1);
		return "getExamPlan";
	}

	public String getExamRecords() throws Exception {
		int pageSize = Constant.PAGE_SIZE;

		records = stuService.getExamRecords(user1, pageSize, page,
				recordsDetails);

		records = stuService.getExamRecords(user1, pageSize, page,
				recordsDetails);

		return "getExamRecords";
	}

	public String getAssistMessage() throws Exception {
		assist = stuService.getAssistMessage(user1);
		return "getAssistMessage";
	}

	/**
	 * 开始考试
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exam() throws Exception {
		qesExamPaperEntity = examPaperService.exam(id);
		if (ExamPlanAnswer.studentMap.get(id) != null) {
			examAnswerList = ExamPlanAnswer.studentMap.get(id).get(
					user1.getId());
		}
		RecordsEntity recordsEntity = new RecordsEntity();
		recordsEntity.setUserId(user1.getId());
		recordsEntity.setExamPlanId(id);
		if (!recordsService.getRecordsEntity(recordsEntity)) {
			recordsEntity.setStartTime(new Date());
			recordsEntity.setIsPass((short) 3);
			@SuppressWarnings("unused")
			String result = recordsService.insertRecords(recordsEntity);
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put(user1.getId(), qesExamPaperEntity.getTotalMinutes());
			ExamPlanAnswer.mess.put(id, map);
		}
		if (ExamPlanAnswer.mess.get(id) != null) {
			if (ExamPlanAnswer.mess.get(id).get(user1.getId()) != 0) {
				mm = ExamPlanAnswer.mess.get(id).get(user1.getId());
			}
		} else {
			mm = qesExamPaperEntity.getTotalMinutes();
		}
		return "exam";
	}

	/**
	 * 接收学员的答案
	 * 
	 * @return
	 * @throws Exception
	 */
	public String params() throws Exception {

		Map<String, List<ExamAnswer>> map = new HashMap<String, List<ExamAnswer>>();
		examAnswers.add(examAnswer);
		map = new HashMap<String, List<ExamAnswer>>();
		map.put(user1.getId(), examAnswers);
		ExamPlanAnswer.studentMap.put(id, map);
		Map<String, List<ExamAnswer>> allStuAnswer = ExamPlanAnswer.studentMap
				.get(id);
		List<ExamAnswer> stuAnswer = allStuAnswer.get(user1.getId());
		if (stuAnswer != null) {
			for (int i = 0; i < stuAnswer.size(); i++) {
				for (int j = i + 1; j < stuAnswer.size(); j++) {
					if (stuAnswer.get(i).getId()
							.equals(stuAnswer.get(j).getId())) {
						if (j > i) {
							stuAnswer.remove(stuAnswer.get(i));
						} else {
							stuAnswer.remove(stuAnswer.get(j));
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 提交答卷
	 * 
	 * @return
	 * @throws Exception
	 */
	public String submitExam() throws Exception {
		date = new Date();
		List<ExamAnswer> examAnswers = ExamPlanAnswer.answerMap.get(id);
		Map<String, List<ExamAnswer>> allStuAnswer = ExamPlanAnswer.studentMap
				.get(id);
		List<ExamAnswer> stuAnswer = allStuAnswer.get(user1.getId());
		int point = 0;
		if (examAnswers != null) {
			for (ExamAnswer examAnswer1 : examAnswers) {
				for (ExamAnswer examAnswer2 : stuAnswer) {
					if (examAnswer1.getId().equals(examAnswer2.getId())
							&& examAnswer1.getAnswer().equals(
									examAnswer2.getAnswer())) {
						int j = 0, m = 0, s = 0;
						if (examAnswer1.getJudgeEachPoint() != null) {
							j = examAnswer1.getJudgeEachPoint();
							point += j;
						}
						if (examAnswer1.getMultiOptionEachPoint() != null) {
							m = examAnswer1.getMultiOptionEachPoint();
							point += m;
						}
						if (examAnswer1.getSingleOptionEachPoint() != null) {
							s = examAnswer1.getSingleOptionEachPoint();
							point += s;
						}

					}
				}

			}
		}
		short isPass = 3;
		RecordsEntity recordsEntity = new RecordsEntity();
		if (qesExamPaperEntity.getPassPoint() > point) {
			isPass = 2;
		} else if (qesExamPaperEntity.getPassPoint() <= point) {
			isPass = 1;
		} else {
			isPass = 3;
		}
		recordsEntity.setExamPlanId(id);
		recordsEntity.setUserId(user1.getId());
		recordsEntity.setIsPass(isPass);
		recordsEntity.setGetPoint(point);
		recordsEntity.setSubmitTime(date);
		recordsService.updateRecords(recordsEntity);
		return "submitExam";
	}

	public void submitTime() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(user1.getId(), minutes);
		ExamPlanAnswer.mess.put(id, map);
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<PlanDetailEntity> getPlan() {
		return plan;
	}

	public void setPlan(List<PlanDetailEntity> plan) {
		this.plan = plan;
	}

	public List<MessageDetailsEntity> getAssist() {
		return assist;
	}

	public void setAssist(List<MessageDetailsEntity> assist) {
		this.assist = assist;
	}

	public PageModel<RecordsDetailsEntity> getRecords() {
		return records;
	}

	public void setRecords(PageModel<RecordsDetailsEntity> records) {
		this.records = records;
	}

	public RecordsDetailsEntity getRecordsDetails() {
		return recordsDetails;
	}

	public void setRecordsDetails(RecordsDetailsEntity recordsDetails) {
		this.recordsDetails = recordsDetails;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public ExamPaperEntity getExamPaperEntity() {
		return examPaperEntity;
	}

	public void setExamPaperEntity(ExamPaperEntity examPaperEntity) {
		this.examPaperEntity = examPaperEntity;
	}

	public QesExamPaperEntity getQesExamPaperEntity() {
		return qesExamPaperEntity;
	}

	public void setQesExamPaperEntity(QesExamPaperEntity qesExamPaperEntity) {
		this.qesExamPaperEntity = qesExamPaperEntity;
	}

	public ExamPaperService getExamPaperService() {
		return examPaperService;
	}

	public void setExamPaperService(ExamPaperService examPaperService) {
		this.examPaperService = examPaperService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ExamAnswer getExamAnswer() {
		return examAnswer;
	}

	public void setExamAnswer(ExamAnswer examAnswer) {
		this.examAnswer = examAnswer;
	}

	public List<ExamAnswer> getExamAnswerList() {
		return examAnswerList;
	}

	public void setExamAnswerList(List<ExamAnswer> examAnswerList) {
		this.examAnswerList = examAnswerList;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getMm() {
		return mm;
	}

	public void setMm(int mm) {
		this.mm = mm;
	}

}
