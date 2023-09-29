package me.erickren.beans.factory;

/**
 * DateTime: 2023/09/29 - 19:58
 * Author: ErickRen
 */
public interface DisposableBean {
    
    void destroy() throws Exception;
}
