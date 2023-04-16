package com.will.bean;

import com.will.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Aop test
 *
 * @author Will
 * @create 2023:04:08 16:57
 **/
public class AopTest {

  private ApplicationContext applicationContext;

  @Before
  public void before(){
    applicationContext = new AnnotationConfigApplicationContext(MyComponentScanBean.class);
  }

  @Test
  public void test01(){
    OrderService orderService = (OrderService) applicationContext.getBean("orderService");
    orderService.getOrderNameById(1L);
  }

  @Test
  public void testTransactionAop() {
    OrderService orderService = (OrderService) applicationContext.getBean("orderService");
    orderService.getOrderByIdFromDb(1L);
  }
}
