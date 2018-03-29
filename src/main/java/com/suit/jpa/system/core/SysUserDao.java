package com.suit.jpa.system.core;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import com.suit.jpa.core.BaseDao;
import com.suit.model.system.core.SysUser;

public interface SysUserDao extends BaseDao<SysUser>{
	/**
	 * 查询手机号注册次数
	 * @param phone
	 * @return
	 */
	@Query("select count(*) from SysUser where userAccount like ?1")
	public int phoneRegisterCount(String phone);
	
	/**
	 * 查询企业名称注册次数
	 * @param name
	 * @return
	 */
	@Query("select count(*) from SysUser where userName=?1")
	public int nameRegisterCount(String name);
	
	
	/**
	 * 根据账号批量查询出用户的基本信息
	 * @param accountList
	 * @return
	 */
	@Query("from SysUser t where t.userAccount in (?1)")
	public List<SysUser> getList(Set<String> accountSet);
}
