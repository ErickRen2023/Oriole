package me.erickren.beans.factory.support;

import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.beans.factory.support.instantiation.InstantiationStrategy;
import me.erickren.beans.factory.support.instantiation.SimpleInstantiationStrategy;

/**
 * Abstract Autowire Bean factory.
 * Implement the createBean() Method.
 * DateTime: 2023/09/14 - 22:29
 * Author: ErickRen
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();
    
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition);
        } catch (Exception e) {
            throw new BeanException("Failed to instantiate [" + beanDefinition.getBeanClass().getName() + "]", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }
    
    protected Object createBeanInstance(BeanDefinition beanDefinition) {
		return getInstantiationStrategy().instantiate(beanDefinition);
	}
    
    public InstantiationStrategy getInstantiationStrategy() {
        return this.instantiationStrategy;
    }
    
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
