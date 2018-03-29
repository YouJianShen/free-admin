package com.suit.core.web.vo;

import java.util.Collection;

/**
 * 系统数据响应 统一应用于所有模块的数据响应模式 若需要扩展，必须继承该类,命名方式为 模块名+JsonPageData，例如用户模块则名称为
 * UserJsonPageData
 * 
 * @author hsy
 * @version 1.0
 * 
 */
public class JsonPageData<T> {

	/**
	 * 当前页
	 */
	private Integer page;
	/**
	 * 每页数量
	 */
	private Integer pageSize;
	/**
	 * 总条数
	 */
	private Long records;
	/**
	 * 总页数
	 */
	private int total;

	private Collection<T> rows;

	/**
	 * 
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            每页大小
	 * @param totalRows
	 *            总条数
	 * @param rows
	 *            当前页集合
	 * @param total
	 *            总页数
	 */
	public JsonPageData(Integer pageNo, Integer pageSize, Long totalRows,
			Collection<T> rows, int total) {
		this.page = pageNo;
		this.pageSize = pageSize;
		this.records = totalRows;
		this.rows = rows;
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
	}

	public Collection<T> getRows() {
		return rows;
	}

	public void setRows(Collection<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}