package ioc;

import ioc.factory.BeanFactory;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BeanReference {

    private String name;
    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }
}
