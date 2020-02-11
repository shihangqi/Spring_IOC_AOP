package aop;

import aop.service.HelloService;
import aop.service.impl.HelloServiceImpl;
import ioc.SimpleIOC;

public class SimpleAOPTest {

    public static void main(String[] args) {

        MethodInvocation logTask = () -> System.out.println("log task start");

        HelloServiceImpl HelloServiceImpl = new HelloServiceImpl();

        HelloService helloService1 = (HelloService)SimpleAOP.getProxy(HelloServiceImpl, new BeforeAdvice(HelloServiceImpl, logTask));

        helloService1.sayHelloWorld();
    }


}
