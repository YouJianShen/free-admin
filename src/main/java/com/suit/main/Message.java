package com.suit.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.suit.base.domain.BaseModel;
import com.suit.model.core.common.EnumConstants.BelongsType;
import com.suit.model.core.common.EnumConstants.MessageType;

@Entity
@Table(name = "main_message")
public class Message extends BaseModel {
	/**
	 * status:0生效，1完成，2取消
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 归属id
	 */
	@Column(length = 40)
	private String belongsId;

	/**
	 * 属于类型
	 */
	@Enumerated
	private BelongsType belongsType;

	/**
	 * 消息类型
	 */
	@Enumerated
	private MessageType msgType;

	/**
	 * 消息内容
	 */
	@Column(length = 50)
	private String message;

	/**
	 * 发送人
	 */
	@Column(length = 50)
	private String sender;

	public String getBelongsId() {
		return belongsId;
	}

	public void setBelongsId(String belongsId) {
		this.belongsId = belongsId;
	}

	public MessageType getMsgType() {
		return msgType;
	}

	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BelongsType getBelongsType() {
		return belongsType;
	}

	public void setBelongsType(BelongsType belongsType) {
		this.belongsType = belongsType;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
}
