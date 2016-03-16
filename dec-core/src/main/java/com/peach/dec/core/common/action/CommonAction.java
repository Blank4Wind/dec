package com.peach.dec.core.common.action;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.peach.dec.core.common.service.CommonService;
import com.peach.dec.core.sys.role.entity.PowerEntity;
import com.peach.dec.core.sys.user.entity.UserEntity;

/**
 * 控制层：公共模块的相关操作
 * 
 * @author peach
 * @date 2015年5月18日
 */
@Controller
@Scope("prototype")
public class CommonAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Resource
	private CommonService commonService;
	private UserEntity user;
	private String code;

	private String password;
	private String error;
	private String success;

	private String oldpwd;
	private String newpwd;
	private String vailcode;

	private PowerEntity powerEntity;

	/**
	 * 登录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String vailcode = request.getParameter("kaptcha");
		String imgcode = String.valueOf(request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY));
		user = commonService.login(code, password);
		if (vailcode.trim().equals(imgcode)) {
			if (user == null) {
				error = "<script>layer.msg('用户名或密码错误',{icon: 2});</script>";
				return "tologinpage";
			} else {
				String roleId = commonService.queryRoleId(code, password);
				if (roleId != null) {
					powerEntity = commonService.queryPower(roleId);
				}
				request.getSession().setAttribute("user", user);
				return "login";
			}
		} else {
			error = "<script>layer.msg('验证码错误');</script>";
			return "tologinpage";
		}

	}

	/**
	 * 注销
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().invalidate();
		return "tologinpage";
	}

	/**
	 * 个人资料查询
	 * 
	 * @return
	 */
	public String beforeUpdate() throws Exception {
		return "beforeUpdate";
	}

	/**
	 * 根据id修改个人资料
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateInfo() throws Exception {
		user = commonService.updateInfo(user);
		if (user == null) {
			error = "<script>layer.msg('修改个人资料失败',{icon: 2});</script>";
		} else {
			success = "<script>layer.msg('修改个人资料成功',{icon: 1});</script>";
		}
		return "beforeUpdate";
	}

	/**
	 * 验证原密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String isPasswdTrue() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		user = (UserEntity) request.getSession().getAttribute("user");
		String id = user.getId();
		boolean result = commonService.isPasswdTrue(id, oldpwd);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (result) {

		} else {
			pw.print("原密码错误");
		}
		pw.flush();
		pw.close();
		return null;
	}

	/**
	 * 根据id修改密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatePwd() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		user = (UserEntity) request.getSession().getAttribute("user");
		boolean isResult = commonService.isPasswdTrue(user.getId(), oldpwd);
		if (isResult) {
			boolean result = commonService.updatePwd(user.getId(), newpwd);
			if (result) {
				return "tologinpage";
			} else {
				error = "<script>layer.msg('密码修改失败',{icon : 2});</script>";
				return null;
			}
		} else {
			error = "<script>layer.msg('原密码错误');</script>";
			return "login";
		}

	}

	/**
	 * 转到修改密码的页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdatePwd() throws Exception {
		return "toUpdatePwd";
	}

	/**
	 * 去备份页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String goBackUpReduction() throws Exception {

		return "goBackUpReduction";
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getVailcode() {
		return vailcode;
	}

	public void setVailcode(String vailcode) {
		this.vailcode = vailcode;
	}

	public PowerEntity getPowerEntity() {
		return powerEntity;
	}

	public void setPowerEntity(PowerEntity powerEntity) {
		this.powerEntity = powerEntity;
	}

}
