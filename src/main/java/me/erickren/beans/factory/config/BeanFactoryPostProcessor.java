package me.erickren.beans.factory.config;

import me.erickren.beans.factory.ConfigurableListableBeanFactory;
import me.erickren.beans.factory.exception.BeanException;

/**
 * Allow users to modify the bean definition.
 * DateTime: 2023/09/26 - 20:05
 * Author: ErickRen
 */
public interface BeanFactoryPostProcessor {

    /**
     * Provide an interface to modify BeanDefinition before creating bean instance.
     *
     * @param beanFactory ConfigurableListableBeanFactory.
     * @throws BeanException Exception.
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException;
}
