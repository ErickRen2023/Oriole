package me.erickren.aop;

import me.erickren.aop.advice.TargetServiceBeforeAdvice;
import me.erickren.aop.advice.before.MethodBeforeAdviceInterceptor;
import me.erickren.aop.aspectj.AspectJExpressionPointcut;
import me.erickren.aop.framework.ProxyFactory;
import me.erickren.common.TargetServiceInterceptor;
import me.erickren.service.TargetService;
import me.erickren.service.impl.TargetServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * DateTime: 2023/10/11 - 11:17
 * Author: ErickRen
 */
public class BAATAdviceTest {
    
    private AdvisedSupport advisedSupport;
    
    @Before
	public void setup() {
		TargetService worldService = new TargetServiceImpl();

		advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(worldService);
		TargetServiceInterceptor methodInterceptor = new TargetServiceInterceptor();
		MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* me.erickren.service.TargetService.test(..))").getMethodMatcher();
		advisedSupport.setTargetSource(targetSource);
		advisedSupport.setMethodInterceptor(methodInterceptor);
		advisedSupport.setMethodMatcher(methodMatcher);
	}
    
    @Test
    public void test() {
        TargetServiceBeforeAdvice beforeAdvice = new TargetServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor methodBeforeAdviceInterceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(methodBeforeAdviceInterceptor);
        
        TargetService proxy = (TargetService) new ProxyFactory(advisedSupport).getProxy();
        proxy.test();
    }
}
