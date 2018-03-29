package com.suit.shiro;

import java.io.Serializable;

import com.suit.model.core.common.EnumConstants.SysUserType;
import com.google.common.base.Objects;

public class ShiroUser implements Serializable {

	private static final long serialVersionUID = -1373760761780840081L;
	public String id;
	public String loginName;
	public String name;
	
	private String userAccount;
	
	private SysUserType userType;
	
	private String belongsId;

	public ShiroUser(String id, String loginName, String name) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		
		this.userAccount = loginName;
	}

	/**
	 * 重载equals,只计算loginName;
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ShiroUser other = (ShiroUser) obj;
		if (loginName == null) {
			if (other.loginName != null) {
				return false;
			}
		} else if (!loginName.equals(other.loginName)) {
			return false;
		}
		return true;
	}

	public String getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getName() {
		return name;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public SysUserType getUserType() {
		return userType;
	}

	/**
	 * 重载hashCode,只计算loginName;
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(loginName);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public void setUserType(SysUserType userType) {
		this.userType = userType;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return loginName;
	}

	public String getBelongsId() {
		return belongsId;
	}

	public void setBelongsId(String belongsId) {
		this.belongsId = belongsId;
	}
}
