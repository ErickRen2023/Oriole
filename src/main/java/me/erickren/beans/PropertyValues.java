package me.erickren.beans;

import me.erickren.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean property list.
 * DateTime: 2023/09/18 - 11:27
 * Author: ErickRen
 */
public class PropertyValues {
    
    private final List<PropertyValue> propertyValueList = new ArrayList<>();
    
    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }
    
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * Get the propertyValue by name.
     * @param propertyName Property name.
     * @return The value if name exists.
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }
    
    
}
