package com.suit.util;

/**
 * 系统级静态常量. 可通过framework.properties初始化,同时保持常量static & final的特征.
 * 
 * @see ConfigurableContants
 */

public class Constants  extends ConfigurableContants{

	// 静态初始化读入framework.properties中的设置
		static {
			init("framework.properties");
		}

		/**
		 * 从framework.properties中读取constant.message_bundle_key的值，
		 * 如果配置文件不存在或配置文件中不存在该值时，默认取值"messages"
		 */
		public final static String MESSAGE_BUNDLE_KEY = getProperty(
				"constant.message_bundle_key", "i18n/messages");

		public final static String ERROR_BUNDLE_KEY = getProperty(
				"constant.error_bundle_key", "i18n/errors");

		public final static int DEFAULT_PAGE_SIZE = Integer.parseInt(getProperty(
				"constant.default_page_size", String.valueOf(15)));

		public final static String USER_IN_SESSION = getProperty(
				"constant.user_in_session", "loginUser");
		
		/**
		 * 邀请码超时时间（7天）
		 */
		public static final long invitationTimeout = 604800000;
}
