package me.erickren.ioc;

import me.erickren.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * DateTime: 2023/09/29 - 20:32
 * Author: ErickRen
 */
public class InitAndDestroyMethodTest {
    
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:XMLTest.xml");
		applicationContext.registerShutdownHook();
    }
}
