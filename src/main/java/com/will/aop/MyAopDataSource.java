package com.will.aop;

import com.will.annotation.MyTargetSource;
import com.will.comm.DynamicDataSourceCache;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * datasource 切面
 * Order保证先于事务切面执行
 * @author Will
 * @create 2023:04:14 11:57
 **/
@Component
@Aspect
@Order(-100)
public class MyAopDataSource {

  @Before(value = "@annotation(targetSource)",argNames = "targetSource")
  public void xxx(MyTargetSource targetSource) {
    System.out.println("======aopBefore  MyAopDataSource======");
    DynamicDataSourceCache.setDsCache(targetSource.value());
  }
}
