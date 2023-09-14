package me.erickren.ioc;

import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * DateTime: 2023/09/14 - 17:52
 * Author: ErickRen
 */
public class BeanDefinitionAndContainerTest {

    @Test
    public void testGetBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(TestClass.class);
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        TestClass testClass = (TestClass) beanFactory.getBean("helloService");
        testClass.helloWorld();
    }
}
