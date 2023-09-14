package me.erickren.ioc;

import me.erickren.beans.factory.BeanFactory;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/09/14 - 17:52
 * Author: ErickRen
 */
public class ContainerTest {

    @Test
    public void testGetBean() {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("testClass", new TestClass());

        TestClass testClass = (TestClass) beanFactory.getBean("testClass");
        assertThat(testClass).isNotNull();
        assertThat(testClass.helloWorld()).isEqualTo("Hello,World!");
    }

    class TestClass {
        public String helloWorld() {
            System.out.println("Hello,World!");
            return "Hello,World!";
        }
    }
}
