package org.xunqi.mapper;

import org.xunqi.pojo.Orders;

import java.util.List;

/**
 * @author Jerry
 */
public interface OrdersMapper {

    /**
     *  新增
     * @param orders
     * @return
     */
    int insert(Orders orders);


    /**
     *  根据主键查询订单表对象
     * @param id
     * @return
     */
    Orders selectById(Long id);


    /**
     *  修改
     * @param orders
     * @return
     */
    int update(Orders orders);


    /**
     *  根据条件查询订单
     * @param orders
     * @return
     */
    List<Orders> select(Orders orders);

}
