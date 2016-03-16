package com.peach.dec.core.sys.role.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.peach.dec.core.sys.role.entity.PowerEntity;
import com.peach.dec.core.sys.role.entity.RoleEntity;
import com.peach.dec.core.sys.role.service.RoleService;
import com.peach.dec.core.sys.user.entity.UserEntity;

@Controller
@Scope("prototype")
public class RoleAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private RoleService roleService;
	private List<RoleEntity> roleList;
	private RoleEntity roleEntity;
	private List<RoleEntity> roleList2;
	private String id;
	private String roleJson;
	private String success;
	private String error;
	private String ids;

	private PowerEntity powerEntity;
	
	public String getRoleJson() {
		return roleJson;
	}

	public void setRoleJson(String roleJson) {
		this.roleJson = roleJson;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	private short status;
	private Date operateTime;
	private String treeCode;

	/**
	 * @return
	 * @throws Exception
	 *             角色查询
	 */
	public String list() throws Exception {
		roleList = roleService.list(roleEntity);
		return "list";

	}

	/**
	 * @return
	 * @throws Exception
	 *             角色新增页面跳转
	 */
	public String add() throws Exception {
		return "add";
	}

	/**
	 * @return
	 * @throws Exception
	 *             角色新增
	 */
	public String addRole() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		roleEntity.setName(roleEntity.getName());
		roleEntity.setOperateUserId(user.getId());
		roleEntity.setOperateTime(new Date());
		roleEntity.setState((short) 1);
		roleEntity.setMenuIds("2");
		roleEntity.setMemo(roleEntity.getMemo());
		roleEntity = roleService.roleAdd(roleEntity);
		if (roleEntity == null) {
			error = "<script>layer.msg('新增失败',{icon: 2});</script>";
			return "add";
		} else {
			roleService.setPower(roleService.getRoleId(roleEntity.getName()));
			success = "<script>layer.msg('新增成功',{icon: 1});</script>";
			return "roleAd";
		}
	}
	/**
	 * 跳转权限管理页面
	 */
	public String toPowerMgr(){
		powerEntity = roleService.queryAllPower(id);
		return "toPowerMgr";
	}
	
	/**
	 * @return 修改角色——修改前查询
	 */

	public String roleUpdate() {
		roleEntity = roleService.updateList(id);
		return "roleUpdate";

	}
	
	/**
	 *权限修改 
	 * @throws IOException 
	 */
	public String powerMGR() throws IOException{
		treeCode = roleService.queryPowers(id);
		return "powerMGR";
	}
	/**
	 *权限修改 
	 * @throws IOException 
	 */
	public String power() throws IOException{
		@SuppressWarnings("unused")
		boolean result = roleService.updateMGR(ids);
		roleEntity=new RoleEntity();
		roleEntity.setState((short)1);
		roleList = roleService.list(roleEntity);
		return "list";
	}

	/**
	 * @return 执行角色修改
	 * @throws Exception
	 */
	public String sureUpdate() throws Exception {
		UserEntity user = (UserEntity) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		roleEntity.setOperateUserId(user.getId());
		roleEntity.setOperateTime(new Date());
		roleEntity.setId(id);
		boolean result = roleService.sureUpdate(roleEntity);
		if (result) {
			success = "<script>layer.msg('修改成功',{icon: 1});</script>";
			return "updateM";
		} else {
			error = "<script>layer.msg('修改失败',{icon: 2});</script>";
			return "roleUpdate";
		}
	}

	/**
	 * 验证角色名是否存在
	 * 
	 * @return
	 * @throws Exception
	 */
	public String isRoleExist() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		boolean result = roleService.isRoleExist(roleEntity);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (result) {
		} else {
			pw.print("角色名已被占用");
		}
		pw.flush();
		pw.close();
		return null;
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
		boolean result = roleService.statusChange(id, status2);

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

	public List<RoleEntity> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleEntity> roleList) {
		this.roleList = roleList;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}

	public List<RoleEntity> getRoleList2() {
		return roleList2;
	}

	public void setRoleList2(List<RoleEntity> roleList2) {
		this.roleList2 = roleList2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
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

	public PowerEntity getPowerEntity() {
		return powerEntity;
	}

	public void setPowerEntity(PowerEntity powerEntity) {
		this.powerEntity = powerEntity;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
