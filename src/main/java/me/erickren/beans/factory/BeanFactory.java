package me.erickren.beans.factory;

import me.erickren.beans.factory.exception.BeanException;

/**
 * Bean factory interface.
 * DateTime: 2023/09/14 - 17:49
 * Author: ErickRen
 */
public interface BeanFactory {

    /**
     * Get the bean.
     *
     * @param beanName Bean name.
     * @return Bean object.
     * @throws BeanException Bean not found.
     */
    Object getBean(String beanName) throws BeanException;

    /**
     * Get bean by type.
     *
     * @param name     Bean name.
     * @param beanType Bean type.
     * @return The bean.
     * @throws BeanException Bean not found.
     */
    <T> T getBean(String name, Class<T> beanType) throws BeanException;
    
    <T> T getBean(Class<T> requiredType) throws BeanException;
}
