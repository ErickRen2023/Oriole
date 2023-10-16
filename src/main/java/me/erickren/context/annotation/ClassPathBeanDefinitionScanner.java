package me.erickren.context.annotation;

import cn.hutool.core.util.StrUtil;
import me.erickren.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.support.BeanDefinitionRegistry;
import me.erickren.stereotype.Component;

import java.util.Set;

/**
 * DateTime: 2023/10/16 - 12:28
 * Author: ErickRen
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {
    
    private BeanDefinitionRegistry registry;
	public static final String AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME = "me.erickren.context.annotation.internalAutowiredAnnotationProcessor";


	public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}
    
    public void doScan(String... basePackage) {
        for (String singlePackage: basePackage) {
            Set<BeanDefinition> candidate = findCandidateComponents(singlePackage);
            for (BeanDefinition singleCandidate: candidate) {
                // Parse bean scope.
                String beanScope = resolveBeanScope(singleCandidate);
                if (StrUtil.isNotEmpty(beanScope)) {
                    singleCandidate.setScope(beanScope);
                }
                // Create bean name.
                String beanName = determineBeanName(singleCandidate);
                // Register bean.
                registry.registerBeanDefinition(beanName, singleCandidate);
            }
        }
		registry.registerBeanDefinition(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME, new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }
    
    private String resolveBeanScope(BeanDefinition beanDefinition) {
		Class<?> beanClass = beanDefinition.getBeanClass();
		Scope scope = beanClass.getAnnotation(Scope.class);
		if (scope != null) {
			return scope.value();
		}

		return StrUtil.EMPTY;
	}
    
    private String determineBeanName(BeanDefinition beanDefinition) {
		Class<?> beanClass = beanDefinition.getBeanClass();
		Component component = beanClass.getAnnotation(Component.class);
		String value = component.value();
		if (StrUtil.isEmpty(value)) {
			value = StrUtil.lowerFirst(beanClass.getSimpleName());
		}
		return value;
	}
}
