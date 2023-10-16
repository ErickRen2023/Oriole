package me.erickren.context.annotation;

import cn.hutool.core.util.ClassUtil;
import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * DateTime: 2023/10/16 - 12:22
 * Author: ErickRen
 */
public class ClassPathScanningCandidateComponentProvider {
    
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> cls : classes) {
            BeanDefinition beanDefinition = new BeanDefinition(cls);
            candidates.add(beanDefinition);
        }
        return candidates;        
    }
}
