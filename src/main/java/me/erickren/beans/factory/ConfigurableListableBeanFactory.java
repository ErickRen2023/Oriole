package me.erickren.beans.factory;

import me.erickren.beans.factory.config.AutowireCapableBeanFactory;
import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.config.ConfigurableBeanFactory;
import me.erickren.beans.factory.exception.BeanException;

/**
 * DateTime: 2023/09/20 - 13:41
 * Author: ErickRen
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * Get bean by name.
     *
     * @param beanName Bean name.
     * @return Bean definition.
     * @throws BeanException Exception.
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeanException;
}
