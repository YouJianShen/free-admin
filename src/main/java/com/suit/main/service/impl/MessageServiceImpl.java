package com.suit.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.suit.core.exception.CoreException;
import com.suit.core.exception.ErrorConstant;
import com.suit.core.service.impl.BaseServiceImpl;
import com.suit.jpa.core.BaseDao;
import com.suit.main.Dorm;
import com.suit.main.Message;
import com.suit.main.dao.MessageDao;
import com.suit.main.service.DormService;
import com.suit.main.service.MessageService;
import com.suit.model.core.common.EnumConstants.BaseModelStatus;
import com.suit.model.core.common.EnumConstants.BelongsType;
import com.suit.model.core.common.EnumConstants.MessageType;

@Component("messageService")
@Transactional(readOnly = true)
public class MessageServiceImpl extends BaseServiceImpl<Message> implements
		MessageService {

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private DormService dormService;

	@Override
	protected BaseDao<Message> getBaseDao() {
		return messageDao;
	}

	@Override
	protected Class getEntityClass() {
		return Message.class;
	}

	public List<Message> getListDorm(String id) throws CoreException {
		return messageDao.findByIdAndBelongsType(id, BelongsType.DORM);
	}

	public List<Message> getListPerson(String id) throws CoreException {
		return messageDao.findByIdAndBelongsType(id, BelongsType.PERSON);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void create(Message msg) throws CoreException {
		if (msg.getMsgType().equals(MessageType.JOIN_IN)) {
			String name = msg.getMessage();
			msg.setBelongsType(BelongsType.DORM);
			if (!name.equals("")) {
				Dorm dorm = dormService.getByName(name);
				if (dorm != null) {
					List<Message> otherJoin = findBy(
							new String[] { "createUserId", "message" },
							new String[] { msg.getCreateUserId(),
									msg.getMessage() });
					for (int i = otherJoin.size() - 1; i >= 0; i--) {
						if (otherJoin.get(i).getMsgType()
								.equals(MessageType.JOIN_IN))
							throw new CoreException(
									ErrorConstant.COMMON_USER_DEVICE_NOTREG,
									"已申请加入该宿舍，请勿重新申请。");
					}
					msg.setBelongsId(dorm.getId());
					save(msg);
				} else {
					throw new CoreException(
							ErrorConstant.COMMON_USER_DEVICE_NOTREG, "不存在名称为"
									+ name + "的公寓");
				}
			}
		} else if (msg.getMsgType().equals(MessageType.INVITE)) {
			msg.setBelongsType(BelongsType.PERSON);
			throw new CoreException(ErrorConstant.COMMON_USER_DEVICE_NOTREG,
					"邀请功能暂时未做");
		} else {
			msg.setBelongsType(BelongsType.PERSON);
			save(msg);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public void deal(String msgId, BaseModelStatus status) throws CoreException {
		Message msg = getById(msgId);
		if (msg.getStatus().equals(BaseModelStatus.NORMAL)) {
			msg.setStatus(status);
			save(msg);
		} else {
			throw new CoreException(ErrorConstant.COMMON_USER_DEVICE_NOTREG,
					"该消息已处理");
		}
	}

}
