package com.peach.dec.core.sys.role.entity;

import java.util.ArrayList;
import java.util.List;

import com.peach.dec.core.sys.user.entity.MenuEntity;


/**
 *@author guosong
 *@version 2015年5月30日 下午3:00:162015年5月30日
 *@explain 权限新加类
 */

public class PowerEntity {
	
	private String roleId;
	List<MenuEntity> baseModel = new ArrayList<MenuEntity>();
	List<MenuEntity> qesModel = new ArrayList<MenuEntity>();
	List<MenuEntity> examModel = new ArrayList<MenuEntity>();
	List<MenuEntity> assistModel = new ArrayList<MenuEntity>();
	List<MenuEntity> stuModel = new ArrayList<MenuEntity>();
	
	public List<MenuEntity> getBaseModel() {
		return baseModel;
	}
	public void setBaseModel(List<MenuEntity> baseModel) {
		this.baseModel = baseModel;
	}
	public List<MenuEntity> getQesModel() {
		return qesModel;
	}
	public void setQesModel(List<MenuEntity> qesModel) {
		this.qesModel = qesModel;
	}
	public List<MenuEntity> getExamModel() {
		return examModel;
	}
	public void setExamModel(List<MenuEntity> examModel) {
		this.examModel = examModel;
	}
	public List<MenuEntity> getAssistModel() {
		return assistModel;
	}
	public void setAssistModel(List<MenuEntity> assistModel) {
		this.assistModel = assistModel;
	}
	public List<MenuEntity> getStuModel() {
		return stuModel;
	}
	public void setStuModel(List<MenuEntity> stuModel) {
		this.stuModel = stuModel;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
