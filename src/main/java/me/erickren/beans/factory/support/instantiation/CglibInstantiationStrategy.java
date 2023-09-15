package me.erickren.beans.factory.support.instantiation;

import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.beans.factory.support.instantiation.InstantiationStrategy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * DateTime: 2023/09/15 - 14:12
 * Author: ErickRen
 */
public class CglibInstantiationStrategy implements InstantiationStrategy {

    /**
     * Create bean by cglib.
     * @param beanDefinition Bean definition.
     * @return Bean Object.
     * @throws BeanException Failed to instantiate.
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeanException {
        Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(beanDefinition.getBeanClass());
		enhancer.setCallback((MethodInterceptor) (obj, method, argsTemp, proxy) -> proxy.invokeSuper(obj,argsTemp));
		return enhancer.create();
    }
}
