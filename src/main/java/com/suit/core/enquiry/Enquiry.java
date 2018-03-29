package com.suit.core.enquiry;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据查询条件打听器
 * 
 * @author hsy
 */
public class Enquiry {

	// 查询参数
	private  Map<String, Object> searchParams =  new HashMap<String, Object>();
	
	// 页数
	private int pageNumber = 1;
	
	// 分页显示的大小
	private int pagzSize = 10;
	
	// 排序类型：aoto代表自动，否则其值为排序字段属性名
	private String sortType = "auto";
	
	// 排序方式：asc代表顺序，desc代表逆序
	private String direction = "desc";

	public Map<String, Object> getSearchParams() {
		return searchParams;
	}

	public void setSearchParams(Map<String, Object> params) {
		searchParams.putAll(params);
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPagzSize() {
		return pagzSize;
	}

	public void setPagzSize(int pagzSize) {
		this.pagzSize = pagzSize;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
