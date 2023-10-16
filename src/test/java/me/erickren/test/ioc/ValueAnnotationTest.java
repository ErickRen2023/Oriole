package me.erickren.test.ioc;

import me.erickren.context.support.ClassPathXmlApplicationContext;
import me.erickren.test.bean.Person;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/10/16 - 21:27
 * Author: ErickRen
 */
public class ValueAnnotationTest {
    
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:value-annotation.xml");

        Person me = applicationContext.getBean("me", Person.class);
        assertThat(me.getName()).isEqualTo("ERICKREN");
    }
}
