package me.erickren.test.service;

import me.erickren.beans.factory.BeanFactory;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.beans.factory.support.BeanFactoryAware;
import me.erickren.context.ApplicationContext;
import me.erickren.context.ApplicationContextAware;

/**
 * DateTime: 2023/10/01 - 20:52
 * Author: ErickRen
 */
public class TestService implements ApplicationContextAware, BeanFactoryAware {
	
	public static final String TEST_METHOD_MESSAGE = "Test Method";
    
    private ApplicationContext applicationContext;

	private BeanFactory beanFactory;
    
    @Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
        this.beanFactory = beanFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeanException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	public String testMethod() {
		System.out.println(TEST_METHOD_MESSAGE);
		return TEST_METHOD_MESSAGE;
	}
}
