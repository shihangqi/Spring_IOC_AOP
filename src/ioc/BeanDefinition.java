package ioc;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class BeanDefinition {

    private Object bean;

    private Class beanClass;

    private String beanClassName;

    private PropertyValues propertyValues = new PropertyValues();

}
