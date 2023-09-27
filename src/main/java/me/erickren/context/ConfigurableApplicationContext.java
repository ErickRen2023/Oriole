package me.erickren.context;

import me.erickren.beans.factory.exception.BeanException;

/**
 * DateTime: 2023/09/27 - 17:33
 * Author: ErickRen
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * Refresh the container.
     *
     * @throws BeanException Exception.
     */
    void refresh() throws BeanException;
}
