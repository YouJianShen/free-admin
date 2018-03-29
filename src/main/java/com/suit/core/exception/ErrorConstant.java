package com.suit.core.exception;

/**
 * Created by hsy on 2014/11/7.
 */
public interface ErrorConstant {

	/**
	 * ************************* 错误码常量 ********************************
	 */

	public final static String ERROR_PARAM_IS_NULL = "common.paramIsNull";
	public final static String ERROR_ENTITY_IS_NULL = "common.entityIsNull";
	public final static String ERROR_RESULT_IS_NULL = "common.resultIsNull";
	public final static String ERROR_BATCH_DEL_FAIL = "common.batchDelFail";
	public final static String ERROR_BATCH_CREATE_FAIL = "common.batchCreateFail";
	public final static String ERROR_PARENT_ISNULL = "common.batchCreateFail";

	public final static String ERROR_ENTITY_IS_DELETED = "common.entityIsDeleted";// 该对象已经被删除

	public final static String ERROR_RE_OPERATION = "common.repetitiveOperation";
	public final static String ERROR_USER_IS_NOT_EXISTED = "common.developer.noFound";
	public final static String ERROR_USER_LOGIN_FAILED = "common.developer.loginFailed";
	public final static String ERROR_USER_OLDPASSWORD_FAILED = "common.developer.oldpassword.failed";
	public final static String ERROR_USER_FINDPASSWORD_INFOERROR = "common.developer.findPassword.infoError";
	public final static String ERROR_INVALID_OPERATE = "common.invalidOperate";
	public final static String VERSION_NOT_FOUND = "common.versionNotFound";
	public final static String RESOURCE_NOT_FOUND = "common.resourceNotFound";
	public final static String ERROR_OPERATE = "common.errorOperate";
	public final static String COMMON_USER_DEVICE_NOTREG = "common.user.device.notReg";
	public final static String NOT_ALLOW_DELETE = "common.notAllowDelete";
	public final static String REGISTER_PHONE_EXIST = "common.phone.hasExist";
	public final static String REGISTER_PASSWORD_ERROR = "common.passWordError";
	public final static String REGISTER_NAME_EXIST = "common.name.hasExist";
	public final static String NOT_ALLOW_REGIST = "common.notAllowRegist";	
}
