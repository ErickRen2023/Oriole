package me.erickren.beans.factory;

import me.erickren.beans.factory.exception.BeanException;

import java.util.Map;

/**
 * Listable bean factory interface.
 * DateTime: 2023/09/20 - 13:41
 * Author: ErickRen
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * Get all the beans by type.
     *
     * @param type Class Type.
     * @param <T>  Bean type.
     * @return Bean map.
     * @throws BeanException Exception.
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException;

    /**
     * Get all the bean name.
     *
     * @return An array of String.
     */
    String[] getBeanDefinitionNames();
}
