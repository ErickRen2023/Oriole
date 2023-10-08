package me.erickren.aop;

import java.lang.reflect.Method;

/**
 * DateTime: 2023/10/07 - 17:09
 * Author: ErickRen
 */
public interface MethodMatcher {
    
    boolean matches(Method method, Class<?> targetClass);
}
