package me.erickren.beans.factory.support;

import me.erickren.beans.factory.BeanFactory;
import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.exception.BeanException;

/**
 * DateTime: 2023/09/14 - 18:53
 * Author: ErickRen
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeanException {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
		return createBean(beanName, beanDefinition);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

}
