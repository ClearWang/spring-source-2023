package com.will.bean;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 测试类
 *
 * @author Will
 * @create 2023:04:03 11:12
 **/
@Data
@Service
public class Teacher {
  private String name = "will";
  private Integer age = 28;
}
