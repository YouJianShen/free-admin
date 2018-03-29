package com.suit.model.system.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.suit.base.domain.BaseModel;
import com.suit.model.core.common.EnumConstants.SysUserType;

/**
 * 系统用户
 * 
 * @author hsy
 * 
 */
@Entity
@Table(name = "system_core_user")
public class SysUser extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户真实姓名
	 */
	@Column(length = 20)
	private String userName;

	/**
	 * 系统登录名
	 */
	@Column(unique = true, length = 50)
	private String userAccount;

	@Column(length = 100)
	private String password;

	/**
	 * 盐值(加密时使用)
	 */
	@Column(length = 40)
	private String salt;

	@Column(unique = true, length = 20)
	private String phoneNumber;

	@Enumerated
	private SysUserType userType;

	@Column(length = 100, name = "belongs_id")
	private String belongsId;

	public String getPassword() {
		return password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getSalt() {
		return salt;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public SysUserType getUserType() {
		return userType;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserType(SysUserType userType) {
		this.userType = userType;
	}

	public String getBelongsId() {
		return belongsId;
	}

	public void setBelongsId(String belongsId) {
		this.belongsId = belongsId;
	}
}
