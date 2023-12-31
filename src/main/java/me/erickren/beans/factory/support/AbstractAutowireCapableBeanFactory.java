package me.erickren.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import me.erickren.beans.PropertyValue;
import me.erickren.beans.PropertyValues;
import me.erickren.beans.factory.DisposableBean;
import me.erickren.beans.factory.InitializingBean;
import me.erickren.beans.factory.config.*;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.beans.factory.support.instantiation.InstantiationStrategy;
import me.erickren.beans.factory.support.instantiation.SimpleInstantiationStrategy;

import java.lang.reflect.Method;

/**
 * Abstract Autowire Bean factory.
 * Implement the createBean() Method.
 * DateTime: 2023/09/14 - 22:29
 * Author: ErickRen
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        // Create proxy if needed.
        Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
        if (bean != null) {
            return bean;
        }
        return doCreateBean(beanName, beanDefinition);
    }

    /**
     * Call the {@link InstantiationAwareBeanPostProcessor} # postProcessBeforeInstantiation()
     */
    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (bean != null) {
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }
        return bean;
    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class beanClass, String beanName) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean;
        try {
            // Create bean.
            bean = createBeanInstance(beanDefinition);
            // Allow the BeanPostProcessor modify the property value.
            applyBeanPostprocessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
            // Populate bean.
            applyPropertyValues(beanName, bean, beanDefinition);
            // Call the Bean initialization method and BeanPostProcessor.
            initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeanException("Failed to instantiate [" + beanDefinition.getBeanClass().getName() + "]", e);
        }
        registerDisposableBean(beanName, bean, beanDefinition);

        // Add bean to singleton map.
        if (beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);
        }
        return bean;
    }

    /**
     * BeanPostProcessor modify property value support.
     *
     * @param beanName       Bean name.
     * @param bean           Bean object.
     * @param beanDefinition Bean definition.
     */
    protected void applyBeanPostprocessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
                if (pvs != null) {
                    for (PropertyValue propertyValue : pvs.getPropertyValues()) {
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }

    /**
     * Registry disposable bean.
     *
     * @param beanName       Bean name.
     * @param bean           Object bean.
     * @param beanDefinition Bean definition.
     */

    protected void registerDisposableBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (beanDefinition.isSingleton()) {
            if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
                registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
            }
        }
    }


    protected void initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }

        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        try {
            invokeInitMethod(beanName, wrappedBean, beanDefinition);
        } catch (Throwable ex) {
            throw new BeanException("Invocation of init method of bean[" + beanName + "] failed", ex);
        }

        applyBeanPostProcessorsAfterInitialization(bean, beanName);
    }

    /**
     * Call the bean initialization method.
     *
     * @param beanName       Bean name.
     * @param wrappedBean    Bean object.
     * @param beanDefinition Bean definition.
     */
    private void invokeInitMethod(String beanName, Object wrappedBean, BeanDefinition beanDefinition) throws Exception {
        if (wrappedBean instanceof InitializingBean) {
            ((InitializingBean) wrappedBean).afterPropertiesSet();
        }
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethodName);
            if (initMethod == null) {
                throw new BeanException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(wrappedBean);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existBean, String beanName) throws BeanException {
        Object result = existBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException {

        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return this.instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    /**
     * Populate bean with property values.
     *
     * @param beanName       Bean name.
     * @param bean           Bean Object.
     * @param beanDefinition Bean definition.
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // Bean reference instantiation.
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                // Populate bean with property values.
                BeanUtil.setFieldValue(bean, name, value);
            }

        } catch (Exception e) {
            throw new BeanException("Error setting property values for bean: " + beanName, e);
        }
    }
}
