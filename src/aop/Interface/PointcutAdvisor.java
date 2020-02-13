package aop.Interface;

import simple.aop.Advice;

public interface PointcutAdvisor extends Advice {

    Pointcut getPointcut();

}
