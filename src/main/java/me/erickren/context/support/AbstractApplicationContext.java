package me.erickren.context.support;

import me.erickren.beans.factory.ConfigurableListableBeanFactory;
import me.erickren.beans.factory.config.BeanFactoryPostProcessor;
import me.erickren.beans.factory.config.BeanPostProcessor;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.context.ApplicationEvent;
import me.erickren.context.ApplicationListener;
import me.erickren.context.ConfigurableApplicationContext;
import me.erickren.context.event.ApplicationEventMulticaster;
import me.erickren.context.event.ContextClosedEvent;
import me.erickren.context.event.ContextRefreshedEvent;
import me.erickren.context.event.SimpleApplicationEventMulticaster;
import me.erickren.core.io.resource.loader.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * Abstract Application context.
 * DateTime: 2023/09/27 - 17:34
 * Author: ErickRen
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeanException {
        // Refresh bean factory.
        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        invokeBeanFactoryPostProcessors(beanFactory);
        registerBeanPostProcessors(beanFactory);
        initApplicationEventMulticaster();
        registerListeners();

        beanFactory.preInstantiateSingletons();
        finishRefresh();
    }

    /**
     * Create BeanFactory and load the BeanDefinition.
     *
     * @throws BeanException Exception.
     */
    protected abstract void refreshBeanFactory() throws BeanException;

    /**
     * Get the bean factory.
     *
     * @return BeanFactory.
     */
    public abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * Call the BeanPostProcessor before create bean instance.
     *
     * @param beanFactory The bean factory.
     */
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * Register the BeanPostProcessor.
     *
     * @param beanFactory The bean factory.
     */
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    protected void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    protected void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        return getBeanFactory().getBeansOfType(type);
    }

    protected void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    public Object getBean(String name) throws BeanException {
        return getBeanFactory().getBean(name);
    }

    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    public void close() {
        doClose();
    }

    public void registerShutdownHook() {
        Thread shutdownHook = new Thread(this::doClose);
        Runtime.getRuntime().addShutdownHook(shutdownHook);

    }

    protected void doClose() {
        publishEvent(new ContextClosedEvent(this));
        destroyBeans();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }
}
