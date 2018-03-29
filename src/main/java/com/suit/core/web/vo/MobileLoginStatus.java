package com.suit.core.web.vo;

/**
 * 移动登录返回的状态
 * 
 * @author hsy
 * 
 */
public class MobileLoginStatus extends OperateStatus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bookId;

	private String workgroupId;

	private String workgroupName;

	public MobileLoginStatus(boolean success, String msg) {
		super(success, msg);
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(String workgroupId) {
		this.workgroupId = workgroupId;
	}

	public String getWorkgroupName() {
		return workgroupName;
	}

	public void setWorkgroupName(String workgroupName) {
		this.workgroupName = workgroupName;
	}

}
