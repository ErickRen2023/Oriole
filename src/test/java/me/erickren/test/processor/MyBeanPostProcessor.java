package me.erickren.test.processor;

import me.erickren.beans.factory.config.BeanPostProcessor;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.test.bean.Money;

/**
 * DateTime: 2023/09/26 - 20:49
 * Author: ErickRen
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
		if ("myMoney".equals(beanName)) {
			((Money) bean).setStatus("Infinity");
		}
		return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
		return bean;
    }
}
