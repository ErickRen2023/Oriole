package me.erickren.aop.framework;

import me.erickren.aop.AdvisedSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * DateTime: 2023/10/10 - 20:34
 * Author: ErickRen
 */
public class CglibAopProxy implements AopProxy {
    
    private final AdvisedSupport advised;

    public CglibAopProxy(AdvisedSupport advise) {
        this.advised = advise;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
		enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
		enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
		return enhancer.create();
    }

    /**
     * Only support for cglib.
     */
    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

		private final AdvisedSupport advised;

		private DynamicAdvisedInterceptor(AdvisedSupport advised) {
			this.advised = advised;
		}

		@Override
		public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
			CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, objects, methodProxy);
			if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
				//代理方法
				return advised.getMethodInterceptor().invoke(methodInvocation);
			}
			return methodInvocation.proceed();
		}
	}
    
    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

		private final MethodProxy methodProxy;

		public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
			super(target, method, arguments);
			this.methodProxy = methodProxy;
		}

		@Override
		public Object proceed() throws Throwable {
			return this.methodProxy.invoke(this.target, this.arguments);
		}
	}
}
