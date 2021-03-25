package com.wangxuegang.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		SpringUtil.applicationContext = arg0;
	}

	public static Object getBean(String name) {
		if (applicationContext == null)
			return null;
		return applicationContext.getBean(name);
	}

	public static boolean containsBean(String name) {
		if (applicationContext == null)
			return false;
		return applicationContext.containsBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		if (applicationContext == null)
			return null;
		return applicationContext.getBean(clazz);
	}

}
