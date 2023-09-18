package me.erickren.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import me.erickren.beans.factory.PropertyValue;
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
        Object bean;
        try {
            bean = createBeanInstance(beanDefinition);
            applyPropertyValues(beanName, bean, beanDefinition);
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

    /**
     * Populate bean with property values.
     * @param beanName Bean name.
     * @param bean Bean Object.
     * @param beanDefinition Bean definition.
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                BeanUtil.setFieldValue(bean, name, value);
            }
            
        } catch (Exception e) {
            throw new BeanException("Error setting property values for bean: " + beanName, e);
        }
    }
}
