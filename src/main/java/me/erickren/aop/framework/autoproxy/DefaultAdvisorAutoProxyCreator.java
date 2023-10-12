package me.erickren.aop.framework.autoproxy;

import me.erickren.aop.*;
import me.erickren.aop.aspectj.AspectJExpressionPointcutAdvisor;
import me.erickren.aop.framework.ProxyFactory;
import me.erickren.beans.factory.BeanFactory;
import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.config.InstantiationAwareBeanPostProcessor;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.beans.factory.support.BeanFactoryAware;
import me.erickren.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * DateTime: 2023/10/12 - 13:56
 * Author: ErickRen
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    
    private DefaultListableBeanFactory beanFactory;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException {
        // Solve endless loop
        if (isInfrastructureClass(beanClass)) {
            return null;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (classFilter.matches(beanClass)) {
                AdvisedSupport advisedSupport = new AdvisedSupport();

                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                Object bean = beanFactory.getInstantiationStrategy().instantiate(beanDefinition);
                TargetSource targetSource = new TargetSource(bean);
                advisedSupport.setTargetSource(targetSource);
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                return new ProxyFactory(advisedSupport).getProxy();
            }
        }
        return null;
    }
    
    private boolean isInfrastructureClass(Class<?> beanClass) {
		return Advice.class.isAssignableFrom(beanClass)
				|| Pointcut.class.isAssignableFrom(beanClass)
				|| Advisor.class.isAssignableFrom(beanClass);
	}

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
