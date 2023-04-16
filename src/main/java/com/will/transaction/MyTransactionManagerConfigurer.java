package com.will.transaction;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * TransactionManager注册
 * 注册TransactionManger的方式2
 * 方式1:参考TransactionAnnotationConfig
 *
 * @author Will
 * @create 2023:04:12 11:15
 **/
//@Component
public class MyTransactionManagerConfigurer implements TransactionManagementConfigurer {

  @Autowired
  private DataSource dataSource;

  @Override
  public TransactionManager annotationDrivenTransactionManager() {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    //绑定数据源
    dataSourceTransactionManager.setDataSource(dataSource);
    return dataSourceTransactionManager;
  }
}
