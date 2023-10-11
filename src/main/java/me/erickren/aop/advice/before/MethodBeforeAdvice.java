package me.erickren.aop.advice.before;

import java.lang.reflect.Method;

/**
 * DateTime: 2023/10/11 - 11:13
 * Author: ErickRen
 */
public interface MethodBeforeAdvice extends BeforeAdvice{
    
    void before(Method method, Object[] args, Object target);
}
