package me.erickren.beans.factory.config;

/**
 * Bean reference.
 * DateTime: 2023/09/18 - 13:45
 * Author: ErickRen
 */
public class BeanReference {
    
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }
    
    public String getBeanName() {
        return this.beanName;
    }
}
