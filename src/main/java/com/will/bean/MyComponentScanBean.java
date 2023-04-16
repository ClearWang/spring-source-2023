package com.will.bean;

import com.will.annotation.MyAnnotationType;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * ComponentScan注解类测试
 * 这里有个坑：通过import方式导入的类的beanName是个全路径名而不是名字小写形式 比如这里的Teacher 默认beanName为：com.will.bean.Teacher
 * @author Will
 * @create 2023:04:07 19:22
 **/
@ComponentScan(basePackages = "com.will")
//@Import(Teacher.class)
@MyAnnotationType
@EnableAspectJAutoProxy
public class MyComponentScanBean {

}
