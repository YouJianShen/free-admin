package com.suit.main.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.suit.core.exception.CoreException;
import com.suit.core.exception.ErrorConstant;
import com.suit.core.service.impl.BaseServiceImpl;
import com.suit.jpa.core.BaseDao;
import com.suit.main.InOut;
import com.suit.main.dao.InOutDao;
import com.suit.main.service.InOutService;

@Component("inOutService")
@Transactional(readOnly = true)
public class InOutServiceImpl extends BaseServiceImpl<InOut> implements
		InOutService {

	@Autowired
	private InOutDao inOutDao;

	@Override
	protected BaseDao<InOut> getBaseDao() {
		return inOutDao;
	}

	@Override
	protected Class getEntityClass() {
		return InOut.class;
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String create(InOut info) throws CoreException {
		save(info);
		return info.getId();
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void confirm(String id) throws CoreException {
		InOut item = findUniqueBy("id", id);
		item.setConfirm(true);
		save(item);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void accept(String id) throws CoreException {
		InOut item = findUniqueBy("id", id);
		if (item.isConfirm()) {
			item.setAccept(true);
			save(item);
		} else {
			throw new CoreException(ErrorConstant.COMMON_USER_DEVICE_NOTREG,
					"改记录还未被寝室管理员确认。");
		}
	}

	public Page<InOut> getList(String id, int page, int rows)
			throws CoreException {
		return inOutDao.getPageList(id, new PageRequest(page - 1, rows));
	}

	public Page<InOut> getList(String partnerId, String dormId, int page,
			int rows) throws CoreException {
		return inOutDao.getPageList(partnerId, dormId, new PageRequest(
				page - 1, rows));
	}

	@Override
	public double[] getSumData(String dormId) throws CoreException {
		return inOutDao.getSum(dormId);
	}

	@Override
	public double getSumDateByDate(Date startDate, Date endDate)
			throws CoreException {
		double pay = 0d;
		try {
			pay = inOutDao.getSumByTime(startDate, endDate);
		} catch (CoreException e) {
		}
		return pay;
	}
}
