package simple.ioc;

import bean.Teacher;
import bean.User;

public class SimpleIOCTest {

    public static void main(String[] args) throws Exception{

//        String location = SimpleIOC.class.getClassLoader().getResource("simple.ioc.xml").getFile();
        SimpleIOC simpleIOC = new SimpleIOC("F:\\JavaWorkSpace\\Spring_IOC&AOP\\src\\simple.ioc\\simple.ioc.xml");
        User user = (User) simpleIOC.getBean("user");
        System.out.println(user);
        Teacher teacher = (Teacher) simpleIOC.getBean("teacher");
        System.out.println(teacher);


    }

}
