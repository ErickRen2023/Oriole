package me.erickren.test.ioc;

import me.erickren.context.support.ClassPathXmlApplicationContext;
import me.erickren.test.service.TestService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/10/02 - 16:38
 * Author: ErickRen
 */
public class PrototypeTest {
    
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:XMLTest.xml");
        TestService testService1 = applicationContext.getBean("testService", TestService.class);
        TestService testService2 = applicationContext.getBean("testService", TestService.class);
        assertThat(testService1 != testService2).isTrue();
    }
}
