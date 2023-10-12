package me.erickren.test.ioc;

import me.erickren.test.beans.Person;
import me.erickren.beans.factory.support.DefaultListableBeanFactory;
import me.erickren.beans.factory.xml.XmlBeanDefinitionReader;
import me.erickren.test.beans.Money;
import me.erickren.test.processor.MyBeanFactoryPostProcessor;
import me.erickren.test.processor.MyBeanPostProcessor;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DateTime: 2023/09/26 - 20:41
 * Author: ErickRen
 */
public class BeanPostProcessorTest {
    
    @Test
    public void testBeanFactoryPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions("classpath:XMLTest.xml");

		// Modify bean property value.
		MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
		beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

		Person me = (Person) beanFactory.getBean("me");
		System.out.println(me);
		assertThat(me.getName()).isEqualTo("Ren");
    }
	
	@Test
	public void testBeanPostProcessor() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions("classpath:XMLTest.xml");
		MyBeanPostProcessor customerBeanPostProcessor = new MyBeanPostProcessor();
		beanFactory.addBeanPostProcessor(customerBeanPostProcessor);
		Money myMoney = (Money) beanFactory.getBean("myMoney");
		System.out.println(myMoney);
		assertThat(myMoney.getStatus()).isEqualTo("Infinity");
	}
}
