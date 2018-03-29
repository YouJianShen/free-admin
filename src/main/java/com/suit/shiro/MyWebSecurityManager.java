package com.suit.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suit.core.exception.CoreException;
import com.suit.model.system.core.SysUser;
import com.suit.system.core.service.SysUserService;

public class MyWebSecurityManager extends DefaultWebSecurityManager {
	private static Logger logger = LoggerFactory.getLogger(MyWebSecurityManager.class);
	

	protected SysUserService sysUserService;

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	protected void onSuccessfulLogin(AuthenticationToken token,
			AuthenticationInfo info, Subject subject) {
		super.onSuccessfulLogin(token, info, subject);
		
		ShiroUser user = (ShiroUser) subject.getPrincipal();
		try {
			SysUser sysUser = sysUserService.findUniqueBy("userAccount",user.getUserAccount());
			user.setUserType(sysUser.getUserType());
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
}
