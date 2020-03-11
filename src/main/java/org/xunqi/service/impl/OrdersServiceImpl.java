package org.xunqi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.xunqi.constant.CommentStateConst;
import org.xunqi.dto.OrdersDto;
import org.xunqi.mapper.OrdersMapper;
import org.xunqi.pojo.Orders;
import org.xunqi.service.OrdersService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jerry
 */
@Service("orderService")
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    @Value("${businessImage.url}")
    private String businessImageUrl;

    @Override
    public boolean add(OrdersDto ordersDto) {
        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersDto,orders);
        orders.setCommentState(CommentStateConst.NOT_COMMENT);
        int result = ordersMapper.insert(orders);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public OrdersDto selectById(Long id) {
        OrdersDto ordersDto = new OrdersDto();
        Orders orders = ordersMapper.selectById(id);
        BeanUtils.copyProperties(orders,ordersDto);
        return ordersDto;
    }

    @Override
    public List<OrdersDto> getListByMemberId(Long memberId) {
        List<OrdersDto> ordersDtoList = new ArrayList<>();
        Orders orders = new Orders();
        orders.setMemberId(memberId);
        List<Orders> ordersList = ordersMapper.select(orders);
        for (Orders orders1 : ordersList) {
            OrdersDto ordersDto = new OrdersDto();
            ordersDtoList.add(ordersDto);
            BeanUtils.copyProperties(orders1,ordersDto);
            ordersDto.setImg(businessImageUrl + orders1.getBusiness().getImgFileName());
            ordersDto.setTitle(orders1.getBusiness().getTitle());
            ordersDto.setCount(orders1.getBusiness().getNumber());
        }
        return ordersDtoList;
    }
}
