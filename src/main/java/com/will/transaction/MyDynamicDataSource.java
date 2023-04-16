package com.will.transaction;

import com.will.comm.Common;
import com.will.comm.DynamicDataSourceCache;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 自定义动态数据源玩法
 *
 * @author Will
 * @create 2023:04:14 10:53
 **/
public class MyDynamicDataSource extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
    //以threadLocal的方式存储 先于事务切面(order)完成动态获取对应数据源的Key
    String dsKey = DynamicDataSourceCache.getDsFromCache();
    System.out.println("=====dsKey===="+dsKey);
    return dsKey;
  }

  //@Override
  //protected Object determineCurrentLookupKey() {
  //  //这样硬编码的方式 不太好 可以通过注解切面的方式 以threadLocal的方式存储 先于事务切面(order)完成动态获取
  //  return Common.defaultDataSourceName;
  //}
}
