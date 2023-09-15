package me.erickren.beans.factory.config;

/**
 * The bean definition.
 * DateTime: 2023/09/14 - 18:34
 * Author: ErickRen
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
