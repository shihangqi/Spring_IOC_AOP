package ioc.xml;

import ioc.*;
import ioc.factory.BeanFactory;
import ioc.factory.BeanFactoryAware;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private List<String> beanDefinitionNames = new ArrayList<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    private XmlBeanDefinitionReader beanDefinitionReader;


    public XmlBeanFactory(String location) throws  Exception {
        beanDefinitionReader = new XmlBeanDefinitionReader();
        loadBeanDefinitions(location);
    }

    private void loadBeanDefinitions(String location) throws Exception {
        beanDefinitionReader.loadBeanDefinitions(location);
        registerBeanDefinition();
        registerBeanPostProcessor();
    }


    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("no this bean with name " + name);
        }

        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = createBean(beanDefinition);
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
        }

        return bean;

    }

    private Object initializeBean(Object bean, String name) throws Exception{

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }

        return bean;
    }

    private Object createBean(BeanDefinition beanDefinition) throws Exception{
        Object bean = beanDefinition.getBeanClass().newInstance();
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues().getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference){

                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }

            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                                + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean,value);

            }catch (Throwable e){
                e.printStackTrace();
            }



        }

    }


    private void registerBeanDefinition() {
        for (Map.Entry<String, BeanDefinition> entry:beanDefinitionReader.getRegistry().entrySet()) {
            String name = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            beanDefinitionMap.put(name, beanDefinition);
            beanDefinitionNames.add(name);
        }
    }


    private void registerBeanPostProcessor() throws Exception{
        List beans = getBeansForType(BeanPostProcessor.class);
        for (Object bean : beans) {
            addBeanPostProcessor((BeanPostProcessor) bean);
        }
        
    }

    private void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

        beanPostProcessors.add(beanPostProcessor);

    }

    private List getBeansForType(Class type)throws  Exception {

        List beans = new ArrayList<>();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
            System.out.println(beanDefinitionMap.get(beanDefinitionName).getBeanClass());
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }


}
