package com.suit.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.suit.core.exception.CoreException;
import com.suit.core.exception.ErrorConstant;
import com.suit.core.service.impl.BaseServiceImpl;
import com.suit.jpa.core.BaseDao;
import com.suit.jpa.system.core.SysUserDao;
import com.suit.main.Dorm;
import com.suit.main.dao.DormDao;
import com.suit.main.service.DormService;
import com.suit.model.core.common.EnumConstants.SysUserType;
import com.suit.model.system.core.SysUser;
import com.suit.system.core.service.SysUserService;

@Component("dormService")
@Transactional(readOnly = true)
public class DormServiceImpl extends BaseServiceImpl<Dorm> implements
		DormService {

	@Autowired
	private DormDao dormDao;

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private SysUserService sysUserService;

	@Override
	protected BaseDao<Dorm> getBaseDao() {
		return dormDao;
	}

	@Override
	protected Class getEntityClass() {
		return Dorm.class;
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String createDorm(Dorm dormInfo) throws CoreException {
		SysUser user = sysUserService.getById(dormInfo.getCreateUserId());
		if (user.getBelongsId() != null) {
			throw new CoreException(ErrorConstant.ERROR_RE_OPERATION,
					"你已经加入了宿舍，不能再创建。");
		}
		if (findUniqueBy("name", dormInfo.getName()) != null) {
			throw new CoreException(ErrorConstant.COMMON_USER_DEVICE_NOTREG,
					"宿舍名已存在");
		}
		dormDao.save(dormInfo);
		dormInfo = getByName(dormInfo.getName());
		user.setBelongsId(dormInfo.getId());
		if (user.getUserType().equals(SysUserType.PARTNER)) {
			user.setUserType(SysUserType.DORM_ADMIN);
		}
		sysUserDao.save(user);
		return dormInfo.getId();
	}

	public void deletePartner(String id) throws CoreException {
		// TODO Auto-generated method stub

	}

	public Dorm getByName(String name) throws CoreException {
		return findUniqueBy("name", name);
	}

}
