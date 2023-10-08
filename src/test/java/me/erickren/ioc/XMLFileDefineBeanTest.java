package me.erickren.ioc;

import me.erickren.beans.Person;
import me.erickren.beans.factory.support.DefaultListableBeanFactory;
import me.erickren.beans.factory.xml.XmlBeanDefinitionReader;
import me.erickren.beans.Money;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/09/20 - 14:00
 * Author: ErickRen
 */
public class XMLFileDefineBeanTest {
    
    @Test
    public void test() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions("classpath:XMLTest.xml");

        Person me = (Person) beanFactory.getBean("me");
        System.out.println(me);
        assertThat(me.getName()).isEqualTo("ErickRen");
		assertThat(me.getMoney().getStatus()).isEqualTo("empty");

        Money myMoney = (Money) beanFactory.getBean("myMoney");
        System.out.println(myMoney);
        assertThat(myMoney.getStatus()).isEqualTo("empty");
    }
}
