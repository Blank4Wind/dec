package com.peach.dec.core.sys.role.entity;
/**
 *@author guosong
 *@version 2015年6月2日 下午3:08:302015年6月2日
 *@explain 权限修改树形条件
 */
public class PowerParam {
	
	private String id;
	private String pid;
	private String name;
	private String url;
	private boolean open;
	private boolean checked;
	private Short state;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean getOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	
}
