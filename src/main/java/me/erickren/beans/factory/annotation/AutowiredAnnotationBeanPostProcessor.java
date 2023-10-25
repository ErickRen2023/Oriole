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
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (valueAnnotation != null) {
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }

        // Solve @Autowired
        for (Field field : fields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (autowiredAnnotation != null) {
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
				if (qualifierAnnotation != null) {
					dependentBeanName = qualifierAnnotation.value();
					dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
				} else {
					dependentBean = beanFactory.getBean(fieldType);
				}
				BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }
        return pvs;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeanException {
        return true;
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
