package com.peach.dec.core.sys.course.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.peach.dec.core.sys.course.entity.CourseEntity;
import com.peach.dec.core.sys.course.service.CourseService;
import com.peach.dec.core.sys.user.entity.UserEntity;

/**
 * 控制层：课程的相关操作
 * 
 * @author peach
 * @date 2015年5月21日
 */
@Controller
@Scope("prototype")
public class CourseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Resource
	private CourseService courseService;
	private String id;
	private CourseEntity courseEntity;
	private List<CourseEntity> courseList;
	private String courseJson;

	private String parentName;

	private String success;
	private String error;

	private short status;

	/**
	 * 转到课程显示页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toCoursePage() throws Exception {
		courseList = courseService.list();
		courseJson = JSON.toJSONString(courseList);
		return "toCourseShow";
	}

	/**
	 * 点击左侧树形显示具体的课程
	 * 
	 * @return
	 * @throws Exception
	 */
	public String courseData() throws Exception {
		courseList = courseService.courseData(id);
		return "courseData";
	}

	/**
	 * 新增课程
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insertCourse() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserEntity user = (UserEntity) request.getSession()
				.getAttribute("user");
		String result = courseService.insertCourse(user.getId(), courseEntity,
				parentName);
		if (result == null) {
			error = "<script>layer.msg('新增失败',{icon: 2});</script>";
			return "toInsertCourse";
		} else {
			success = "<script>layer.msg('新增成功',{icon: 1});</script>";
			return "success";
		}
	}

	/**
	 * 修改前的查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String beforeUpdate() throws Exception {
		courseEntity = courseService.query(courseEntity.getId());
		String id = courseEntity.getId();
		courseList = courseService.findCondition(id);
		courseJson = JSON.toJSONString(courseList);
		return "updateQuery";
	}

	/**
	 * 修改课程
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateCourse() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserEntity user = (UserEntity) request.getSession()
				.getAttribute("user");
		courseEntity.setOperateUserId(user.getId());
		courseEntity.setOperateTime(new Date());
		int result = courseService.updateCourse(courseEntity, parentName);
		if (result != 1) {
			error = "<script>layer.msg('修改失败',{icon: 2});</script>";
			return "updateQuery";
		} else {
			success = "<script>layer.msg('修改成功',{icon: 1});</script>";
			return "updateCourse";
		}
	}

	/**
	 * 转到新增课程的页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toInsertCoursePage() throws Exception {
		courseList = courseService.show();
		courseJson = JSON.toJSONString(courseList);
		return "toInsertCourse";
	}

	/**
	 * 修改状态
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changeState() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		short status2 = 0;
		if (status == 1) {
			status2 = 2;
		}
		if (status == 2) {
			status2 = 1;
		}
		boolean result = courseService.statusChange(id, status2);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (result) {
			pw.print(status2);
		} else {
			pw.print(0);
		}

		pw.flush();
		pw.close();
		return null;
	}

	/**
	 * 判断课程名的唯一性
	 * 
	 * @return
	 * @throws Exception
	 */
	public String isCourseExist() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		boolean result = courseService.isCourseExist(courseEntity);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (result) {
		} else {
			pw.print("课程名已被占用");
		}

		pw.flush();
		pw.close();
		return null;
	}

	public CourseEntity getCourseEntity() {
		return courseEntity;
	}

	public void setCourseEntity(CourseEntity courseEntity) {
		this.courseEntity = courseEntity;
	}

	public List<CourseEntity> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<CourseEntity> courseList) {
		this.courseList = courseList;
	}

	public String getCourseJson() {
		return courseJson;
	}

	public void setCourseJson(String courseJson) {
		this.courseJson = courseJson;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
