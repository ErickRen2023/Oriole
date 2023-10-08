package me.erickren.beans;

import me.erickren.beans.factory.FactoryBean;

/**
 * DateTime: 2023/10/02 - 16:59
 * Author: ErickRen
 */
public class PersonFactoryBean implements FactoryBean<Person> {
    
    private String name;
    
    @Override
    public Person getObject() throws Exception {
        Person person = new Person();
        person.setName("ErickRen");
        return person;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
    
    public void setName(String name) {
		this.name = name;
	}
}
