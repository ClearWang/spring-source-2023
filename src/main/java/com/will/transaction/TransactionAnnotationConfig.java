package com.will.transaction;

import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 事务相关注解配置
 * @EnableTransactionManagement 开启事务需要配置的注解
 * annotationClass: 扫描对应包下有对应注解的类
 * @author Will
 * @create 2023:04:12 9:08
 **/
@Component
@EnableTransactionManagement
@MapperScan(basePackages = {"com.will.dao"},annotationClass = Repository.class)
public class TransactionAnnotationConfig {

  /**
   * sqlSessionFactoryBean 思考下这里为什么要要注入一个SqlSessionFactoryBean
   * @param dataSource
   * @return
   */
  @Bean
  public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    return sqlSessionFactoryBean;
  }

  /**
   * 注册TransactionManger的方式1
   * 方式2:参考MyTransactionManagerConfigurer
   *
   * @param dataSource
   * @return
   */
  @Bean
  public TransactionManager annotationDrivenTransactionManager(DataSource dataSource) {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    //绑定数据源
    dataSourceTransactionManager.setDataSource(dataSource);
    return dataSourceTransactionManager;
  }

}
