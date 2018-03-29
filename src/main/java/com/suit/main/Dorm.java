package com.suit.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.suit.base.domain.BaseModel;
import com.suit.model.core.common.EnumConstants.DormType;

@Entity
@Table(name = "main_dorm")
public class Dorm extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	@Column(length = 40, unique = true, nullable = false)
	private String name;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 类型
	 */
	private DormType dormType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public DormType getDormType() {
		return dormType;
	}

	public void setDormType(DormType dormType) {
		this.dormType = dormType;
	}

}
