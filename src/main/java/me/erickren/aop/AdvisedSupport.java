package me.erickren.aop;


import org.aopalliance.intercept.MethodInterceptor;

/**
 * DateTime: 2023/10/08 - 21:59
 * Author: ErickRen
 */
public class AdvisedSupport {
    
    private boolean cglibProxy = false;
    private TargetSource targetSource;
    private MethodInterceptor methodInterceptor;
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public boolean isCglibProxy() {
        return cglibProxy;
    }

    public void setCglibProxy(boolean cglibProxy) {
        this.cglibProxy = cglibProxy;
    }
}
