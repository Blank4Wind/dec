package com.peach.dec.core.exam.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.peach.dec.core.common.service.CommonService;
import com.peach.dec.core.exam.entity.PlanEntity;
import com.peach.dec.core.exam.service.PlanService;
import com.peach.dec.core.qes.entity.ExamPaperEntity;
import com.peach.dec.core.qes.service.QestionsService;
import com.peach.dec.core.sys.clazz.entity.ClassEntity;
import com.peach.dec.core.sys.clazz.service.ClazzService;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.constant.Constant;
import com.peach.dec.util.util.PageModel;

/**
 * 控制层：考试安排的相关操作
 * 
 * @author peach
 * @date 2015年5月24日
 */
@Controller
@Scope("prototype")
public class PlanAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Resource
	private PlanService planService;
	@Resource
	private ClazzService clazzService;
	@Resource
	private CommonService commonService;
	@Resource
	private QestionsService qestionsService;
	private PlanEntity planEntity;
	private List<PlanEntity> planlist;
	private List<ExamPaperEntity> paperlist;
	private PageModel<PlanEntity> pageModel;
	private int pageNo = Constant.PAGE_FIRST;
	private List<ClassEntity> classlist;
	private List<UserEntity> userlist;
	private String type;
	private String timeout;
	private short status;
	private String error;
	private String success;
	private String paperId;

	/**
	 * 考试安排查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String planList() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		pageModel = planService.planList(user.getId(), pageNo,
				Constant.PAGE_SIZE, planEntity, type, timeout);
		return "planList";
	}

	/**
	 * 转到新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toInsertPlan() throws Exception {
		classlist = clazzService.classlist();
		userlist = new LinkedList<UserEntity>();
		for (ClassEntity classEntity2 : classlist) {
			userlist.addAll(commonService.getUser(classEntity2));
		}
		paperlist = planService.paperList();
		return "toInsertPlan";
	}

	/**
	 * 添加考试安排
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		UserEntity user = (UserEntity) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		planEntity.setOperateUserId(user.getId());
		planEntity.setOperateTime(new Date());
		String result = planService.insert(planEntity);
		if (result == null) {
			error = "<script>layer.msg('新增失败',{icon: 2});</script>";
			return "toInsertPlan";
		} else {
			success = "<script>layer.msg('新增成功',{icon: 1});</script>";
			return "success";
		}
	}

	/**
	 * 修改前的查询
	 */
	public String beforeUpdate() throws Exception {
		classlist = clazzService.classlist();
		userlist = new LinkedList<UserEntity>();
		for (ClassEntity classEntity2 : classlist) {
			userlist.addAll(commonService.getUser(classEntity2));
		}
		paperlist = planService.paperList();
		planEntity = planService.beforeUpdate(planEntity);
		return "beforeUpdate";
	}

	/**
	 * 修改考试安排
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		UserEntity user = (UserEntity) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		planEntity.setOperateUserId(user.getId());
		planEntity.setOperateTime(new Date());
		boolean result = planService.update(planEntity);
		if (result) {
			success = "<script>layer.msg('修改成功',{icon: 1});</script>";
			return "success";
		} else {
			error = "<script>layer.msg('修改失败',{icon: 2});</script>";
			return "beforeUpdate";
		}
	}

	/**
	 * 改变状态
	 * 
	 * @return
	 */
	public String changeState() throws Exception {
		boolean result = planService.changeState(planEntity);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (result) {
			pw.print(planEntity.getState());
		} else {
			pw.print(0);
		}
		pw.flush();
		pw.close();
		return null;
	}

	/**
	 * 查询出试卷的考试时间
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryEndTime() throws Exception {
		String endTime = planService.queryEndTime(paperId, planEntity);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (endTime.equals("1970-01-01 08:00:00")) {
			pw.print("");
		} else {
			pw.print(endTime);
		}
		pw.flush();
		pw.close();
		return null;
	}

	public PlanEntity getPlanEntity() {
		return planEntity;
	}

	public void setPlanEntity(PlanEntity planEntity) {
		this.planEntity = planEntity;
	}

	public List<PlanEntity> getPlanlist() {
		return planlist;
	}

	public void setPlanlist(List<PlanEntity> planlist) {
		this.planlist = planlist;
	}

	public List<ExamPaperEntity> getPaperlist() {
		return paperlist;
	}

	public void setPaperlist(List<ExamPaperEntity> paperlist) {
		this.paperlist = paperlist;
	}

	public PageModel<PlanEntity> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<PlanEntity> pageModel) {
		this.pageModel = pageModel;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public List<ClassEntity> getClasslist() {
		return classlist;
	}

	public void setClasslist(List<ClassEntity> classlist) {
		this.classlist = classlist;
	}

	public List<UserEntity> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<UserEntity> userlist) {
		this.userlist = userlist;
	}

	public ClazzService getClazzService() {
		return clazzService;
	}

	public void setClazzService(ClazzService clazzService) {
		this.clazzService = clazzService;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

}
