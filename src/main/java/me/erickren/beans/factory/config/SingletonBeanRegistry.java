package me.erickren.beans.factory.config;

/**
 * Singleton bean registry.
 * DateTime: 2023/09/14 - 18:38
 * Author: ErickRen
 */
public interface SingletonBeanRegistry {

    /**
     * Get the single bean.
     *
     * @param beanName Bean name.
     * @return Object bean.
     */
    Object getSingleton(String beanName);

    void addSingleton(String beanName, Object singletonObject);
}
