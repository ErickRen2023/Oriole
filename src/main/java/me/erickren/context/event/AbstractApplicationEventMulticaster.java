package me.erickren.context.event;

import me.erickren.beans.factory.BeanFactory;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.beans.factory.support.BeanFactoryAware;
import me.erickren.context.ApplicationEvent;
import me.erickren.context.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

/**
 * DateTime: 2023/10/03 - 19:42
 * Author: ErickRen
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();
    private BeanFactory beanFactory;
    
    @Override
	public void addApplicationListener(ApplicationListener<?> listener) {
		applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
	}
    
    @Override
	public void removeApplicationListener(ApplicationListener<?> listener) {
		applicationListeners.remove(listener);
	}
    
    @Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
		this.beanFactory = beanFactory;
	}
}
