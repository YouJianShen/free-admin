package com.suit.core.web.mvc.servlet;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.web.servlet.DispatcherServlet;

import com.suit.core.constant.WebConstant;
import com.suit.core.util.SystemUtil;
import com.suit.shiro.ShiroUser;

public class MyDispatcherServlet extends DispatcherServlet {
	private static final Logger logger = LoggerFactory
			.getLogger(MyDispatcherServlet.class);

	private String SPLIT_FLAG = "|";

	private Marker marker;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyDispatcherServlet() {
		super();
		marker = new Marker() {


			public void add(Marker arg0) {
			}

			public boolean contains(Marker arg0) {
				return false;
			}

			public boolean contains(String arg0) {
				return false;
			}

			public String getName() {
				return "MyDispatcherServlet";
			}

			public boolean hasChildren() {
				return false;
			}

			public boolean hasReferences() {
				return false;
			}

			public Iterator<Marker> iterator() {
				return null;
			}

			public boolean remove(Marker arg0) {
				return false;
			}

		};
	}

	protected void doDispatch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = request.getRequestURL().toString();
		if (url.contains("static") || url.contains("weixin")|| url.contains("app")) {
			super.doDispatch(request, response);
			return;
		}

		boolean isMobile = false;
		if (url.contains("mobile")) {
			isMobile = true;
		}
		String userAccount = getUserAccount(request, isMobile);
		
		//打印请求开始日志
		long starttime = System.currentTimeMillis();
		String msg = makeInfoMsg(starttime, request, userAccount, isMobile);
		logger.info(marker, msg);
		super.doDispatch(request, response);
		
		//打印请求结束日志
		long costtime = System.currentTimeMillis() - starttime;
		StringBuffer costSb = new StringBuffer();
		costSb.append(starttime).append(SPLIT_FLAG);
		costSb.append(costtime).append(SPLIT_FLAG);
		costSb.append(userAccount).append(SPLIT_FLAG);
		costSb.append(isMobile);
		logger.info(marker, costSb.toString());
	}

	private String getUserAccount(HttpServletRequest request, boolean isMobile) {
		String account = "";
		if (isMobile) {
			account = request.getHeader(WebConstant.USER_ACCOUNT);
		} else {
			ShiroUser shiroUser = SystemUtil.getUser();
			if (shiroUser != null) {
				account = shiroUser.getUserAccount();
			}
		}

		if (account == null) {
			account = "";
		}
		return account;
	}

	private String makeInfoMsg(long starttime, HttpServletRequest request,
			String userAccount, boolean isMobile) {
		StringBuffer sb = new StringBuffer();
		sb.append(starttime).append(SPLIT_FLAG);
		sb.append(request.getRequestURL()).append(SPLIT_FLAG);

		// 获取所有参数
		Map map = new HashMap();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length < 1) {
				continue;
			}
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					map.put(paramName, paramValue);
				}
			} else {
				StringBuffer voSb = new StringBuffer();
				for (int i = 0; i < paramValues.length; i++) {
					if (i == 0) {
						voSb.append(paramValues[i]);
					} else {
						voSb.append(",").append(paramValues[i]);
					}
				}
				map.put(paramName, voSb.toString());
			}
		}
		sb.append(map.toString());
		sb.append(SPLIT_FLAG).append(userAccount);
		sb.append(SPLIT_FLAG).append(isMobile);
		String agent = request.getHeader("User-Agent"); 
		sb.append(SPLIT_FLAG).append(agent);
		return sb.toString();
	}

}
