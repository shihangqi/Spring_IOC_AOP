package ioc;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();


    public void addPropertyValue(PropertyValue propertyValue){

        //这里可以增加一些对propertyvAalue的一些操作
        propertyValueList.add(propertyValue);

    }

    public List<PropertyValue> getPropertyValues(){
        return propertyValueList;
    }


}
