package com.will.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * 全局切面拦截器
 *
 * @author Will
 * @create 2023:04:14 11:12
 **/
@Component
public class ThoughOutInterceptor implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    System.out.println("===========ThoughOutInterceptor==========");
    //火炬传递
    return invocation.proceed();
  }
}
