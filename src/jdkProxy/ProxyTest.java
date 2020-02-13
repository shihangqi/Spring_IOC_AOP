package jdkProxy;

import bean.User;
import simple.ioc.SimpleIOC;

public class ProxyTest {

    public static void main(String[] args) throws Exception {

        SimpleIOC simpleIOC = new SimpleIOC("F:\\JavaWorkSpace\\Spring_IOC&AOP\\src\\simple.ioc\\simple.ioc.xml");
        User user = (User) simpleIOC.getBean("user");

        Subject subject = (Subject)Agent.agent(Subject.class,user);
        subject.show();
    }
}
