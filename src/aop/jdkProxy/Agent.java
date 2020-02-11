package aop.jdkProxy;

import bean.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Agent {

    static class JdkProxySubject implements InvocationHandler {

        private Object object;

        public JdkProxySubject(Object object) {
            this.object = object;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("before");
            try {
                method.invoke(this.object, args);
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                System.out.println("after");
            }

            return null;
        }
    }


    public static Object agent(Class interfaceClazz, Object proxy) {
        return Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class[]{interfaceClazz},
                new JdkProxySubject(proxy));
    }

}
