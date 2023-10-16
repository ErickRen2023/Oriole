package me.erickren.beans.factory;

/**
 * DateTime: 2023/10/02 - 16:51
 * Author: ErickRen
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    boolean isSingleton();
}
