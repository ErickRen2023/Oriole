package me.erickren.beans.factory.support;

import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.config.BeanPostProcessor;
import me.erickren.beans.factory.config.ConfigurableBeanFactory;
import me.erickren.beans.factory.exception.BeanException;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Bean factory.
 * Implement the getBean() Method.
 * DateTime: 2023/09/14 - 18:53
 * Author: ErickRen
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String beanName) throws BeanException {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }
    
    @Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
		return ((T) getBean(name));
	}

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    @Override
	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
		//有则覆盖
		this.beanPostProcessors.remove(beanPostProcessor);
		this.beanPostProcessors.add(beanPostProcessor);
	}

	public List<BeanPostProcessor> getBeanPostProcessors() {
		return this.beanPostProcessors;
	}
}
