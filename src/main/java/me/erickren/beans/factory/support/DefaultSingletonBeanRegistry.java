package me.erickren.beans.factory.support;

import me.erickren.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * DateTime: 2023/09/14 - 18:54
 * Author: ErickRen
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object bean) {
        singletonObjects.put(beanName, bean);
    }
}
