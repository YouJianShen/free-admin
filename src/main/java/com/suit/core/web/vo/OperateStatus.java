package com.suit.core.web.vo;

import java.io.Serializable;

public class OperateStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1999340161892195890L;
	/**
	 * 成功标志
	 */
	private boolean success;
	/**
	 * 消息
	 */
	private String msg;
	
	private String code;

	/**
	 * 返回数据
	 */
	private Object data;

	/**
	 * 返回数据备用字段
	 */
	private String other;
	public OperateStatus(){
		success = true;
		msg = "";
	}

	public OperateStatus(boolean success,String msg){
		this.success = success;
		this.msg = msg;
	}
	

	public OperateStatus(boolean success,String msg,String data){
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public OperateStatus(boolean success,String msg,String data,String other){
		this.success = success;
		this.msg = msg;
		this.data = data;
		this.other = other;
	}

	public String getCode() {
		return code;
	}

	public Object getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}
	public String getOther() {
		return other;
	}
	public boolean getSuccess() {
		return success;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void setOther(String other) {
		this.other = other;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

}
