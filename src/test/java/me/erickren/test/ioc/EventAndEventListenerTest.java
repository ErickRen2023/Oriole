package me.erickren.test.ioc;

import me.erickren.context.support.ClassPathXmlApplicationContext;
import me.erickren.test.event.CustomEvent;
import org.junit.Test;

/**
 * DateTime: 2023/10/03 - 19:57
 * Author: ErickRen
 */
public class EventAndEventListenerTest {
    
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
		applicationContext.publishEvent(new CustomEvent(applicationContext));
    }
}
