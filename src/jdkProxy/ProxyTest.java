package jdkProxy;

import bean.User;
import simple.ioc.SimpleIOC;

public class ProxyTest {

    public static void main(String[] args) throws Exception {

        SimpleIOC simpleIOC = new SimpleIOC("F:\\JavaWorkSpace\\Spring_IOC&AOP\\src\\simple\\ioc\\ioc.xml");
        User user = (User) simpleIOC.getBean("user");

        Subject subject = (Subject)Agent.agent(user);
        //因为代理类是通过实现和目标类一样的所有接口来实现代理功能的，
        //所以这里一定要强转成目标类的接口，因为之后接口中声明的类，代理类才能实现，
        //具体类中有可能有方法代理类并不能实现。
        subject.show();
    }
}
