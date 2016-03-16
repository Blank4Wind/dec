package com.peach.dec.core.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.peach.dec.core.sys.user.entity.UserEntity;

public class CustomInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 获取request
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取session
		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		// 获取请求的url
		String path = request.getRequestURI();
		// 判断是否登录
		if (path.indexOf("common_login.action") != -1
				|| path.indexOf("common_logout.action") != -1) {
			return invocation.invoke();
		} else if (user == null) {
			// 重定向到登录界面
			return "tologinpage2";
		} else {
			return invocation.invoke();
		}

	}

}
