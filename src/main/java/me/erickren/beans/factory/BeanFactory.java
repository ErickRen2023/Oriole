package me.erickren.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * DateTime: 2023/09/14 - 17:49
 * Author: ErickRen
 */
public class BeanFactory {
    private Map<String, Object> beanMap = new HashMap<>();

    public void registerBean(String name, Object bean){
        beanMap.put(name, bean);
    }

    public Object getBean(String name) {
        return beanMap.get(name);
    }
}
