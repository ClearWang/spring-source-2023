package com.will.bean;

import com.will.bean2.Student;
import com.will.bean3.Sporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.config.ContextNamespaceHandler;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类
 *
 * @author Will
 * @create 2023:04:03 11:03
 **/
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:spring.xml"})
public class NoAopTest {

  private AbstractApplicationContext applicationContext;
  //方式1
  //AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
  //方式2
  //AbstractApplicationContext applicationContext = new FileSystemXmlApplicationContext(
  //    "F:\\javago\\spring-study\\spring-source\\src\\main\\resources\\spring.xml");
  //方式3
  //AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Teacher.class);

  @Test
  public void test1() {
    //通过名字(name即id)的方式去beanFactory中获取bean name区分大小写
    Teacher teacher = (Teacher) applicationContext.getBean("teacher");
    //通过class方式的方式去beanFactory中获取bean
    //Teacher bean = applicationContext.getBean(Teacher.class);
    System.out.println("teacher:"+teacher.getName());
    Student student = (Student) applicationContext.getBean("student");
    System.out.println("student:"+student.getName());
    System.out.println("customerScopeTypeBean==>"+applicationContext.getBean(
        "customerScopeTypeBean"));
  }

  @Test
  public void test2(){
     //多线程去获取bean
     for (int i=0;i< 5;i++) {
       int finalI = i;
       new Thread(()->{
         if (finalI % 2 == 0) {
           System.out.println(Thread.currentThread().getName()+"========"+applicationContext.getBean("customerScopeTypeBean"));
           System.out.println(Thread.currentThread().getName()+"========"+applicationContext.getBean("customerScopeTypeBean"));
         } else {
           System.out.println(Thread.currentThread().getName()+"========"+applicationContext.getBean("customerScopeTypeBean"));
         }
       }).start();
     }
  }

  /**
   * 这里有个坑：通过@import方式导入的类的beanName是个全路径名而不是名字小写形式
   * 比如这里的Teacher 默认beanName为：com.will.bean.Teacher
   * 非@import注解方式导入的类beanName默认为首字母小写的类名 比如：teacher
   */
  @Test
  public void test3(){
    applicationContext = new AnnotationConfigApplicationContext(MyComponentScanBean.class);
    //Sporter sporter = (Sporter)applicationContext.getBean("sporter");
    //System.out.println("sporter:"+sporter.getName());
    //Teacher teacher = (Teacher) applicationContext.getBean("com.will.bean.Teacher");
    //System.out.println("teacher:"+teacher.getName());
    Teacher teacher = (Teacher) applicationContext.getBean("teacher");
    System.out.println("teacher2:"+teacher.getName());
  }
}
