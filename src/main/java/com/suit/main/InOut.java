package com.suit.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.suit.base.domain.BaseModel;

@Entity
@Table(name = "main_inout")
public class InOut extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 金额
	 */
	@Column(precision = 8, scale = 2)
	private double money;

	/**
	 * 所属宿舍Id
	 */
	@Column(name = "belongs_id", length = 40)
	private String belongsId;

	/**
	 * 创建人名称
	 */
	@Column(length = 20)
	private String creator;

	/**
	 * 确认标识
	 */
	@Column
	private boolean confirm;

	/**
	 * 收款标识
	 */
	@Column
	private boolean accept;
	
	/**
	 * 用途
	 */
	@Column(length = 10)
	private String useIn;

	/**
	 * 备注
	 */
	@Column(length = 100)
	private String remark;
	
	/**
	 * 类型 
	 */
	private boolean inFlag; 

	public String getUseIn() {
		return useIn;
	}

	public void setUseIn(String useIn) {
		this.useIn = useIn;
	}

	public boolean isInFlag() {
		return inFlag;
	}

	public void setInFlag(boolean inFlag) {
		this.inFlag = inFlag;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getBelongsId() {
		return belongsId;
	}

	public void setBelongsId(String belongsId) {
		this.belongsId = belongsId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public String getUserIn() {
		return useIn;
	}

	public void setUserIn(String userIn) {
		this.useIn = userIn;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
