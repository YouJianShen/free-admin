package com.suit.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public AppUtil(){
	}
	
	public static void setContext(ApplicationContext applicationContext){
		AppUtil.applicationContext = applicationContext;
	}

	public static Object getBean(String paramString) {
		return applicationContext.getBean(paramString);
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
