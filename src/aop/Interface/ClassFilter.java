package aop.Interface;

public interface ClassFilter {

    Boolean matchers(Class beanClass) throws Exception;
}
