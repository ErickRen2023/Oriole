package me.erickren.beans.factory.support;

import me.erickren.beans.factory.config.BeanDefinition;

/**
 * DateTime: 2023/09/14 - 18:39
 * Author: ErickRen
 */
public interface BeanDefinitionRegistry {

    /**
     * Register BeanDefinition.
     *
     * @param beanName Bean name.
     * @param beanDefinition Bean definition. 
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
