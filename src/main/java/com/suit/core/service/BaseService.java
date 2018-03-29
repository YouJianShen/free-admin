package com.suit.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.suit.base.domain.BaseModel;
import com.suit.core.enquiry.Enquiry;
import com.suit.core.exception.CoreException;
public interface BaseService<T extends BaseModel> {
	/**
	 * 创建或更新实体记录
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity)throws CoreException;
	/**
	 * 批量创建实体
	 * @param entityList
	 * @throws CoreException
	 */
	public void save(List<T> entityList)throws CoreException;
	
	/**
	 * 删除实体
	 */
	public  void delete(T entity)throws CoreException;

	/**
	 * 删除对应id的实体
	 */
	public  void delete(String id)throws CoreException;
	
	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public  void multiDelete(String[] ids)throws CoreException;

	/**
	 * 根据id获取实体
	 * 
	 * @param id
	 * @return
	 */
	public  T getById(String id)throws CoreException;

	/**
	 * 查询对应字段值与给定值匹配的唯一结果
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public  T findUniqueBy(String propertyName, Object value)throws CoreException;

	/**
	 * 查询对应字段值与给定值匹配的结果集合
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public  List<T> findBy(String propertyName, Object value)throws CoreException;

	/**
	 * 查询多个字段值与给定值匹配的结果集合
	 * 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public  List<T> findBy(String[] propertyNames, Object[] values)throws CoreException;
	
	/**
	 * 查询多个字段值与给定值匹配的唯一结果
	 * 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public  T findUniqueBy(String[] propertyNames, Object[] values)throws CoreException;

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	public  List<T> getAll()throws CoreException;

	/**
	 * 查询满足条件的所有记录
	 * 
	 * @param queryFilter
	 * @return
	 */
	public  List<T> getAll(Map<String,Object> map)throws CoreException;
	

	/**根据搜索条件，搜索组织机构。
	 * 
	 * @param info
	 * @return
	 */
	public Page<T> search(Enquiry info)throws CoreException;

	/**根据搜索条件，搜索组织机构。
	 * 
	 * @param info
	 * @return
	 */
	public List<T> search(Enquiry info,Sort sort)throws CoreException;
	
}
