package me.erickren.test.aop;

import me.erickren.aop.AdvisedSupport;
import me.erickren.aop.ClassFilter;
import me.erickren.aop.TargetSource;
import me.erickren.test.aop.advice.TargetServiceBeforeAdvice;
import me.erickren.test.service.TargetService;
import me.erickren.test.service.impl.TargetServiceImpl;
import me.erickren.aop.advice.before.MethodBeforeAdviceInterceptor;
import me.erickren.aop.aspectj.AspectJExpressionPointcutAdvisor;
import me.erickren.aop.framework.ProxyFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

/**
 * DateTime: 2023/10/12 - 13:37
 * Author: ErickRen
 */
public class AdvisorTest {
    
    @Test
    public void test() {
        TargetService targetService = new TargetServiceImpl();
        MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(new TargetServiceBeforeAdvice());
		String expression = "execution(* me.erickren.test.service.TargetService.test(..))";
        
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
		advisor.setExpression(expression);
		advisor.setAdvice(methodInterceptor);

		ClassFilter classFilter = advisor.getPointcut().getClassFilter();
		if (classFilter.matches(targetService.getClass())) {
			AdvisedSupport advisedSupport = new AdvisedSupport();
			TargetSource targetSource = new TargetSource(targetService);
            
			advisedSupport.setTargetSource(targetSource);
			advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
			advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

			TargetService proxy = (TargetService) new ProxyFactory(advisedSupport).getProxy();
			proxy.test();
		}
    }
}
