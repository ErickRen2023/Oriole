package me.erickren.test.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * DateTime: 2023/10/08 - 22:17
 * Author: ErickRen
 */
public class TargetServiceInterceptor implements MethodInterceptor {
    
    @Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("Before advice.");
		Object result = invocation.proceed();
		System.out.println("After advice");
		return result;
	}
}
