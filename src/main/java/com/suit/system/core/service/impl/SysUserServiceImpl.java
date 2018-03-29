package com.suit.system.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.util.GUID;
import com.suit.core.exception.CoreException;
import com.suit.core.exception.ErrorConstant;
import com.suit.core.service.impl.BaseServiceImpl;
import com.suit.core.util.SystemUtil;
import com.suit.jpa.core.BaseDao;
import com.suit.jpa.system.core.SysUserDao;
import com.suit.model.core.common.EnumConstants.BaseModelStatus;
import com.suit.model.core.common.EnumConstants.SysUserType;
import com.suit.model.system.core.SysUser;
import com.suit.system.core.service.SysUserService;
import com.suit.system.core.service.vo.RegisterVo;

@Component("sysUserService")
@Transactional(readOnly = true)
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements
		SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	protected BaseDao<SysUser> getBaseDao() {
		return sysUserDao;
	}

	@Override
	protected Class getEntityClass() {
		return SysUser.class;
	}

	public SysUser create(SysUser SysUser) throws CoreException {
		return null;
	}

	public SysUser update(SysUser SysUser) throws CoreException {
		return null;
	}

	public SysUser freeze(String id) throws CoreException {
		return null;
	}

	/**
	 * 注册
	 * 
	 * @throws CoreException
	 */
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String register(RegisterVo regInfo) throws CoreException {
		// 检查验证
		checkIsExist(regInfo);
		checkPassword(regInfo.getPassword());
		return doRegister(regInfo);
	}

	private void checkIsExist(RegisterVo regInfo) throws CoreException {

		SysUser user = this.findUniqueBy("userAccount", regInfo.getAccount());
		if (user != null) {
			throw new CoreException(ErrorConstant.REGISTER_PHONE_EXIST,
					"该账号已被注册");
		}
	}

	/**
	 * 检查密码是否满足规则
	 * 
	 * @param password
	 * @throws CoreException
	 */
	private void checkPassword(String password) throws CoreException {
		if ((password.length() > 15 && password.length() < 8)
				|| password.matches("[0-9]{8,15}")
				|| password.matches("[a-zA-Z]{8,15}")) {
			throw new CoreException(ErrorConstant.REGISTER_PASSWORD_ERROR,
					"密码不符合规定！");
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	private String doRegister(RegisterVo regInfo) throws CoreException {
		String md5Psd = regInfo.getPassword();
		SysUser user = new SysUser();
		user.setUserName(regInfo.getName());
		user.setPassword(md5Psd);
		user.setSalt(regInfo.getSalt());
		user.setPhoneNumber(regInfo.getAccount());
		user.setStatus(BaseModelStatus.NORMAL);
		user.setUserAccount(regInfo.getAccount());
		// 用户类型不能为管理员
		SysUserType userType = regInfo.getUserType();
		if (userType == null)
			throw new CoreException(ErrorConstant.NOT_ALLOW_REGIST, "指定创建用户类型");
		// if(regInfo.getUserType().equals(SysUserType.ADMIN))
		// throw new CoreException(ErrorConstant.NOT_ALLOW_REGIST,"不能注册管理员用户");
		user.setUserType(regInfo.getUserType());

		user = this.save(user);
		return user.getId();
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void jionDorm(String partnerId, String dormId) throws CoreException {
		SysUser user = findUniqueBy("id", partnerId);
		if (user.getBelongsId() == null) {
			user.setBelongsId(dormId);
			save(user);
		} else {
			throw new CoreException(ErrorConstant.COMMON_USER_DEVICE_NOTREG,
					"该成员已加入宿舍。");
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void cancelDorm(String partnerId) throws CoreException {
		SysUser user = findUniqueBy("id", partnerId);
		user.setBelongsId(null);
		if (user.getUserType().equals(SysUserType.DORM_ADMIN))
			user.setUserType(SysUserType.PARTNER);
		save(user);
	}

	@Override
	public List<SysUser> getPartner(String dormId) throws CoreException {
		return findBy("belongsId", dormId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void updatePassword(String account, String old, String pre)
			throws CoreException {
		SysUser user = findUniqueBy("account", account);
		String salt = GUID.generateGUID();
		String md5Psd = SystemUtil.encryPass(old, salt);
		if(user.getPassword().equals(md5Psd)){
			user.setPassword(SystemUtil.encryPass(pre, salt));
			save(user);
		}else{
			throw new CoreException(ErrorConstant.COMMON_USER_DEVICE_NOTREG,"输入旧密码错误");
		}
	}

}
