package me.erickren.beans.factory;

import me.erickren.beans.PropertyValue;
import me.erickren.beans.PropertyValues;
import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.config.BeanFactoryPostProcessor;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.core.io.resource.Resource;
import me.erickren.core.io.resource.loader.DefaultResourceLoader;

import java.io.IOException;
import java.util.Properties;

/**
 * DateTime: 2023/10/16 - 11:27
 * Author: ErickRen
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {
    
    public static final String PLACEHOLDER_PREFIX = "${";
    public static final String PLACEHOLDER_SUFFIX = "}";
    private String location;
    
    @Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
		Properties properties = loadProperties();
        
		processProperties(beanFactory, properties);
	}
    
    private Properties loadProperties() {
		try {
			DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
			Resource resource = resourceLoader.getResource(location);
			Properties properties = new Properties();
			properties.load(resource.getInputStream());
			return properties;
		} catch (IOException e) {
			throw new BeanException("Could not load properties", e);
		}
	}
    
    private void processProperties(ConfigurableListableBeanFactory beanFactory, Properties properties) throws BeanException {
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (String beanName : beanDefinitionNames) {
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
			resolvePropertyValues(beanDefinition, properties);
		}
	}
    private void resolvePropertyValues(BeanDefinition beanDefinition, Properties properties) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            Object value = propertyValue.getValue();
            if (value instanceof String) {
                String strVal = (String) value;
                StringBuilder sb = new StringBuilder(strVal);
                int startIndex = strVal.indexOf(PLACEHOLDER_PREFIX);
                int endIndex = strVal.indexOf(PLACEHOLDER_SUFFIX);
                if (startIndex != - 1 && endIndex != - 1 && startIndex < endIndex) {
                    String propKey = strVal.substring(startIndex + 2, endIndex);
                    String propVal = properties.getProperty(propKey);
                    sb.replace(startIndex, endIndex + 1, propVal);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), sb.toString()));
                }
            }
        }
    }
    
    public void setLocation(String location) {
		this.location = location;
	}
}
