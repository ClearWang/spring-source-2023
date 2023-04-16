package com.will.service.impl;

import com.will.annotation.MyTargetSource;
import com.will.comm.Common;
import com.will.dao.ExpandOrderMapper;
import com.will.dao.OrderMapper;
import com.will.entity.Order;
import com.will.service.OrderService;
import java.util.List;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * test
 *
 * @author Will
 * @create 2023:04:08 16:48
 **/
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  ExpandOrderMapper orderMapper;

  @Override
  public String getOrderNameById(Long orderId) {
    String orderName = "WILL-史密斯深蹲架";

    if (1 == orderId) {
      orderName = "WILL-哈克深蹲架";
    }
    System.out.println(orderId + ":" + orderName);
    return orderName;
  }

  @Override
  @MyTargetSource(value = "ds2")
  @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,readOnly = false,rollbackFor = RuntimeException.class)
  public String getOrderByIdFromDb(Long orderId) {
    logger.info("getOrderByIdFromDb ===>");
    List<Order> orders = orderMapper.getOrderById(orderId);
    logger.info("getOrderByIdFromDb ==> orders="+orders);

    return "Success";
  }
}
