package me.erickren.beans.factory.support;

import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.exception.BeanException;

import java.lang.reflect.Constructor;

/**
 * DateTime: 2023/09/15 - 14:09
 * Author: ErickRen
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    /**
     * Create bean by NO-ARGS constructor.
     * @param beanDefinition Bean definition
     * @return Bean Object
     * @throws BeanException Failed to instantiate.
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeanException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            Constructor constructor = beanClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new BeanException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
