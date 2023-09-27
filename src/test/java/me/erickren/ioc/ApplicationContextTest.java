package me.erickren.ioc;

import me.erickren.context.support.ClassPathXmlApplicationContext;
import me.erickren.ioc.beans.Money;
import me.erickren.ioc.beans.Person;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * DateTime: 2023/09/27 - 17:54
 * Author: ErickRen
 */
public class ApplicationContextTest {
    
    @Test
    public void testApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:XMLTest.xml");
        Person me = applicationContext.getBean("me", Person.class);
        assertThat(me.getName()).isEqualTo("Ren");

        Money money = applicationContext.getBean("myMoney", Money.class);
        assertThat(money.getStatus()).isEqualTo("Infinity");
    }
}
