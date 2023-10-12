package me.erickren.beans.factory.config;

import me.erickren.beans.factory.exception.BeanException;

/**
 * DateTime: 2023/10/12 - 13:59
 * Author: ErickRen
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * Called before creating bean instance.
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException;
}
