package me.erickren.test.aop;

import me.erickren.aop.AdvisedSupport;
import me.erickren.aop.MethodMatcher;
import me.erickren.aop.TargetSource;
import me.erickren.aop.aspectj.AspectJExpressionPointcut;
import me.erickren.aop.framework.JdkDynamicAopProxy;
import me.erickren.test.common.TargetServiceInterceptor;
import me.erickren.test.service.TargetService;
import me.erickren.test.service.impl.TargetServiceImpl;
import org.junit.Test;

/**
 * DateTime: 2023/10/08 - 22:08
 * Author: ErickRen
 */
public class JdkDynamicProxyTest {
    
    @Test
    public void test() {
        TargetService targetService = new TargetServiceImpl();

		AdvisedSupport advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(targetService);
		TargetServiceInterceptor methodInterceptor = new TargetServiceInterceptor();
		MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* me.erickren.test.service.TargetService.test(..))").getMethodMatcher();
		advisedSupport.setTargetSource(targetSource);
		advisedSupport.setMethodInterceptor(methodInterceptor);
		advisedSupport.setMethodMatcher(methodMatcher);

		TargetService proxy = (TargetService) new JdkDynamicAopProxy(advisedSupport).getProxy();
		proxy.test();
    }
}
