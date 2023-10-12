package me.erickren.aop.aspectj;

import me.erickren.aop.Pointcut;
import me.erickren.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * AspectJ expression advisor 
 * DateTime: 2023/10/12 - 13:35
 * Author: ErickRen
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    
    private AspectJExpressionPointcut pointcut;
    private Advice advice;
    private String expression;
    
    public void setExpression(String expression) {
		this.expression = expression;
		pointcut = new AspectJExpressionPointcut(expression);
	}

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
