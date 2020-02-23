package jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Agent {
//
//    static class JdkProxySubject implements InvocationHandler {
//
//        private Object object;
//
//        public JdkProxySubject(Object object) {
//            this.object = object;
//        }
//
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//            System.out.println("before");
//            try {
//                method.invoke(this.object, args);
//            } catch (Throwable e) {
//                e.printStackTrace();
//            } finally {
//                System.out.println("after");
//            }
//
//            return null;
//        }
//    }


    public static Object agent(Object proxy) {
        ClassLoader classLoader = proxy.getClass().getClassLoader();
        Class<?>[] interfaces = proxy.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces,
                (object,method,args)->{
                    System.out.println("before");
                    try {
                        method.invoke(proxy, args);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("after");
                    }

                    return null;
                });
    }

}
