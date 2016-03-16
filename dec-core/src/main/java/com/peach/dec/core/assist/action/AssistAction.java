package com.peach.dec.core.assist.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.peach.dec.core.assist.entity.MessageEntity;
import com.peach.dec.core.assist.service.AssistService;
import com.peach.dec.core.common.service.CommonService;
import com.peach.dec.core.stu.entity.MessageDetailsEntity;
import com.peach.dec.core.sys.clazz.entity.ClassEntity;
import com.peach.dec.core.sys.clazz.service.ClazzService;
import com.peach.dec.core.sys.role.entity.RoleEntity;
import com.peach.dec.core.sys.role.service.RoleService;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.util.constant.Constant;
import com.peach.dec.util.util.PageModel;

/**
 * 控制层：辅助模块站内消息的相关操作
 * 
 * @author zhaowei
 * @date 2015年5月24日
 */

@Controller
@Scope("prototype")
public class AssistAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private AssistService assisService;
	@Resource
	private RoleService roleService;
	@Resource
	private ClazzService clazzService;
	@Resource
	private CommonService userService;

	private List<UserEntity> userList;
	private List<ClassEntity> classEntity;
	private PageModel<MessageDetailsEntity> assist;
	private MessageDetailsEntity messageDetails;
	private MessageDetailsEntity assistDetils;
	private MessageEntity message;
	private int page = Constant.PAGE_FIRST;
	private int pageSize = Constant.PAGE_SIZE;
	private String beOverdue;
	private List<RoleEntity> roleList;

	private String success;
	private String error;

	/**
	 * 查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAssistMessage() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		UserEntity user1 = (UserEntity) request.getSession().getAttribute(
				"user");
		messageDetails.setSenderId(user1.getId());
		assist = assisService.getAssistMessage(messageDetails, pageSize, page,
				beOverdue);
		roleList = roleService.getRole();
		return "getAssistMessage";
	}

	/**
	 * 到添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String goAddAssistMessage() throws Exception {
		classEntity = clazzService.classlist();
		userList = new LinkedList<UserEntity>();
		for (ClassEntity classEntity2 : classEntity) {
			userList.addAll(userService.getUser(classEntity2));
		}

		return "goAddAssistMessage";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public void addAssistMessage() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserEntity user = (UserEntity) request.getSession()
				.getAttribute("user");
		message.setSenderId(user.getId());
		message.setSendTime(new Date());
		boolean result = assisService.addAssistMessage(message);
		if (!result) {
			error = "<script>layer.msg('新增失败',{icon: 2});</script>";
		} else {
			success = "<script>layer.msg('新增成功',{icon: 1});</script>";
			request.getRequestDispatcher(
					"/assist/assist_getAssistMessage.action?messageDetails.uname=true")
					.forward(request, response);
		}
	}

	/**
	 * 去修改页面
	 * 
	 * @return
	 * @throws IOException
	 */
	public String goUpdateAssistMessage() throws IOException {
		assistDetils = assisService.getAssistEntity(message);
		classEntity = clazzService.classlist();
		userList = new LinkedList<UserEntity>();
		for (ClassEntity classEntity2 : classEntity) {
			userList.addAll(userService.getUser(classEntity2));
		}
		return "goUpdateAssistMessage";
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public void updateAssistMessages() throws IOException, ServletException {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean result = assisService.updateAssistMessages(assistDetils);
		if (!result) {
			error = "<script>layer.msg('修改失败',{icon: 2});</script>";
		} else {
			success = "<script>layer.msg('修改成功',{icon: 1});</script>";
			request.getRequestDispatcher(
					"/assist/assist_getAssistMessage.action?messageDetails.uname=true")
					.forward(request, response);
		}

	}

	/**
	 * 修改删除状态
	 * 
	 * @throws IOException
	 */
	public void updateAssistMessage() throws IOException {
		boolean result = assisService.updateAssistMessage(message);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (result) {
			pw.print(message.getState());
		} else {
			pw.print(0);
		}
		pw.flush();
		pw.close();
	}

	public PageModel<MessageDetailsEntity> getAssist() {
		return assist;
	}

	public void setAssist(PageModel<MessageDetailsEntity> assist) {
		this.assist = assist;
	}

	public MessageDetailsEntity getMessageDetails() {
		return messageDetails;
	}

	public void setMessageDetails(MessageDetailsEntity messageDetails) {
		this.messageDetails = messageDetails;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getBeOverdue() {
		return beOverdue;
	}

	public void setBeOverdue(String beOverdue) {
		this.beOverdue = beOverdue;
	}

	public List<RoleEntity> getRoleList() {
		return roleList;
	}

	public MessageEntity getMessage() {
		return message;
	}

	public void setMessage(MessageEntity message) {
		this.message = message;
	}

	public List<ClassEntity> getClassEntity() {
		return classEntity;
	}

	public void setClassEntity(List<ClassEntity> classEntity) {
		this.classEntity = classEntity;
	}

	public List<UserEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
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

	public MessageDetailsEntity getAssistDetils() {
		return assistDetils;
	}

	public void setAssistDetils(MessageDetailsEntity assistDetils) {
		this.assistDetils = assistDetils;
	}

}
