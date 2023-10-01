package me.erickren.context.support;

import me.erickren.beans.factory.config.BeanPostProcessor;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.context.ApplicationContext;
import me.erickren.context.ApplicationContextAware;

/**
 * DateTime: 2023/10/01 - 20:38
 * Author: ErickRen
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    
    private final ApplicationContext applicationContext;


    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if (bean instanceof ApplicationContextAware) {
			((ApplicationContextAware) bean).setApplicationContext(applicationContext);
		}
		return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
}
