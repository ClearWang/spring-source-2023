package com.will.service;


/**
 * service 测试类
 *
 * @author Will
 * @create 2023:04:08 16:39
 **/
public interface OrderService {

  /**
   * getOrderNameById
   * @param orderId
   * @return
   */
  String getOrderNameById(Long orderId);

  /**
   * getOrderNameByIdFromDb
   * @param orderId
   * @return
   */
  String getOrderByIdFromDb(Long orderId);
}
