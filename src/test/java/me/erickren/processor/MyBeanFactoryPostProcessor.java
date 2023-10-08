package me.erickren.processor;

import me.erickren.beans.PropertyValue;
import me.erickren.beans.PropertyValues;
import me.erickren.beans.factory.ConfigurableListableBeanFactory;
import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.config.BeanFactoryPostProcessor;
import me.erickren.beans.factory.exception.BeanException;

/**
 * DateTime: 2023/09/26 - 20:43
 * Author: ErickRen
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        BeanDefinition definition = beanFactory.getBeanDefinition("me");
		PropertyValues propertyValues = definition.getPropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("name", "Ren"));
    }
}
