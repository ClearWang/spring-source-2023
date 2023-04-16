package com.will.dao;

import com.will.entity.Order;
import com.will.entity.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 自动生成的OrderMapper的拓展
 *
 * @author Will
 * @create 2023:04:08 16:39
 **/
@Repository
public interface ExpandOrderMapper extends OrderMapper{

  /**
   * getOrderById
   * 这里有个坑：mysql表名称不能是关键字 这里order就是mysql的关键字 所以这里需要加反引号转义
   * 如果不是关键字就不需要 比如起名为order_all
   * @param id
   * @return
   */
  @Select("select * from `order` where id=#{id}")
  List<Order> getOrderById(Long id);
}
