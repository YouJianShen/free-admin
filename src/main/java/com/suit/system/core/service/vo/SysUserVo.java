package com.suit.system.core.service.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Enumerated;

import net.sf.json.JSONArray;

import com.suit.model.core.common.EnumConstants;
import com.suit.model.core.common.EnumConstants.SysUserType;

/**
 * 宿舍用户
 * 
 * @author syj
 * 
 */
public class SysUserVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * id值
	 */
	@Column(length = 32)
	private String id;
	
	/**
	 * 用户真实姓名
	 */
	@Column(length = 20)
	private String userName;

	/**
	 * 账号
	 */
	@Column(unique = true, length = 50)
	private String userAccount;

	/**
	 * 手机号码
	 */
	@Column(unique = true, length = 20)
	private String phoneNumber;

	/**
	 * 登录密码
	 */
	@Column(length = 100)
	private String password;

	/**
	 * 盐值(加密时使用)
	 */
	@Column(length = 40)
	private String salt;
	
	/**
	 * 状态
	 */
	@Enumerated
	private EnumConstants.BaseModelStatus status;
	
	/**
	 * 权限管辖楼栋
	 */
	@Column(length = 40)
	private JSONArray houseSet;
	
	private SysUserType userType = SysUserType.PARTNER;
	
	public JSONArray getHouseSet() {
		return houseSet;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getSalt() {
		return salt;
	}

	public EnumConstants.BaseModelStatus getStatus() {
		return status;
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

	public void setHouseSet(String houseSet) {
		this.houseSet = JSONArray.fromObject(houseSet);
	}

	public void setId(String id) {
		this.id = id;
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

	public void setStatus(EnumConstants.BaseModelStatus status) {
		this.status = status;
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
}
