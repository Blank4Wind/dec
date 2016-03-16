package com.peach.dec.core.exam.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.peach.dec.core.exam.dao.RecordsDao;
import com.peach.dec.core.exam.entity.RecordsEntity;
import com.peach.dec.core.exam.service.RecordsService;
import com.peach.dec.util.util.PageModel;

/**
 * 业务层：考试记录的相关操作
 * 
 * @author peach
 * @date 2015年5月26日
 */
@Service
public class RecordsServiceImpl implements RecordsService {
	@Resource
	private RecordsDao recordsDao;

	/**
	 * 对考试记录的条件查询和分页
	 * 
	 * @param recordsEntity
	 * @param pageNo
	 * @param pageSize
	 * @param clazzId
	 * @return
	 */
	@Override
	public PageModel<RecordsEntity> recordsList(RecordsEntity recordsEntity,
			int pageNo, int pageSize, String clazzId) {
		return recordsDao.recordsList(recordsEntity, pageNo, pageSize, clazzId);
	}

	/**
	 * 新增考试记录
	 * 
	 * @param recordsEntity
	 * @return
	 */
	@Override
	public String insertRecords(RecordsEntity recordsEntity) {
		return recordsDao.insertRecords(recordsEntity);
	}

	/**
	 * 统计
	 * 
	 * @param recordsEntity
	 * @param clazzId
	 * @return
	 */
	@Override
	public List<RecordsEntity> getGroupCount(RecordsEntity recordsEntity,
			String clazzId) {
		return recordsDao.getGroupCount(recordsEntity, clazzId);
	}

	@Override
	public int updateRecords(RecordsEntity recordsEntity) {
		return recordsDao.updateRecords(recordsEntity);
	}
	@Override
	public boolean getRecordsEntity(RecordsEntity recordsEntity) {
		return recordsDao.getRecordsEntity(recordsEntity);
	}

}
