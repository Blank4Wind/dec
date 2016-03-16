package com.peach.dec.util.util;

import java.util.List;
import com.peach.dec.util.constant.Constant;

/**
 * 分页工具类
 * 
 * @author peach
 * @date 2015年3月15日
 * @version V1.0
 */
public class PageModel<T> {

	/**
	 * 结果集
	 */
	private List<T> list;

	/**
	 * 总记录数
	 */
	private int totalRecord;

	/**
	 * 每页显示多少条
	 */
	private int pageSize;

	/**
	 * 当前是第几页
	 */
	private int pageNum;

	/**
	 * 共多少页
	 * 
	 * @return
	 */
	public int getTotalPageNum() {
		return (int) Math.ceil((double) totalRecord / pageSize);
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	public int getFirst() {
		return Constant.PAGE_FIRST;
	}

	/**
	 * 尾页
	 * 
	 * @return
	 */
	public int getLast() {
		return getTotalPageNum();
	}

	/**
	 * 上一页
	 * 
	 * @return
	 */
	public int getPrev() {
		if (pageNum == getFirst()) {
			return Constant.PAGE_FIRST;
		}
		return pageNum - 1;
	}

	/**
	 * 下一页
	 * 
	 * @return
	 */
	public int getNext() {
		if (pageNum == getLast()) {
			return getTotalPageNum();
		}
		return pageNum + 1;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
