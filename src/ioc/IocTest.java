package ioc;

import bean.User;
import ioc.xml.XmlBeanFactory;

public class IocTest {

    public static void main(String[] args) {
        try {
            XmlBeanFactory xmlBeanFactory = new XmlBeanFactory("F:\\JavaWorkSpace\\Spring_IOC&AOP\\src\\ioc\\ioc.xml");
            User user = (User)xmlBeanFactory.getBean("user");
            System.out.println(user);

        }catch (Throwable e){
            e.printStackTrace();
        }



    }
}
