package com.peach.dec.core.sys.user.action;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.peach.dec.core.sys.clazz.entity.ClassEntity;
import com.peach.dec.core.sys.clazz.service.ClazzService;
import com.peach.dec.core.sys.role.entity.RoleEntity;
import com.peach.dec.core.sys.role.service.RoleService;
import com.peach.dec.core.sys.user.entity.UserEntity;
import com.peach.dec.core.sys.user.service.UserService;
import com.peach.dec.util.constant.Constant;
import com.peach.dec.util.util.MD5Util;
import com.peach.dec.util.util.PageModel;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Resource
	private UserService userService;

	// 新增用户
	private UserEntity user;

	// 新增页面角色与班级获取封装的对象
	private RoleEntity role;
	private ClassEntity clazz;

	// 修改用户前查询回显页面对象
	private UserEntity user1;
	// 修改用户后封装成对象插入
	private UserEntity users;

	// 角色与班级回显
	private List<RoleEntity> roleList;
	@Resource
	private ClazzService clazzService;
	@Resource
	private RoleService roleService;
	private List<ClassEntity> classList;
	// 执行修改页面传过来的id
	private String id;

	// 状态修改传过来的state
	private short state;

	// 密码
	private String password;
	// 条件分页查询
	private PageModel<UserEntity> userPageModel;
	private UserEntity user2;
	private int pageNo = Constant.PAGE_FIRST;

	private String error;
	private String success;
	private String clazzId;
	private String roleId;

	/**
	 * 判断用户的唯一性
	 * 
	 * @return
	 * @throws Exception
	 */
	public String isUserExist() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		boolean result = userService.isUserExist(user);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (result) {
		} else {
			pw.print("用户名已被占用");
		}
		pw.flush();
		pw.close();
		return null;
	}

	/**
	 * 分页 查询用户
	 */
	public String findUserByCondition() throws Exception {
		roleList = roleService.getRole();
		// classList = clazzService.classlist();
		// userPageModel = userService.query(Constant.PAGE_SIZE, pageNo, user2);
		userPageModel = userService.recordsList(user2, pageNo,
				Constant.PAGE_SIZE, roleId);
		return "usershow";
	}

	/**
	 * 转到新增角色页面
	 */
	public String toUserPage() throws Exception {
		roleList = roleService.getRole();
		classList = clazzService.classlist();
		return "toInsertUserPage";
	}

	/**
	 * @return 重置密码
	 */
	public String toUserPassword() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		String password1 = MD5Util.toMD5("123456");
		boolean flag = true;
		if (!password.equals(password1)) {
			flag = userService.toUserPassword(password1, id);
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (flag) {
			pw.print(1);
		} else {
			pw.print(0);
		}

		pw.flush();
		pw.close();

		return null;
	}

	/**
	 * 用户状态修改
	 */
	public String changeState() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		short status2 = 0;
		if (state == 1) {
			status2 = 2;
		}
		if (state == 2) {
			status2 = 1;
		}
		boolean result = userService.statusChange(id, status2);

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
	 * 修改前查询
	 */
	public String toUserUpdate() throws Exception {
		roleList = roleService.getRole();
		classList = clazzService.classlist();
		user1 = userService.getUserEntity(id);
		return "userupdate";
	}

	/**
	 * @return 执行修改
	 */
	public String sureUpdate() throws Exception {
		user.setId(id);
		user.setState((short) 1);
		user.setPassword(MD5Util.toMD5("123456"));
		user.setCreateTime(userService.getCurrentTime());
		user.setOperateTime(userService.getCurrentTime());// 操作时间
		user.setOperateUserId(userService.getUserId());// 操作者
		user.setRoleId(roleId);// 角色ID
		user.setClassId(clazzId);// 班级ID
		boolean flag = userService.sureUpdate(user);
		if (flag) {
			userPageModel = userService
					.query(Constant.PAGE_SIZE, pageNo, user2);
			success = "<script>layer.msg('修改成功',{icon : 1});</script>";
			return "sureUpdate";
		} else {
			error = "<script>layer.msg('修改失败',{icon : 2});</script>";
			return "userupdate";
		}

	}

	/**
	 * 修改回显角色
	 */
	public String getRoleName() {

		String roleId = user1.getRoleId();
		String kk = userService.getRoleName(roleId);
		return kk;
	}

	/**
	 * 新增用户
	 */
	public String insertUser() throws Exception {
		user.setRoleId(roleId);
		String roleName = userService.findRoleName(roleId);
		if (roleName != null) {
			if (roleName.equals("学员")) {
				user.setClassId(clazzId);
			}
		}
		user.setPassword(MD5Util.toMD5("123456"));
		user.setCreateTime(userService.getCurrentTime());
		user.setState((short) 1);
		if (userService.getUserId() != null) {
			user.setOperateUserId(userService.getUserId());
		}
		user.setOperateTime(userService.getCurrentTime());
		boolean flag = userService.insert(user);
		if (flag) {
			userPageModel = userService
					.query(Constant.PAGE_SIZE, pageNo, user2);
			success = "<script>layer.msg('新增成功',{icon : 1});</script>";
			return "userAdd";
		} else {
			error = "<script>layer.msg('新增失败',{icon : 2});</script>";
			return "usershow";
		}

	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<RoleEntity> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleEntity> roleList) {
		this.roleList = roleList;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public ClassEntity getClazz() {
		return clazz;
	}

	public void setClazz(ClassEntity clazz) {
		this.clazz = clazz;
	}

	public UserEntity getUser1() {
		return user1;
	}

	public void setUser1(UserEntity user1) {
		this.user1 = user1;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PageModel<UserEntity> getUserPageModel() {
		return userPageModel;
	}

	public void setUserPageModel(PageModel<UserEntity> userPageModel) {
		this.userPageModel = userPageModel;
	}

	public UserEntity getUser2() {
		return user2;
	}

	public void setUser2(UserEntity user2) {
		this.user2 = user2;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public UserEntity getUsers() {
		return users;
	}

	public void setUsers(UserEntity users) {
		this.users = users;
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

	public String getClazzId() {
		return clazzId;
	}

	public void setClazzId(String clazzId) {
		this.clazzId = clazzId;
	}

	public List<ClassEntity> getClassList() {
		return classList;
	}

	public void setClassList(List<ClassEntity> classList) {
		this.classList = classList;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
