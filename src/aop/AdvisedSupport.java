package aop;

import aop.Interface.MethodMatcher;
import lombok.Data;
import lombok.ToString;
import org.aopalliance.intercept.MethodInterceptor;

@Data
@ToString
public class AdvisedSupport {


    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;
}
