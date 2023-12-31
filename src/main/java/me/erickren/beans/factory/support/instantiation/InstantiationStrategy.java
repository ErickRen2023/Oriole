package me.erickren.beans.factory.support.instantiation;

import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.exception.BeanException;

/**
 * DateTime: 2023/09/15 - 14:08
 * Author: ErickRen
 */
public interface InstantiationStrategy {

    /**
     * Instantiation of bean.
     *
     * @param beanDefinition Bean definition.
     * @return Object
     * @throws BeanException Exception.
     */
    Object instantiate(BeanDefinition beanDefinition) throws BeanException;
}
