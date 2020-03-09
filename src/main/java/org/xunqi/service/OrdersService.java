package org.xunqi.service;

import org.xunqi.dto.OrdersDto;

import java.util.List;

/**
 * @author Jerry
 */
public interface OrdersService {

    /**
     *  新增订单
     * @param ordersDto
     * @return
     */
    boolean add(OrdersDto ordersDto);


    /**
     *  根据主键id查询订单信息
     * @param id
     * @return
     */
    OrdersDto selectById(Long id);


    /**
     *  根据会员id获取该会员下的全部订单信息
     * @param memberId
     * @return
     */
    List<OrdersDto> getListByMemberId(Long memberId);

}
