package me.erickren.ioc;

import me.erickren.beans.factory.PropertyValue;
import me.erickren.beans.factory.PropertyValues;
import me.erickren.beans.factory.config.BeanDefinition;
import me.erickren.beans.factory.config.BeanReference;
import me.erickren.beans.factory.support.DefaultListableBeanFactory;
import me.erickren.ioc.beans.Money;
import me.erickren.ioc.beans.Person;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/09/14 - 17:52
 * Author: ErickRen
 */
public class BeanDefinitionAndContainerTest {

    @Test
    public void testGetBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(TestClass.class);
        beanFactory.registerBeanDefinition("testClass", beanDefinition);

        TestClass testClass = (TestClass) beanFactory.getBean("testClass");
        testClass.helloWorld();
    }
    
    @Test
    public void testPopulateBeanWithProperty() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("name", "ErickRen"));
		propertyValues.addPropertyValue(new PropertyValue("age", 18));
		BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
		beanFactory.registerBeanDefinition("me", beanDefinition);

		Person person = (Person) beanFactory.getBean("me");
		System.out.println(person);
		assertThat(person.getName()).isEqualTo("ErickRen");
		assertThat(person.getAge()).isEqualTo(18);
    }
    
    @Test
    public void testPopulateBeanWithBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // Create reference.
        PropertyValues moneyValues = new PropertyValues();
        moneyValues.addPropertyValue(new PropertyValue("count", 0));
        BeanDefinition moneyDefinition = new BeanDefinition(Money.class, moneyValues);
        beanFactory.registerBeanDefinition("myMoney", moneyDefinition);
        
        // Create bean.
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("name", "ErickRen"));
		propertyValues.addPropertyValue(new PropertyValue("age", 18));
        propertyValues.addPropertyValue(new PropertyValue("money", new BeanReference("myMoney")));
		BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
		beanFactory.registerBeanDefinition("me", beanDefinition);
        
        Person person = (Person) beanFactory.getBean("me");
        Money testMoney = person.getMoney();
        
        // Test
		assertThat(person.getName()).isEqualTo("ErickRen");
		assertThat(person.getAge()).isEqualTo(18);
		assertThat(testMoney).isNotNull();
		assertThat(testMoney.getCount()).isEqualTo(0);
    }
}
