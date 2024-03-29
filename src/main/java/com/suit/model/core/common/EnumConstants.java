package com.suit.model.core.common;

import java.io.Serializable;

/**
 * 所有的枚举常量
 * 
 * @author hsy
 * 
 */
public class EnumConstants {

	/**
	 * 基础类的状态枚举
	 * 
	 * @author syj
	 * 
	 */
	public static enum BaseModelStatus {
		/**
		 * 正常
		 */
		NORMAL,
		/**
		 * 冻结
		 */
		FROZEN,
		/**
		 * 逻辑删除
		 */
		DEL,
		/**
		 * 无效
		 */
		INVALID
	}
	
	/**
	 * 用户类型
	 * 
	 * @author hsy
	 * 
	 */
	public static enum SysUserType {
		/**
		 * 普通舍友
		 */
		PARTNER,
		/**
		 * 室长
		 */
		DORM_ADMIN,
		/**
		 * 管理员
		 */
		ADMIN
	}
	
	public static enum DormType{
		/**
		 * 一般宿舍
		 */
		NORMAL
	}
	
	/**
	 * 流程状态
	 * 
	 * @author hsy
	 * 
	 */
	public static enum FlowStatus {
		/**
		 * 未提交
		 */
		UNCOMMIT,
		/**
		 * 处理中
		 */
		PROCESSING,
		/**
		 * 处理完成
		 */
		COMPLETE,
		/**
		 * 退回
		 */
		REJECT
	}
	
	public static enum SMSType {
		/**
		 * 注册
		 */
		REG,
		/**
		 * 找回密码
		 */
		GETPSD,
		/**
		 * 修改密码
		 */
		EDITPSD,
		/**
		 * 修改手机号
		 */
		CHANGEPHONE,
		/**
		 * 接受邀请
		 */
		ACCEPTINV;
	}	
	
	/**
	 * 消息类型
	 * @author suit
	 *
	 */
	public static enum MessageType {
		/**
		 * 加入宿舍类型(宿舍)
		 */
		JOIN_IN,
		/**
		 * 普通用户消息(个人)
		 */
		NORMAL,
		/**
		 * 收到邀请(个人)
		 */
		INVITE
	}
	
	/**
	 * 属于类型
	 * @author suit
	 *
	 */
	public static enum BelongsType {
		/**
		 * 个人
		 */
		PERSON,
		/**
		 * 宿舍
		 */
		DORM
	}

	/**
	 *  属性类型
	 */
	public enum AttributeType implements Serializable{
		INT("int",11),
		STRING("String",20),
		BOOLEAN("boolean",0),
//		LONG("long",22),
		FLOAT("float",0),
		DOUBLE("double",0),
		DATE("date",0),
		TIME("time",0),
		DATETIME("datetime",0);

		private String name;
		private int length;

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		AttributeType(String name, int length){
			this.name = name;
			this.length = length;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
