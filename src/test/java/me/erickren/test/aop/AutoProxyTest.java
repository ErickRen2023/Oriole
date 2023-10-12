package me.erickren.test.aop;

import me.erickren.context.support.ClassPathXmlApplicationContext;
import me.erickren.test.service.TargetService;
import org.junit.Test;

/**
 * DateTime: 2023/10/12 - 14:09
 * Author: ErickRen
 */
public class AutoProxyTest {
    
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");
        TargetService targetService = applicationContext.getBean("targetService", TargetService.class);
        targetService.test();
    }
}
