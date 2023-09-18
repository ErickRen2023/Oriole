package me.erickren.beans.factory.config;

/**
 * Singleton bean registry.
 * DateTime: 2023/09/14 - 18:38
 * Author: ErickRen
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
