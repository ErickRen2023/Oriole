package me.erickren.test.aop.advice;

import me.erickren.aop.advice.before.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * DateTime: 2023/10/11 - 11:18
 * Author: ErickRen
 */
public class TargetServiceBeforeAdvice implements MethodBeforeAdvice {
    
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("Before Advice");
    }
}
