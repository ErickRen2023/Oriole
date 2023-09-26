package me.erickren.beans.factory.config;

import me.erickren.beans.factory.BeanFactory;
import me.erickren.beans.factory.exception.BeanException;

/**
 * DateTime: 2023/09/20 - 13:47
 * Author: ErickRen
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * Call the BeanPostProcessors method: postProcessBeforeInitialization().
     *
     * @param existBean Bean object.
     * @param beanName  Bean name.
     * @return Object.
     * @throws BeanException Exception.
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existBean, String beanName) throws BeanException;

    /**
     * Call the BeanPostProcessors method: postProcessAfterInitialization().
     *
     * @param existingBean Bean object.
     * @param beanName     Bean name.
     * @return Object.
     * @throws BeanException Exception.
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException;
}
