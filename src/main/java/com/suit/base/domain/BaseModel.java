package com.suit.base.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import com.suit.model.core.common.EnumConstants;
import com.suit.model.core.common.EnumConstants.BaseModelStatus;

//JPA 基类的标识
@MappedSuperclass
public class BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * UUID
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	protected String id;
	/**
	 * 创建时间
	 */
	@Column(name="createtime")
	protected Date createTime;
	/**
	 * 上次修改时间
	 */
	@Column(name="lastupdatetime")
	protected Date lastUpdateTime;
	/**
	 * 创建人Id
	 */
	@Column(name="createuserid",length=40)
	private String createUserId;
	/**
	 * 状态控制字段
	 */
	@Enumerated
	//@Result(typeHandler=MyEnumOrdinalTypeHandler.class,javaType=BaseModelStatus.class,column="status")
	private EnumConstants.BaseModelStatus status = BaseModelStatus.NORMAL;
	
	public Date getCreateTime() {
		return createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public String getId() {
		if(StringUtils.isEmpty(id)){
			return "";
		}
		return id;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public EnumConstants.BaseModelStatus getStatus() {
		return status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setStatus(EnumConstants.BaseModelStatus status) {
		this.status = status;
	}
}
