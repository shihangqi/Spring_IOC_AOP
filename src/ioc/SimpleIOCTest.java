package ioc;

import ioc.bean.Teacher;
import ioc.bean.User;

public class SimpleIOCTest {

    public static void main(String[] args) throws Exception{

//        String location = SimpleIOC.class.getClassLoader().getResource("ioc.xml").getFile();
        SimpleIOC bf = new SimpleIOC("F:\\JavaWorkSpace\\Spring_IOC&AOP\\src\\ioc\\ioc.xml");
        User wheel = (User) bf.getBean("user");
        System.out.println(wheel);
        Teacher car = (Teacher) bf.getBean("teacher");
        System.out.println(car);


    }

}
