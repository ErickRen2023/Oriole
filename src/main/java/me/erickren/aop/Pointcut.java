package me.erickren.aop;

/**
 * DateTime: 2023/10/07 - 17:09
 * Author: ErickRen
 */
public interface Pointcut {
    
    ClassFilter getClassFilter();
    
    MethodMatcher getMethodMatcher();
}
