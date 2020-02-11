package aop;

import java.lang.reflect.Method;

public class BeforeAdvice implements Advice{

    private Object object;

    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object object, MethodInvocation methodInvocation) {
        this.object = object;
        this.methodInvocation = methodInvocation;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        methodInvocation.invoke();
        return method.invoke(object,args);
    }
}
