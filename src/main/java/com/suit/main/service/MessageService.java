package com.suit.main.service;

import java.util.List;

import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.main.Message;
import com.suit.model.core.common.EnumConstants.BaseModelStatus;

public interface MessageService extends BaseService<Message> {

	/**
	 * 查询宿舍消息
	 * 
	 * @param id
	 * @return
	 * @throws CoreException
	 */
	public List<Message> getListDorm(String id) throws CoreException;

	/**
	 * 查询个人消息
	 * 
	 * @param id
	 * @return
	 * @throws CoreException
	 */
	public List<Message> getListPerson(String id) throws CoreException;

	/**
	 * 生成信息
	 * 
	 * @param msg
	 * @throws CoreException
	 */
	public void create(Message msg) throws CoreException;

	/**
	 * 标记处理消息
	 * 
	 * @param msgId
	 * @throws CoreException
	 */
	public void deal(String msgId, BaseModelStatus status) throws CoreException;
}
