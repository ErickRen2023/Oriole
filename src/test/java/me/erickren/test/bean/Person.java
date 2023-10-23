package me.erickren.test.bean;

import me.erickren.beans.factory.DisposableBean;
import me.erickren.beans.factory.InitializingBean;
import me.erickren.beans.factory.annotation.Autowired;
import me.erickren.beans.factory.annotation.Value;
import me.erickren.stereotype.Component;

/**
 * Test bean.
 * DateTime: 2023/09/18 - 11:50
 * Author: ErickRen
 */

@Component("me")
public class Person implements InitializingBean, DisposableBean {

    @Value("${name}")
    private String name;

    @Autowired
    private Money money;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void myInitMethod() {
        System.out.println("Init method...");
    }

    public void myDestroyMethod() {
        System.out.println("My Destroy method...");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("AfterPropertiesSet method...");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean Method...");
    }
}