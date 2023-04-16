package com.will.processor;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

/**
 * bean实例化之后处理类
 *
 * @author Will
 * @create 2023:04:06 14:47
 **/
public class AfterPopulateBean implements InitializingBean, BeanNameAware {

  @Override
  public void afterPropertiesSet() throws Exception {
    //bean实例化完成后调用的方法
    System.out.println("AfterPopulateBean====>afterPropertiesSet");
  }

  @Override
  public void setBeanName(String name) {

  }
}
