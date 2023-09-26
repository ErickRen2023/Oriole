package me.erickren.beans.factory.config;

import me.erickren.beans.factory.HierarchicalBeanFactory;

/**
 * DateTime: 2023/09/20 - 13:48
 * Author: ErickRen
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * Add a BeanPostProcessor.
     * @param postProcessor BeanPostProcessor.
     */
    void addBeanPostProcessor(BeanPostProcessor postProcessor);
}
