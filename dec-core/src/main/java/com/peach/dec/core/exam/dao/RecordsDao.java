package com.peach.dec.core.exam.dao;

import java.util.List;

import com.peach.dec.core.exam.entity.RecordsEntity;
import com.peach.dec.util.util.PageModel;

/**
 * dao:考试记录的相关操作
 * 
 * @author peach
 * @date 2015年5月26日
 */
public interface RecordsDao {
	/**
	 * 对考试记录的条件查询和分页
	 * 
	 * @param recordsEntity
	 * @param pageNo
	 * @param pageSize
	 * @param clazzId
	 * @return
	 */
	PageModel<RecordsEntity> recordsList(RecordsEntity recordsEntity,
			int pageNo, int pageSize, String clazzId);

	/**
	 * 新增考试记录
	 * 
	 * @param recordsEntity
	 * @return
	 */
	String insertRecords(RecordsEntity recordsEntity);

	/**
	 * 统计
	 * 
	 * @param recordsEntity
	 * @param clazzId
	 * @return
	 */
	List<RecordsEntity> getGroupCount(RecordsEntity recordsEntity,
			String clazzId);
	int updateRecords(RecordsEntity recordsEntity);
	public boolean getRecordsEntity(RecordsEntity recordsEntity);
}
