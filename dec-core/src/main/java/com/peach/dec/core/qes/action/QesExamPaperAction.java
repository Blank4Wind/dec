package com.peach.dec.core.qes.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.peach.dec.core.qes.entity.ExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperEntity;
import com.peach.dec.core.qes.entity.QesExamPaperQueryCondition;
import com.peach.dec.core.qes.service.ExamPaperService;
import com.peach.dec.core.qes.service.QestionsService;
import com.peach.dec.util.constant.Constant;
import com.peach.dec.util.util.PageModel;

/**
 * 控制层：试卷的相关操作
 * 
 * @author guosong
 * @version 2015年5月26日 下午4:46:292015年5月26日
 * @explain
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class QesExamPaperAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 计算出单选题的总个数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String calcSingleNumber() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Long result = examPaperService.calcSingleNumber(examPaperEntity);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (result != null) {
			if (examPaperEntity.getSingleOptionNumber() > result) {
				pw.print("已超过单选题总个数,最大为" + result);
			} else {

			}
		}
		pw.flush();
		pw.close();
		return null;
	}

	/**
	 * 计算出多选题的总个数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String calcMultiNumber() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Long result = examPaperService.calcMultiNumber(examPaperEntity);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (result != null) {
			if (examPaperEntity.getMultiOptionNumber() > result) {
				pw.print("已超过多选题总个数,最大为" + result);
			} else {

			}
		}
		pw.flush();
		pw.close();
		return null;
	}

	/**
	 * 计算出判断题的总个数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String calcJudgeNumber() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Long result = examPaperService.calcJudgeNumber(examPaperEntity);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (result != null) {
			if (examPaperEntity.getJudgeNumber() > result) {
				pw.print("已超过判断题总个数,最大为" + result);
			} else {

			}
		}
		pw.flush();
		pw.close();
		return null;
	}

	/**
	 * 重新生成试卷
	 * 
	 * @throws IOException
	 */
	public String reCreateQesExamPaper() throws IOException {
		examPaperService.reGetQesExamPaperEntity(examId);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter pw = response.getWriter();
		pw.write(examPaperEntity.getId());
		pw.flush();
		pw.close();
		return "reCreateQesExamPaper";
	}

	/**
	 * 转到试卷页面
	 */
	public String toQesExamPaper() {
		if (condition == null) {
			condition = new QesExamPaperQueryCondition();
			condition.setPage(Constant.PAGE_FIRST);
			condition.setState((short) 0);
		} else {
			condition.setPage(page);
		}
		if (condition.getPage() < 1) {
			condition.setPage(1);
		}
		condition.setPageSize(Constant.PAGE_SIZE);
		pageModel = examPaperService.getQesExamEntity(condition);
		return "toQesExamPaper";
	}

	/**
	 * 试卷生成
	 */
	public String addExamPaper() {
		if (examPaperEntity == null) {
			examPaperEntity = new ExamPaperEntity();
		}
		examPaperEntity.setOpreateUserId(examPaperService.getUserId());
		examPaperEntity.setOpreateTime(examPaperService.getCurrentTime());
		examPaperEntity.setState((short) 1);
		if (examPaperEntity.getMemo() == null) {
			examPaperEntity.setMemo(" ");
		}
		if (examPaperEntity.getJudgeEachPoint() == null) {
			examPaperEntity.setJudgeEachPoint((short) 0);
		}
		if (examPaperEntity.getJudgeNumber() == null) {
			examPaperEntity.setJudgeNumber((short) 0);
		}
		if (examPaperEntity.getMultiOptionEachPoint() == null) {
			examPaperEntity.setJudgeEachPoint((short) 0);
		}
		if (examPaperEntity.getMultiOptionNumber() == null) {
			examPaperEntity.setMultiOptionNumber((short) 0);
		}
		if (examPaperEntity.getSingleOptionEachPoint() == null) {
			examPaperEntity.setSingleOptionEachPoint((short) 0);
		}
		if (examPaperEntity.getSingleOptionNumber() == null) {
			examPaperEntity.setSingleOptionNumber((short) 0);
		}
		examPaperService.addExamPaper(examPaperEntity);
		return "transPaper";
	}

	/**
	 * 转到添加试卷页面
	 */
	public String toAddQesExamPaper() {
		return "toAddQesExamPaper";
	}

	/**
	 * 试卷预览
	 */
	public String viewExamPaper() {
		qesExamPaperEntity = (QesExamPaperEntity) examPaperService
				.getQesExamPaperEntity(id).get(0);
		examPaperEntity = (ExamPaperEntity) examPaperService
				.getQesExamPaperEntity(id).get(1);
		return "viewExamPaper";
	}

	/**
	 * 修改状态
	 * 
	 * @return
	 * @throws IOException
	 */
	public String updateState() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		short a = 0;
		PrintWriter pw = response.getWriter();
		if (state == 1) {
			a = 2;
		} else {
			a = 1;
		}
		boolean result = examPaperService.setState(id, a);
		if (result) {
			pw.print(a);
		} else {
			pw.print((short) 0);
		}
		pw.flush();
		pw.close();
		return null;
	}

	@Resource
	private QestionsService qestionsService;
	@Resource
	private ExamPaperService examPaperService;
	private QesExamPaperQueryCondition condition;
	private PageModel<Object> pageModel;
	private ExamPaperEntity examPaperEntity;
	private QesExamPaperEntity qesExamPaperEntity;
	private List<Object> listClazzName;
	private String id;
	private int page;
	private short state;
	private String examId;

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public QesExamPaperQueryCondition getCondition() {
		return condition;
	}

	public void setCondition(QesExamPaperQueryCondition condition) {
		this.condition = condition;
	}

	public PageModel<Object> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<Object> pageModel) {
		this.pageModel = pageModel;
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

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public QesExamPaperEntity getQesExamPaperEntity() {
		return qesExamPaperEntity;
	}

	public void setQesExamPaperEntity(QesExamPaperEntity qesExamPaperEntity) {
		this.qesExamPaperEntity = qesExamPaperEntity;
	}

	public List<Object> getListClazzName() {
		return examPaperService.listClazzName();
	}

	public void setListClazzName(List<Object> listClazzName) {
		this.listClazzName = listClazzName;
	}

}
