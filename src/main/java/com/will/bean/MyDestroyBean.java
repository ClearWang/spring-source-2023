package com.will.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * destroyBean
 *
 * @author Will
 * @create 2023:04:07 15:40
 **/
@Component
public class MyDestroyBean implements DisposableBean {

  @Override
  public void destroy() throws Exception {
    System.out.println("======MyDestroyBean.destroy======");
  }

  @PreDestroy
  public void preBeanDestroy(){
    System.out.println("======MyDestroyBean.preBeanDestroy======");
  }

  @PostConstruct
  public void postBeanConstruct(){
    System.out.println("======MyDestroyBean.postBeanConstruct======");
  }
}
