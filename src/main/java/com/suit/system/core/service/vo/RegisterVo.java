package com.suit.system.core.service.vo;

import java.io.Serializable;

import com.suit.model.core.common.EnumConstants.SysUserType;

/**
 * 注册模型
 * 
 * @author lzw
 * 
 */
public class RegisterVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String account;// 账号

	private String phoneNumber;// 电话号码

	private String name;// 用户名

	private String password;// 密码

	/**
	 * 用户类型
	 */
	private SysUserType userType;

	/**
	 * 密码盐值
	 */
	private String salt;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SysUserType getUserType() {
		return userType;
	}

	public void setUserType(SysUserType userType) {
		this.userType = userType;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
