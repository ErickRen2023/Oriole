package me.erickren.beans.factory;

import me.erickren.beans.factory.exception.BeanException;

/**
 * DateTime: 2023/09/14 - 17:49
 * Author: ErickRen
 */
public interface BeanFactory {

    /**
     * Get the bean.
     *
     * @param beanName Bean name.
     * @return Bean object.
     * @throws BeanException Bean not found.
     */
    Object getBean(String beanName) throws BeanException;
}
