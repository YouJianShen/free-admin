package com.suit.system.core.service;

import java.util.List;

import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.model.system.core.SysUser;
import com.suit.system.core.service.vo.RegisterVo;

public interface SysUserService extends BaseService<SysUser> {

	/**
	 * 创建
	 * 
	 */
	public SysUser create(SysUser SysUser) throws CoreException;

	/**
	 * 更新
	 * 
	 */
	public SysUser update(SysUser SysUser) throws CoreException;


	/**
	 * 冻结/解冻
	 * 
	 */
	public SysUser freeze(String id) throws CoreException;
	
	/**
	 * 用户注册
	 * @param regInfo
	 * @return
	 * @throws CoreException
	 */
	public String register(RegisterVo regInfo) throws CoreException;
	
	/**
	 * 修改密码
	 * @param old
	 * @param pre
	 * @throws CoreException
	 */
	public void updatePassword(String account,String old,String pre) throws CoreException;
	
	/**
	 * 成员加入宿舍
	 * @param partnerId
	 * @param dormId
	 * @throws CoreException
	 */
	public void jionDorm(String partnerId,String dormId) throws CoreException;
	
	/**
	 * 成员退出宿舍
	 * @param partnerId
	 * @param dormId
	 * @throws CoreException
	 */
	public void cancelDorm(String partnerId) throws CoreException;
	
	/**
	 * 获取宿舍成员列表
	 * @param dormId
	 * @return
	 * @throws CoreException
	 */
	public List<SysUser> getPartner(String dormId) throws CoreException;
	
}
