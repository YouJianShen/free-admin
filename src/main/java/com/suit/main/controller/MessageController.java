package com.suit.main.controller;

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
import com.suit.main.Message;
import com.suit.main.service.MessageService;
import com.suit.model.core.common.EnumConstants.BaseModelStatus;
import com.suit.model.core.common.EnumConstants.BelongsType;
import com.suit.system.web.SysUserController;
import com.suit.util.JsonUtil;

@Controller
@RequestMapping(value = "/message")
public class MessageController extends BaseController<Message> {
	private static final Logger logger = LoggerFactory
			.getLogger(SysUserController.class);

	@Autowired
	private MessageService messageService;

	@Override
	protected BaseService<Message> getBaseService() {
		return messageService;
	}

	public MessageController() {
	}

	@ResponseBody
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(Message msg) {
		OperateStatus status = new OperateStatus(true,
				WebConstant.COMMON_SUCCESS_MSG);
		try {
			messageService.create(msg);
		} catch (CoreException e) {
			status.setSuccess(false);
			status.setMsg(e.getMessage());
		}
		return JsonUtil.genJson(status);
	}

	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getList(Message msg) {
		OperateStatus status = new OperateStatus(true,
				WebConstant.COMMON_SUCCESS_MSG);
		try {
			if (msg.getBelongsType().equals(BelongsType.DORM)) {
				status.setData(messageService.getListDorm(msg.getBelongsId()));
			} else if (msg.getBelongsType().equals(BelongsType.PERSON)) {
				status.setData(messageService.getListPerson(msg.getBelongsId()));
			}
		} catch (CoreException e) {
			status.setSuccess(false);
			status.setMsg(e.getMessage());
		}
		return JsonUtil.genJson(status);
	}

	@ResponseBody
	@RequestMapping(value = "deal", method = RequestMethod.POST)
	public String getList(String msgId, BaseModelStatus flag) {
		OperateStatus status = new OperateStatus(true,
				WebConstant.COMMON_SUCCESS_MSG);
		try {
			messageService.deal(msgId, flag);
		} catch (CoreException e) {
			status.setSuccess(false);
			status.setMsg(e.getMessage());
		}
		return JsonUtil.genJson(status);
	}

}
