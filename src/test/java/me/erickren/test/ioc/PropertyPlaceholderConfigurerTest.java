package me.erickren.test.ioc;

import me.erickren.context.support.ClassPathXmlApplicationContext;
import me.erickren.test.beans.Person;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/10/16 - 11:33
 * Author: ErickRen
 */
public class PropertyPlaceholderConfigurerTest {
    
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:property-placeholder-configurer.xml");

        Person me = (Person) applicationContext.getBean("me");
        assertThat(me.getName()).isEqualTo("ERICKREN");
        System.out.println(me.getName());
    }
}
