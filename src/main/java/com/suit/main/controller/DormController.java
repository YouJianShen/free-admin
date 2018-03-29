package com.suit.main.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suit.core.constant.WebConstant;
import com.suit.core.exception.CoreException;
import com.suit.core.service.BaseService;
import com.suit.core.tool.BaseController;
import com.suit.core.web.vo.OperateStatus;
import com.suit.main.Dorm;
import com.suit.main.service.DormService;
import com.suit.main.service.InOutService;
import com.suit.main.vo.DormVo;
import com.suit.system.core.service.SysUserService;
import com.suit.system.web.SysUserController;
import com.suit.util.JsonUtil;

@Controller
@RequestMapping(value = "/dorm")
public class DormController extends BaseController<Dorm> {
	private static final Logger logger = LoggerFactory
			.getLogger(SysUserController.class);

	@Autowired
	private DormService dormService;

	@Autowired
	private InOutService inOutService;

	@Autowired
	private SysUserService sysUserService;

	@Override
	protected BaseService<Dorm> getBaseService() {
		return dormService;
	}

	@ResponseBody
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(Dorm dormInfo, HttpServletRequest request)
			throws CoreException {
		OperateStatus status = new OperateStatus(true,
				WebConstant.COMMON_SUCCESS_MSG);
		try {
			status.setData(dormService.createDorm(dormInfo));
		} catch (CoreException e) {
			status.setSuccess(false);
			status.setMsg(e.getMessage());
		}
		return JsonUtil.genJson(status);
	}

	@ResponseBody
	@RequestMapping(value = "getInfo", method = RequestMethod.GET)
	public String create(String dormId, int page, int rows)
			throws CoreException {
		OperateStatus status = new OperateStatus(true,
				WebConstant.COMMON_SUCCESS_MSG);
		DormVo data = new DormVo();
		try {
			Date endDate = new Date();
			Date startDate = new Date();
			int year = endDate.getYear();
			int month = endDate.getMonth();
			if (month == 0) {
				startDate.setMonth(11);
				startDate.setYear(year - 1);
			} else {
				startDate.setMonth(month - 1);
			}
			data.setThenPay(inOutService.getSumDateByDate(startDate, endDate));
			endDate = startDate;
			startDate = new Date();
			if (month == 1) {
				startDate.setMonth(11);
				startDate.setYear(year - 1);
			} else {
				startDate.setMonth(month - 2);
			}
			data.setPrePay(inOutService.getSumDateByDate(startDate, endDate));
			data.setDormInfo(dormService.getById(dormId));
			data.setPartners(JsonUtil.getJSONSerializer(new String[] { "" },
					new String[] { "salt", "password" },
					"yyyy-MM-dd HH:mm:ss").serialize(
					sysUserService.getPartner(dormId)).toString());
			double[] sum = inOutService.getSumData(dormId);
			data.setRemain(sum[1] - sum[0]);
			data.setExpense(sum[0]);
			status.setData(data);
		} catch (CoreException e) {
			status.setSuccess(false);
			status.setMsg(e.getMessage());
		}
		return JSONObject.fromObject(status).toString();
	}

}
