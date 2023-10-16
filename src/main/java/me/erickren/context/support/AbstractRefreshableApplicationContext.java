package me.erickren.context.support;

import me.erickren.beans.factory.exception.BeanException;
import me.erickren.beans.factory.support.DefaultListableBeanFactory;

/**
 * DateTime: 2023/09/27 - 17:44
 * Author: ErickRen
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected final void refreshBeanFactory() throws BeanException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();

        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * Load BeanDefinitions
     *
     * @param beanFactory The bean factory.
     * @throws BeanException Exception.
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeanException;

    /**
     * Create BeanFactory.
     *
     * @return An implement for BeanFactory.
     */
    protected DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
