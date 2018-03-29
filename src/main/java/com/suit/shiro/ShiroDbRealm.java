/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.suit.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.suit.model.core.common.EnumConstants.BaseModelStatus;
import com.suit.model.system.core.SysUser;
import com.suit.system.core.service.SysUserService;

public class ShiroDbRealm extends AuthorizingRealm {

	protected SysUserService sysUserService;

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;

		SysUser sysUser = null;
		try {
			sysUser = sysUserService.findUniqueBy("userAccount",
					token.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (sysUser != null) {
			// 状态异常
			if (sysUser.getStatus() != BaseModelStatus.NORMAL) {
				return null;
			}
			ShiroUser shiroUser = new ShiroUser(sysUser.getId(),
					sysUser.getUserAccount(), sysUser.getUserName());
			return new SimpleAuthenticationInfo(shiroUser,
					sysUser.getPassword(), ByteSource.Util.bytes(sysUser
							.getSalt()), getName());
		} else {
			return null;
		}

		// ShiroUser shiroUser = new ShiroUser("1",
		// "admin", "管理员");
		//
		// String salt ="123";
		// String password = "123456";
		// String md5Psd = SystemUtil.encryPass(password, salt);
		// return new SimpleAuthenticationInfo(shiroUser,
		// md5Psd, ByteSource.Util.bytes(salt), getName());
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		// User user = accountService.findUserByLoginName(shiroUser.loginName);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// info.addRoles(user.getRoleList());

		List<String> roleList = new ArrayList<String>();
		roleList.add("role1");
		roleList.add("role2");
		roleList.add("role3");
		info.addRoles(roleList);
		return info;
	}

}
