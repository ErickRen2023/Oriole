package me.erickren.context.support;

import me.erickren.beans.factory.support.DefaultListableBeanFactory;
import me.erickren.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * DateTime: 2023/09/27 - 17:49
 * Author: ErickRen
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * Get the config location.
     * @return String location.
     */
    protected abstract String[] getConfigLocations();
}
