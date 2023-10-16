package me.erickren.test.ioc;

import me.erickren.context.support.ClassPathXmlApplicationContext;
import me.erickren.test.bean.Person;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/10/16 - 12:36
 * Author: ErickRen
 */
public class PackageScanTest {
    
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:package-scan.xml");

        Person me = applicationContext.getBean("me", Person.class);
        me.setName("ErickRen");
        assertThat(me.getName()).isEqualTo("ErickRen");
    }
}
