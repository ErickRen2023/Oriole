package me.erickren.ioc;

import me.erickren.context.support.ClassPathXmlApplicationContext;
import me.erickren.ioc.beans.Person;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/10/02 - 17:01
 * Author: ErickRen
 */
public class FactoryBeanTest {
    
    @Test
    public void test() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        Person me = classPathXmlApplicationContext.getBean("me", Person.class);
        assertThat(me.getName()).isEqualTo("ErickRen-2023");
        classPathXmlApplicationContext.registerShutdownHook();
    }
}
