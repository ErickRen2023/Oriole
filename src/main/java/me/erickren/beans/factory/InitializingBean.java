package me.erickren.beans.factory;

/**
 * DateTime: 2023/09/29 - 20:18
 * Author: ErickRen
 */
public interface InitializingBean {
    
    void afterPropertiesSet() throws Exception;
}
