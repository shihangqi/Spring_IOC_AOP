package simple.aop.service.impl;

import simple.aop.service.HelloService;

public class HelloServiceImpl implements HelloService {


    @Override
    public void sayHelloWorld() {
        System.out.println("hello world!");
    }
}
