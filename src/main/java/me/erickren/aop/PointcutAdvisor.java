package me.erickren.aop;

/**
 * DateTime: 2023/10/12 - 13:33
 * Author: ErickRen
 */
public interface PointcutAdvisor extends Advisor {
    
    Pointcut getPointcut();
}
