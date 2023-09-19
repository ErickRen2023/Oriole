package me.erickren.beans;

/**
 * Bean property
 * DateTime: 2023/09/18 - 11:25
 * Author: ErickRen
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Object getValue() {
        return this.value;
    }
}
