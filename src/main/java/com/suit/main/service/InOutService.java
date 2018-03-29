package com.suit.main.service;


import java.util.Date;

import org.springframework.data.domain.Page;

import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.main.InOut;

public interface InOutService extends BaseService<InOut> {

	/**
	 * 创建记录
	 * 
	 * @param info
	 * @return
	 * @throws CoreException
	 */
	public String create(InOut info) throws CoreException;

	/**
	 * 获取宿舍记录
	 * 
	 * @param id
	 * @return
	 * @throws CoreException
	 */
	public Page<InOut> getList(String id, int page, int rows)
			throws CoreException;

	/**
	 * 根据成员获取记录
	 * 
	 * @param partnerId
	 * @return
	 * @throws CoreException
	 */
	public Page<InOut> getList(String partnerId, String dormId, int page,
			int rows) throws CoreException;

	/**
	 * 室长确认记录
	 * 
	 * @param id
	 * @throws CoreException
	 */
	public void confirm(String id) throws CoreException;

	/**
	 * 确认收账
	 * 
	 * @param id
	 * @throws CoreException
	 */
	public void accept(String id) throws CoreException;
	
	/**
	 * 获取宿舍统计数据
	 * @param dormId
	 * @return
	 * @throws CoreException
	 */
	public double[] getSumData(String dormId) throws CoreException;
	
	/**
	 * 按时间段查询支出
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws CoreException
	 */
	public double getSumDateByDate(Date startDate,Date endDate) throws CoreException;
}
