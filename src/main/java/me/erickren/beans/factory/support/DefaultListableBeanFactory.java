package me.erickren.beans.factory.support;

import me.erickren.beans.factory.ConfigurableListableBeanFactory;
import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.exception.BeanException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The final Bean factory.
 * DateTime: 2023/09/14 - 22:29
 * Author: ErickRen
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeanException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeanException("No bean named '" + beanName + "' is defined");
        }

        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        Map<String, T> result = new HashMap<>();
		beanDefinitionMap.forEach((beanName, beanDefinition) -> {
			Class beanClass = beanDefinition.getBeanClass();
			if (type.isAssignableFrom(beanClass)) {
				T bean = (T) getBean(beanName);
				result.put(beanName, bean);
			}
		});
		return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> beanNames = beanDefinitionMap.keySet();
        return beanNames.toArray(new String[beanNames.size()]);
    }
    
    @Override
	public void preInstantiateSingletons() throws BeanException {
		beanDefinitionMap.keySet().forEach(this::getBean);
	}
}
