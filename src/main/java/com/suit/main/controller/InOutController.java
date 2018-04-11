package com.suit.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suit.core.constant.WebConstant;
import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.core.tool.BaseController;
import com.suit.core.web.vo.OperateStatus;
import com.suit.main.InOut;
import com.suit.main.service.InOutService;
import com.suit.system.web.SysUserController;
import com.suit.util.JsonUtil;

@Controller
@RequestMapping(value = "inout")
public class InOutController extends BaseController<InOut> {

	@Autowired
	private InOutService inOutService;

	@Override
	protected BaseService<InOut> getBaseService() {
		return inOutService;
	}

	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(InOut info, HttpServletRequest request)
			throws CoreException {
		OperateStatus status = new OperateStatus(true,
				WebConstant.COMMON_SUCCESS_MSG);
		try {
			inOutService.create(info);
		} catch (CoreException e) {
			status.setSuccess(false);
			status.setMsg(e.getMessage());
		}
		return JsonUtil.genJson(status);
	}

	/**
	 * 获取宿舍记录
	 * 
	 * @param dormId
	 * @param request
	 * @return
	 * @throws CoreException
	 */
	@ResponseBody
	@RequestMapping(value = "/dorm/list", method = RequestMethod.GET)
	public String getList(String dormId, int page, int rows,
			HttpServletRequest request) throws CoreException {
		OperateStatus status = new OperateStatus(true,
				WebConstant.COMMON_SUCCESS_MSG);
		try {
			Page<InOut> pageList = inOutService.getList(dormId, page, rows);
			status.setData(pageList);
		} catch (CoreException e) {
			status.setSuccess(false);
			status.setMsg(e.getMessage());
		}
		return JsonUtil.genJson(status);
	}

	/**
	 * 获取宿舍成员记录
	 * 
	 * @param dormId
	 * @param partnerId
	 * @param request
	 * @return
	 * @throws CoreException
	 */
	@ResponseBody
	@RequestMapping(value = "/partner/list", method = RequestMethod.GET)
	public String getListByPartner(String dormId, String partnerId, int page,
			int rows, HttpServletRequest request) throws CoreException {
		OperateStatus status = new OperateStatus(true,
				WebConstant.COMMON_SUCCESS_MSG);
		try {
			status.setData(inOutService.getList(dormId, partnerId, page, rows));
		} catch (CoreException e) {
			status.setSuccess(false);
			status.setMsg(e.getMessage());
		}
		return JsonUtil.genJson(status);
	}

}
