package com.suit.main.vo;

import java.util.List;

import com.suit.main.Dorm;
import com.suit.model.system.core.SysUser;

public class DormVo {
	
	private Dorm dormInfo;
	
	/**
	 * 剩余室费
	 */
	private double remain;
	
	/**
	 * 总支出
	 */
	private double expense;
	
	/**
	 * 上月支出
	 */
	private double prePay;
	
	/**
	 * 本月已支出
	 */
	private double thenPay;
	
	/**
	 * 宿舍成员
	 */
	private String partners;
	
	public double getRemain() {
		return remain;
	}

	public void setRemain(double remain) {
		this.remain = remain;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public Dorm getDormInfo() {
		return dormInfo;
	}

	public void setDormInfo(Dorm dormInfo) {
		this.dormInfo = dormInfo;
	}

	public double getPrePay() {
		return prePay;
	}

	public void setPrePay(double prePay) {
		this.prePay = prePay;
	}

	public double getThenPay() {
		return thenPay;
	}

	public void setThenPay(double thenPay) {
		this.thenPay = thenPay;
	}

	public String getPartners() {
		return partners;
	}

	public void setPartners(String partners) {
		this.partners = partners;
	}
}
