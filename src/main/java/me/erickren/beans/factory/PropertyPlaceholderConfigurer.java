package me.erickren.beans.factory;

import me.erickren.beans.PropertyValue;
import me.erickren.beans.PropertyValues;
import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.config.BeanFactoryPostProcessor;
import me.erickren.beans.factory.exception.BeanException;
import me.erickren.core.io.resource.Resource;
import me.erickren.core.io.resource.loader.DefaultResourceLoader;
import me.erickren.utils.StringValueResolver;

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

        StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
        beanFactory.addEmbeddedValueResolver(valueResolver);
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
                value = resolvePlaceholder((String) value, properties);
                propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
            }
        }
    }

    private String resolvePlaceholder(String value, Properties properties) {
        StringBuilder sb = new StringBuilder(value);
        int startIndex = value.indexOf(PLACEHOLDER_PREFIX);
        int endIndex = value.indexOf(PLACEHOLDER_SUFFIX);
        if (startIndex != - 1 && endIndex != - 1 && startIndex < endIndex) {
            String propKey = value.substring(startIndex + 2, endIndex);
            String propVal = properties.getProperty(propKey);
            sb.replace(startIndex, endIndex + 1, propVal);
        }
        return sb.toString();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        public String resolveStringValue(String strVal) throws BeanException {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
        }
    }
}
