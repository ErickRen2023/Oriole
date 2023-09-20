package me.erickren.beans.factory.support;

import me.erickren.beans.factory.exception.BeanException;
import me.erickren.core.io.resource.Resource;
import me.erickren.core.io.resource.loader.ResourceLoader;

/**
 * Bean definition reader.
 * DateTime: 2023/09/20 - 12:24
 * Author: ErickRen
 */
public interface BeanDefinitionReader {

    /**
     * Get the bean definition registry.
     *
     * @return BeanDefinitionRegistry.
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * Get the bean loader.
     *
     * @return ResourceLoader.
     */
    ResourceLoader getResourceLoader();

    /**
     * Load bean definition from resource.
     *
     * @param resource Resource.
     * @throws BeanException Exception.
     */
    void loadBeanDefinitions(Resource resource) throws BeanException;


    /**
     * Load bean definition from location.
     *
     * @param location Location.
     * @throws BeanException Exception.
     */
    void loadBeanDefinitions(String location) throws BeanException;

    /**
     * Load bean definition from locations.
     *
     * @param locations An array of location.
     * @throws BeanException Exception.
     */
    void loadBeanDefinitions(String[] locations) throws BeanException;
}
