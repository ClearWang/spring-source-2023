package com.will.transaction;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.will.comm.Common;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * DataSource相关配置
 *
 * @PropertySource类似于
 *  <bean id="propertyConfigurerForProject" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
 *         <property name="order" value="1" />
 *         <property name="ignoreUnresolvablePlaceholders" value="true" />
 *         <property name="location">
 *             <value>classpath:config/core/core.properties</value>
 *         </property>
 *  </bean>
 * @author Will
 * @create 2023:04:12 13:43
 **/
@Component
@PropertySource("classpath:config/core/core.properties")
public class MyDataSource {

  /**
   * 这里需要注意下：只有在spring web环境下 environment才会被提前注册到容器
   * 非spring web环境下 要使用environment 就只能通过comboPooledDataSource(@Autowired Environment environment)
   * 参数方式注入
   */
  @Autowired
  Environment environment;

  /**
   * 单数据源的玩法
   * @return
   */
  //@Bean
  public DataSource comboPooledDataSource() {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    try {
      comboPooledDataSource.setDriverClass(environment.getProperty("jdbc.driverClassName"));
      comboPooledDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
      comboPooledDataSource.setUser(environment.getProperty("jdbc.username"));
      comboPooledDataSource.setPassword(environment.getProperty("jdbc.password"));
      comboPooledDataSource.setMinPoolSize(10);
      comboPooledDataSource.setMaxPoolSize(100);
      comboPooledDataSource.setMaxIdleTime(1800);
      comboPooledDataSource.setAcquireIncrement(3);
      comboPooledDataSource.setMaxStatements(1000);
      comboPooledDataSource.setInitialPoolSize(10);
      comboPooledDataSource.setIdleConnectionTestPeriod(60);
      comboPooledDataSource.setAcquireRetryAttempts(30);
      comboPooledDataSource.setBreakAfterAcquireFailure(false);
      comboPooledDataSource.setTestConnectionOnCheckout(false);
      comboPooledDataSource.setAcquireRetryDelay(100);
    } catch (PropertyVetoException e) {
      e.printStackTrace();
    }

    return comboPooledDataSource;
  }

  /**
   * 动态多数据源的玩法
   * @return
   */
  @Bean
  public DataSource dynamicDataSource() {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    try {
      comboPooledDataSource.setDriverClass(environment.getProperty("jdbc.driverClassName"));
      comboPooledDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
      comboPooledDataSource.setUser(environment.getProperty("jdbc.username"));
      comboPooledDataSource.setPassword(environment.getProperty("jdbc.password"));
      comboPooledDataSource.setMinPoolSize(10);
      comboPooledDataSource.setMaxPoolSize(100);
      comboPooledDataSource.setMaxIdleTime(1800);
      comboPooledDataSource.setAcquireIncrement(3);
      comboPooledDataSource.setMaxStatements(1000);
      comboPooledDataSource.setInitialPoolSize(10);
      comboPooledDataSource.setIdleConnectionTestPeriod(60);
      comboPooledDataSource.setAcquireRetryAttempts(30);
      comboPooledDataSource.setBreakAfterAcquireFailure(false);
      comboPooledDataSource.setTestConnectionOnCheckout(false);
      comboPooledDataSource.setAcquireRetryDelay(100);
    } catch (PropertyVetoException e) {
      e.printStackTrace();
    }

    Map<Object, Object> targetDataSource = new HashMap<>();
    /**
     * 多数据源的玩法 其实在实践中用的很少 因为这种方式不灵活 和代码耦合度太高
     * 一般会使用mycat[根据app传入参数-自定义路由规则]+haproxy[基于tcp+http的负载均衡中间件]
     * mycat配置haproxy的ip
     */
    targetDataSource.put(Common.defaultDataSourceName,comboPooledDataSource);
    MyDynamicDataSource myDynamicDataSource = new MyDynamicDataSource();
    myDynamicDataSource.setTargetDataSources(targetDataSource);
    myDynamicDataSource.setDefaultTargetDataSource(comboPooledDataSource);

    return myDynamicDataSource;
  }

}
