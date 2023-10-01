package me.erickren.beans.factory.support;

import me.erickren.beans.factory.BeanFactory;
import me.erickren.beans.factory.exception.BeanException;

/**
 * DateTime: 2023/10/01 - 20:34
 * Author: ErickRen
 */
public interface BeanFactoryAware extends Aware {

    /**
     * @param beanFactory Bean Factory.
     * @throws BeanException Exception.
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeanException;
}
