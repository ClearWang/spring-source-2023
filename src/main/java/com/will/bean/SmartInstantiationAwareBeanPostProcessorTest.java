package com.will.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * test
 *
 * @author Will
 * @create 2023:04:10 20:38
 **/
@Component
public class SmartInstantiationAwareBeanPostProcessorTest implements
    SmartInstantiationAwareBeanPostProcessor {

  @Override
  public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
    return null;
  }
}
