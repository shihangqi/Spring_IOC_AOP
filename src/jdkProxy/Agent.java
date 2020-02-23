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
        //获取目标类的类加载器，用来加载代理对象所属类
        //这里的类加载器不用管是谁的，类加载器可以加载不同的类。
        ClassLoader classLoader = proxy.getClass().getClassLoader();
        //获取目标对象实现的所有接口的class，代理类会和目标类实现相同的接口。
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
