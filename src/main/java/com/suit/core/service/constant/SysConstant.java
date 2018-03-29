package com.suit.core.service.constant;

import java.util.List;

/**
 * 系统运行常量
 * 
 * @author hsy
 * 
 */
public class SysConstant {
	/**
	 * 信鸽推送accessid
	 */
	private long xgAccessid;
	/**
	 * 信鸽推送secretKey
	 */
	private String xgSecretKey;

	/**
	 * 信鸽推送IOS的accessid
	 */
	private long xgIOSAccid;

	/**
	 * 信鸽推送的IOS的secretKey
	 */
	private String xgIOSKey;
	/**
	 * websocket允许建立连接的域列表
	 */
	private List<String> websoketOriginList;

	/**
	 * 存储影像件物理基础目录地址
	 */
	private String imgBasePath;

	private String imgHttpPath;

	/**
	 * 下面是针对七牛云相关常量的配置
	 */
	private String qnAK;
	private String qnSK;
	private String qnDomain;// 域名
	private String qnBucket;// bucket
	private long qnTkTime;//
	private String qnCallback;// 回调地址
	private String zfbCallback;//支付宝回调地址
	
	private String wxCallback;//微信回调地址

	/**
	 * 体验账套工作组Id
	 */
	private String expGroupId;
	
	/**
	 * 生成订单的抬头字符串
	 */
	private String orderHead;

	public String getExpGroupId() {
		return expGroupId;
	}


	public String getImgBasePath() {
		return imgBasePath;
	}


	public String getImgHttpPath() {
		return imgHttpPath;
	}


	public String getOrderHead() {
		return orderHead;
	}

	public String getQnAK() {
		return qnAK;
	}

	public String getQnBucket() {
		return qnBucket;
	}

	public String getQnCallback() {
		return qnCallback;
	}

	public String getQnDomain() {
		return qnDomain;
	}

	public String getQnSK() {
		return qnSK;
	}

	public long getQnTkTime() {
		return qnTkTime;
	}

	public List<String> getWebsoketOriginList() {
		return websoketOriginList;
	}

	public String getWxCallback() {
		return wxCallback;
	}


	public long getXgAccessid() {
		return xgAccessid;
	}

	public long getXgIOSAccid() {
		return xgIOSAccid;
	}

	public String getXgIOSKey() {
		return xgIOSKey;
	}

	public String getXgSecretKey() {
		return xgSecretKey;
	}

	public String getZfbCallback() {
		return zfbCallback;
	}
	public void setExpGroupId(String expGroupId) {
		this.expGroupId = expGroupId;
	}
	public void setImgBasePath(String imgBasePath) {
		this.imgBasePath = imgBasePath;
	}

	public void setImgHttpPath(String imgHttpPath) {
		this.imgHttpPath = imgHttpPath;
	}

	public void setOrderHead(String orderHead) {
		this.orderHead = orderHead;
	}

	public void setQnAK(String qnAK) {
		this.qnAK = qnAK;
	}
	public void setQnBucket(String qnBucket) {
		this.qnBucket = qnBucket;
	}
	
	public void setQnCallback(String qnCallback) {
		this.qnCallback = qnCallback;
	}

	public void setQnDomain(String qnDomain) {
		this.qnDomain = qnDomain;
	}

	public void setQnSK(String qnSK) {
		this.qnSK = qnSK;
	}

	public void setQnTkTime(long qnTkTime) {
		this.qnTkTime = qnTkTime;
	}

	public void setWebsoketOriginList(List<String> websoketOriginList) {
		this.websoketOriginList = websoketOriginList;
	}

	public void setWxCallback(String wxCallback) {
		this.wxCallback = wxCallback;
	}

	public void setXgAccessid(long xgAccessid) {
		this.xgAccessid = xgAccessid;
	}

	public void setXgIOSAccid(long xgIOSAccid) {
		this.xgIOSAccid = xgIOSAccid;
	}

	public void setXgIOSKey(String xgIOSKey) {
		this.xgIOSKey = xgIOSKey;
	}

	public void setXgSecretKey(String xgSecretKey) {
		this.xgSecretKey = xgSecretKey;
	}


	public void setZfbCallback(String zfbCallback) {
		this.zfbCallback = zfbCallback;
	}

}
