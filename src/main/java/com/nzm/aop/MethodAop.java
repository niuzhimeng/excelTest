package com.nzm.aop;

import com.nzm.model.vo.BatchVo;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Parameter;

/**
 * Created by Nzm on 2017/12/27.
 */
public class MethodAop implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Object[] arguments = methodInvocation.getArguments();
        String one = (String) arguments[0];
        one = "拦截器改的参数" + one;

        arguments[0] = one;
        Object proceed = methodInvocation.proceed();
        String name = methodInvocation.getMethod().getName();
        Parameter[] parameters = methodInvocation.getMethod().getParameters();

        Parameter parameter = parameters[0];
        BatchVo batchVo = (BatchVo) proceed;
        batchVo.setBatchType("拦截器加的返回结果");
        return batchVo;
    }
}
