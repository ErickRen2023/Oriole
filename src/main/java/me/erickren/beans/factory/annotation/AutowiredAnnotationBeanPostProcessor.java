package me.erickren.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import me.erickren.beans.PropertyValues;
import me.erickren.beans.factory.BeanFactory;
import me.erickren.beans.factory.ConfigurableListableBeanFactory;
import me.erickren.beans.factory.config.InstantiationAwareBeanPostProcessor;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.beans.factory.support.BeanFactoryAware;

import java.lang.reflect.Field;

/**
 * DateTime: 2023/10/16 - 20:57
 * Author: ErickRen
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException {
        // Solve @Value
        Class<?> cls = bean.getClass();
        Field[] field = cls.getDeclaredFields();
        for (Field singleField : field) {
            Value valueAnnotation = singleField.getAnnotation(Value.class);
            if (valueAnnotation != null) {
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean, singleField.getName(), value);
            }
        }

        // Solve @Autowired

        // TODO
        return pvs;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException {
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return null;
    }
}
