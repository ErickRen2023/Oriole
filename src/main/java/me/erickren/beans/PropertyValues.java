package me.erickren.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean property list.
 * DateTime: 2023/09/18 - 11:27
 * Author: ErickRen
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue newPV) {
        for (PropertyValue pv : propertyValueList) {
            if (pv.getName().equals(newPV.getName())) {
                // Modify the property value.
                propertyValueList.set(propertyValueList.indexOf(pv), newPV);
                return;
            }
        }
        this.propertyValueList.add(newPV);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * Get the propertyValue by name.
     *
     * @param propertyName Property name.
     * @return The value if name exists or null.
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
