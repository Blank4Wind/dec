package com.peach.dec.core.sys.clazz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.peach.dec.core.sys.clazz.dao.impl.QueryParam;
import com.peach.dec.core.sys.clazz.entity.ClassEntity;
import com.peach.dec.core.sys.clazz.service.ClazzService;
import com.peach.dec.core.sys.course.entity.CourseEntity;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.constant.Constant;
import com.peach.dec.util.util.PageModel;

@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class ClazzAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String success;
	private String error;

	/**
	 * 显示，查询所有班级
	 * 
	 * @return
	 */
	public String toClazzPage() {
		if (queryParam == null) {
			queryParam = new QueryParam();
			queryParam.setPage(Constant.PAGE_FIRST);
		} else {
			queryParam.setPage(page);
		}
		if (queryParam.getPage() < 1) {
			queryParam.setPage(1);
		}
		queryParam.setPageSize(Constant.PAGE_SIZE);
		pageModel = clazzService.getPageModel(queryParam);

		return "toClazzPage";
	}

	/**
	 * 检查班级名称是否存在
	 * 
	 * @throws IOException
	 */
	public String checkAccount() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		boolean result = clazzService.checkAccount(classEntity.getName());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (result) {
		} else {
			pw.print("班级名已被占用");
		}
		pw.flush();
		pw.close();
		return null;
	}

	/**
	 * 得到班级详细信息
	 * 
	 * @return
	 */
	public String getClassInfo() {
		String id = classEntity.getId();
		classEntity = clazzService.getClassResult(id);
		return "toClazzInfo";
	}

	/**
	 * 跳转添加班级页面
	 * 
	 * @return
	 */
	public String toAddClazzPage() {
		listClazzName = clazzService.listClazzName();
		return "toAddClazzPage";
	}

	/**
	 * 修改班级信息
	 * 
	 * @return
	 */
	public String updateClassEntityInfo() {
		classEntity.setOperateTime(clazzService.getCurrentTime());
		classEntity.setOperateUserId(clazzService.getUserId());
		classEntity.setCourseId(clazzService.getCourseId(classEntity
				.getCourseName()));
		boolean result = clazzService.updateClassEntity(classEntity);
		if (result) {
			success = "<script>layer.msg('修改成功',{icon: 1});</script>";
			return "selectClazz";
		} else {
			error = "<script>layer.msg('修改失败',{icon: 2});</script>";
			return "toClazzInfo";
		}
	}

	/**
	 * 修改班级状态
	 * 
	 * @return
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
		boolean result = clazzService.setState(id, a);
		if (result) {
			pw.print(a);
		} else {
			pw.print((short) 0);
		}
		pw.flush();
		pw.close();
		return null;
	}

	/**
	 * 添加班级
	 * 
	 * @return
	 */
	public String addClass() {
		classEntity
				.setCourseId(clazzService.getCourseId(courseEntity.getName()));
		classEntity.setOperateUserId(clazzService.getUserId());
		classEntity.setState((short) 1);
		classEntity.setOperateTime(clazzService.getCurrentTime());
		boolean result = clazzService.addClass(classEntity);
		if (result) {
			success = "<script>layer.msg('新增成功',{icon: 1});</script>";
			return "selectClazz";
		} else {
			error = "<script>layer.msg('新增失败',{icon: 2});</script>";
			return "toAddClazzPage";
		}
	}

	@Resource
	private ClazzService clazzService;

	private List<Object> listClazzName;

	private PageModel<Object> pageModel;

	private ClassEntity classEntity;

	private CourseEntity courseEntity;

	private UserEntity userEntity;

	private QueryParam queryParam;

	private String id;
	private short state;
	private int page;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public QueryParam getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(QueryParam queryParam) {
		this.queryParam = queryParam;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public CourseEntity getCourseEntity() {
		return courseEntity;
	}

	public void setCourseEntity(CourseEntity courseEntity) {
		this.courseEntity = courseEntity;
	}

	public ClassEntity getClassEntity() {
		return classEntity;
	}

	public void setClassEntity(ClassEntity classEntity) {
		this.classEntity = classEntity;
	}

	public PageModel<Object> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<Object> pageModel) {
		this.pageModel = pageModel;
	}

	public List<Object> getListClazzName() {
		return clazzService.listClazzName();
	}

	public void setListClazzName(List<Object> listClazzName) {
		this.listClazzName = listClazzName;
	}

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

}
