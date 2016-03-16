package com.peach.dec.core.qes.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.peach.dec.core.qes.entity.QestionsEntity;
import com.peach.dec.core.qes.entity.QestionsQueryCondition;
import com.peach.dec.core.qes.service.QestionsService;
import com.peach.dec.core.sys.course.entity.CourseEntity;
import com.peach.dec.core.sys.course.service.CourseService;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.constant.Constant;
import com.peach.dec.util.util.PageModel;

/**
 * 控制层：试题的相关操作
 * 
 * @author peach
 * @date 2015年5月22日
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class QestionsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Resource
	private QestionsService qestionsService;
	@Resource
	private CourseService courseService;
	private QestionsEntity qestionsEntity;
	private List<QestionsEntity> qeslist;
	private List<CourseEntity> courselist;
	private PageModel<Object> pageModel;
	private QestionsQueryCondition queryParam;

	public PageModel<Object> getPageModel() {

		return pageModel;
	}

	public QestionsQueryCondition getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(QestionsQueryCondition queryParam) {
		this.queryParam = queryParam;
	}

	public void setPageModel(PageModel<Object> pageModel) {
		this.pageModel = pageModel;
	}

	private String courseJson;
	private Short state;
	private String success;
	private String error;
	private String courseName;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	private String id;
	private int page;
	private int pagess;

	public int getPagess() {
		return pagess;
	}

	public void setPagess(int pagess) {
		this.pagess = pagess;
	}

	/**
	 * 通过条件查询所有的试题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String qestionList() throws Exception {
		if (queryParam == null) {
			queryParam = new QestionsQueryCondition();
			queryParam.setPage(Constant.PAGE_FIRST);
			queryParam.setState((short) 0);
		} else {
			queryParam.setPage(page);
		}
		if (queryParam.getPage() < 1) {
			queryParam.setPage(1);
		}
		queryParam.setPageSize(Constant.PAGE_SIZE);
		pageModel = qestionsService.getPageModel(queryParam);

		return "qestionlist";
	}

	/**
	 * 转到新增试题的页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toInsertQestionPage() throws Exception {
		courselist = courseService.show();
		courseJson = JSON.toJSONString(courselist);
		return "toInsertQestionPage";
	}

	/**
	 * 新增试题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insertQestion() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		String curseId = courseService.select(courseName);
		qestionsEntity.setCourseId(curseId);
		qestionsEntity.setOperateUserId(user.getId());
		qestionsEntity.setOperateTime(new Date());
		qestionsEntity.setState((short) 1);
		qestionsEntity.setMemo(qestionsEntity.getMemo());
		boolean result = qestionsService.insertQestion(qestionsEntity);
		if (result) {
			success = "<script>layer.msg('新增成功',{icon: 1});</script>";
			return "insertQestion";
		} else {
			error = "<script>layer.msg('新增失败',{icon: 2});</script>";
			return "toInsertQestionPage";
		}
	}

	/**
	 * @return 修改试题——修改前查询
	 */

	public String qestionUpdate() {
		courselist = courseService.show();
		courseJson = JSON.toJSONString(courselist);
		qestionsEntity = qestionsService.updateList(id);
		if (qestionsEntity != null) {
			if (qestionsEntity.getQuestionType() == 2) {
				String answer = qestionsEntity.getAnswer();
				List list = new ArrayList();
				String[] str = answer.split(",");
				for (int i = 0; i < str.length; i++) {
					list.add(str[i].trim());
				}
				ServletActionContext.getRequest().setAttribute("multiNumber",
						list);
			}
		}
		return "qestionUpdate";

	}

	/**
	 * @return 执行试题修改
	 * @throws Exception
	 */
	public String sureUpdate() throws Exception {
		UserEntity user = (UserEntity) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		String curseId = courseService.select(courseName);
		qestionsEntity.setCourseId(curseId);
		qestionsEntity.setOperateUserId(user.getId());
		qestionsEntity.setOperateTime(new Date());
		qestionsEntity.setId(id);
		boolean result = qestionsService.sureUpdate(qestionsEntity);
		if (result) {
			success = "<script>layer.msg('修改成功',{icon: 1});</script>";
			return "insertQestion";
		} else {
			error = "<script>layer.msg('修改失败',{icon: 2});</script>";
			return "qestionUpdate";
		}
	}

	/**
	 * @return 试题详情查看——查看前查询
	 */

	public String qestionDetail() {
		qestionsEntity = qestionsService.updateList(id);
		return "qestionDetail";

	}

	/**
	 * @return 试题详情查看
	 * @throws Exception
	 */
	public String detail() throws Exception {
		UserEntity user = (UserEntity) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		CourseEntity course = (CourseEntity) ServletActionContext.getRequest()
				.getSession().getAttribute("course");
		String curseId = courseService.select(courseName);
		qestionsEntity.setCourseId(curseId);
		// qestionsEntity.setCourseName(course.getName());
		qestionsEntity.setOperateUserId(user.getId());
		qestionsEntity.setOperateTime(new Date());
		qestionsEntity.setId(id);
		boolean result = qestionsService.detail(qestionsEntity);
		if (result) {
			success = "<script>layer.msg('查看成功',{icon: 1});</script>";
			return "updateM";
		} else {
			error = "<script>layer.msg('查看失败',{icon: 2});</script>";
			return "qestionDetail";
		}
	}

	/**
	 * 改变状态
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changeState() throws Exception {
		short status = 0;
		if (state == 1) {
			status = 2;
		}
		if (state == 2) {
			status = 1;
		}

		boolean result = qestionsService.changeState(id, status);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (result) {
			pw.print(status);
		} else {
			pw.print(0);
		}
		pw.flush();
		pw.close();
		return null;
	}

	public QestionsEntity getQestionsEntity() {
		return qestionsEntity;
	}

	public void setQestionsEntity(QestionsEntity qestionsEntity) {
		this.qestionsEntity = qestionsEntity;
	}

	public List<QestionsEntity> getQeslist() {
		return qeslist;
	}

	public void setQeslist(List<QestionsEntity> qeslist) {
		this.qeslist = qeslist;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CourseEntity> getCourselist() {
		return courselist;
	}

	public void setCourselist(List<CourseEntity> courselist) {
		this.courselist = courselist;
	}

	public String getCourseJson() {
		return courseJson;
	}

	public void setCourseJson(String courseJson) {
		this.courseJson = courseJson;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
