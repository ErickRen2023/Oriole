package me.erickren.context;

import me.erickren.beans.factory.exception.BeanException;
import me.erickren.beans.factory.support.Aware;

/**
 * DateTime: 2023/10/01 - 20:37
 * Author: ErickRen
 */
public interface ApplicationContextAware extends Aware {

    /**
     * @param context Target Application context.
     * @throws BeanException Exception.
     */
    void setApplicationContext(ApplicationContext context) throws BeanException;
}
