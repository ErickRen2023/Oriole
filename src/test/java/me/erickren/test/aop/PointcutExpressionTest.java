package me.erickren.test.aop;

import me.erickren.aop.aspectj.AspectJExpressionPointcut;
import me.erickren.test.service.TestService;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/10/07 - 17:23
 * Author: ErickRen
 */
public class PointcutExpressionTest {
    
    @Test
    public void test() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* me.erickren.test.service.TargetService.test(..))");
		Class<TestService> clazz = TestService.class;
		Method method = clazz.getDeclaredMethod("testMethod");

		assertThat(pointcut.matches(clazz)).isTrue();
		assertThat(pointcut.matches(method, clazz)).isTrue();
    }
}
