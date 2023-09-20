package me.erickren.beans.factory.support;

import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.exception.BeanException;

/**
 * Bean definition registry interface.
 * DateTime: 2023/09/14 - 18:39
 * Author: ErickRen
 */
public interface BeanDefinitionRegistry {

    /**
     * Register BeanDefinition.
     *
     * @param beanName       Bean name.
     * @param beanDefinition Bean definition.
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * Find bean with name.
     *
     * @param beanName bean name.
     * @return BeanDefinition.
     * @throws BeanException Exception
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    /**
     * Contains bean definition or not by name.
     *
     * @param beanName name
     * @return true if contains.
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * All the bean definitions.
     *
     * @return An array of String.
     */
    String[] getBeanDefinitionNames();
}
