package com.suit.main.service;

import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.main.Dorm;

public interface DormService extends BaseService<Dorm>{
	
	/**
	 * 创建宿舍
	 * @param dormInfo
	 * @return
	 * @throws CoreException
	 */
	public String createDorm(Dorm dormInfo) throws CoreException;
	
	/**
	 * 获取宿舍信息
	 * @return
	 * @throws CoreException
	 */
	public Dorm getByName(String name) throws CoreException;
	
	/**
	 * 删除成员
	 * @param id
	 * @throws CoreException
	 */
	public void deletePartner(String id) throws CoreException;
}
