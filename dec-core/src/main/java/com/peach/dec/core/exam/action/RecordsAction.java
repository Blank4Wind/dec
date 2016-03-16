package com.peach.dec.core.exam.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.peach.dec.core.exam.entity.RecordsEntity;
import com.peach.dec.core.exam.service.RecordsService;
import com.peach.dec.core.sys.clazz.entity.ClassEntity;
import com.peach.dec.core.sys.clazz.service.ClazzService;
import com.peach.dec.util.constant.Constant;
import com.peach.dec.util.util.PageModel;

/**
 * 控制层：考试记录的相关操作
 * 
 * @author peach
 * @date 2015年5月26日
 */
@Controller
@Scope("prototype")
public class RecordsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private RecordsService recordsService;
	@Resource
	private ClazzService clazzService;
	private RecordsEntity recordsEntity;
	private PageModel<RecordsEntity> pageModel;
	private List<RecordsEntity> recordsList;
	private int pageNo = Constant.PAGE_FIRST;
	private List<ClassEntity> classList;
	private String clazzId;
	private String recordsJson;

	/**
	 * 对考试记录的条件查询和分页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String recordsList() throws Exception {
		classList = clazzService.classlist();
		recordsList = recordsService.getGroupCount(recordsEntity, clazzId);
		recordsJson = JSON.toJSONString(recordsList);
		pageModel = recordsService.recordsList(recordsEntity, pageNo,
				Constant.PAGE_SIZE, clazzId);
		return "recordsList";
	}

	public RecordsEntity getRecordsEntity() {
		return recordsEntity;
	}

	public void setRecordsEntity(RecordsEntity recordsEntity) {
		this.recordsEntity = recordsEntity;
	}

	public PageModel<RecordsEntity> getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel<RecordsEntity> pageModel) {
		this.pageModel = pageModel;
	}

	public List<RecordsEntity> getRecordsList() {
		return recordsList;
	}

	public void setRecordsList(List<RecordsEntity> recordsList) {
		this.recordsList = recordsList;
	}

	public List<ClassEntity> getClassList() {
		return classList;
	}

	public void setClassList(List<ClassEntity> classList) {
		this.classList = classList;
	}

	public String getClazzId() {
		return clazzId;
	}

	public void setClazzId(String clazzId) {
		this.clazzId = clazzId;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getRecordsJson() {
		return recordsJson;
	}

	public void setRecordsJson(String recordsJson) {
		this.recordsJson = recordsJson;
	}

}
