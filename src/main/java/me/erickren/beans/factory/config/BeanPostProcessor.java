package me.erickren.beans.factory.config;

import me.erickren.beans.factory.exception.BeanException;

/**
 * DateTime: 2023/09/26 - 20:13
 * Author: ErickRen
 */
public interface BeanPostProcessor {

    /**
     * Call the method before call the bean initialization method.
     *
     * @param bean     The bean object.
     * @param beanName Bean name.
     * @return Object of bean.
     * @throws BeanException Exception.
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException;
    
    /**
     * Call the method after call the bean initialization method.
     *
     * @param bean     The bean object.
     * @param beanName Bean name.
     * @return Object of bean.
     * @throws BeanException Exception.
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException;
}
