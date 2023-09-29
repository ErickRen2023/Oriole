package me.erickren.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import me.erickren.beans.factory.DisposableBean;
import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.exception.BeanException;

import java.lang.reflect.Method;

/**
 * DateTime: 2023/09/29 - 19:57
 * Author: ErickRen
 */
public class DisposableBeanAdapter implements DisposableBean {
    
    private final Object bean;

	private final String beanName;

	private final String destroyMethodName;


    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        
        /* Ensure that the bean implements DisposableBean 
           and its destruction method name is equals to "destroy", 
           the destruction operation is only called once. */
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
			// Call the bean destroy method.
			Method destroyMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
			if (destroyMethod == null) {
				throw new BeanException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
			}
			destroyMethod.invoke(bean);
		}
    }
}
