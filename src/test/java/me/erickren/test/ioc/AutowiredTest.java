package me.erickren.test.ioc;

import me.erickren.context.support.ClassPathXmlApplicationContext;
import me.erickren.test.bean.Person;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/10/23 - 11:44
 * Author: ErickRen
 */
public class AutowiredTest {
    
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:autowired-annotation.xml");
        Person bean = applicationContext.getBean(Person.class);
        assertThat(bean.getMoney()).isNotNull();
    }
}
