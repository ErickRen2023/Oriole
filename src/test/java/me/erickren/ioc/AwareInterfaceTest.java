package me.erickren.ioc;

import me.erickren.context.support.ClassPathXmlApplicationContext;
import me.erickren.service.TestService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/10/01 - 20:54
 * Author: ErickRen
 */
public class AwareInterfaceTest {
    
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:XMLTest.xml");
		TestService testService = applicationContext.getBean("testService", TestService.class);
		assertThat(testService.getApplicationContext()).isNotNull();
		assertThat(testService.getBeanFactory()).isNotNull();
    }
}
