package ioc.xml;

import ioc.BeanDefinition;
import ioc.BeanDefinitionReader;
import ioc.BeanReference;
import ioc.PropertyValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class XmlBeanDefinitionReader implements BeanDefinitionReader {


    private Map<String, BeanDefinition> registry;

    public XmlBeanDefinitionReader() {
        registry = new HashMap<>();
    }



    @Override
    public void loadBeanDefinitions(String location) throws FileNotFoundException, Exception {

        InputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);
        Element root = doc.getDocumentElement();
        parseBeanDefinitions(root);
    }


    private void parseBeanDefinitions(Element root) throws Exception {
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                parseBeanDefinition(ele);
            }
        }
    }

    private void parseBeanDefinition(Element ele) throws Exception {

        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        Class beanClass = Class.forName(className);

        BeanDefinition beanDefinition= new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        beanDefinition.setBeanClass(beanClass
        );
        processProperty(ele, beanDefinition);
        registry.put(name, beanDefinition);
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNodes = ele.getElementsByTagName("property");
        for (int j = 0; j < propertyNodes.getLength(); j++) {
            Node propertyNode = propertyNodes.item(j);
            if (propertyNode instanceof Element) {
                Element propertyElement = (Element) propertyNode;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");

                if (value != null && value.length() > 0) {
                    // 将属性值填充到相关字段中
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyElement.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("ref config error");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,beanReference));
                }
            }
        }
    }


    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

}
