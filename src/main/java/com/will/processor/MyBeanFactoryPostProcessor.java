package com.will.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * 测试类
 *
 * @author Will
 * @create 2023:04:04 20:01
 **/
@Component
public class MyBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor,
    PriorityOrdered, Ordered {

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
      throws BeansException {
    BeanDefinition teacher = registry.getBeanDefinition("teacher");
    MutablePropertyValues propertyValues = teacher.getPropertyValues();
    //修改bean的name属性值
    propertyValues.clearProcessedProperty("name");
    PropertyValue propertyValue = new PropertyValue("name","will2");
    propertyValues.addPropertyValue(propertyValue);
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    DefaultListableBeanFactory dbf = (DefaultListableBeanFactory) beanFactory;
    //解决带有@Transactional和@Async的循环依赖问题
    dbf.setAllowRawInjectionDespiteWrapping(true);
    //自定义scope 目标是使得同一个线程返回同一个对象实例 而不是每次都是创建一个新的实例对象
    beanFactory.registerScope("willScope", new Scope() {
      private final ThreadLocal<Object> myth = new ThreadLocal<>();

      @Override
      public Object get(String name, ObjectFactory<?> objectFactory) {
        Object scopedObject = myth.get();
        if (scopedObject == null) {
          scopedObject = objectFactory.getObject();
          myth.set(scopedObject);
        }
        return scopedObject;
      }

      @Override
      public Object remove(String name) {
        return null;
      }

      @Override
      public void registerDestructionCallback(String name, Runnable callback) {

      }

      @Override
      public Object resolveContextualObject(String key) {
        return null;
      }

      @Override
      public String getConversationId() {
        return null;
      }
    });
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
