package com.will.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * aop aspect(切面 有两部分组成： pointCut+advice 切点+增强)
 *
 * @author Will
 * @create 2023:04:08 16:35
 **/
@Component
@Aspect
public class MyAopTest01 {

  /**
   * pointcut 切点
   */
  @Pointcut(value = "execution(public * com.will.service.*.*(..))")
  public void pointCut01(){}

  @Before(value = "pointCut01()")
  public void aopBefore() {
    System.out.println("======aopBefore======");
  }

  /**
   * advice 增强
   * @param joinPoint
   * @throws Throwable
   */
  @Around(value = "pointCut01()")
  public void aopAround(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("======aopAround begin======");
    joinPoint.proceed();
    System.out.println("======aopAround end======");
  }

}
