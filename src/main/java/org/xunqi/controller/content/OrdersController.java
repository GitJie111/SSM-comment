package org.xunqi.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xunqi.dto.OrdersDto;
import org.xunqi.service.OrdersService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jerry
 */
@Controller
@RequestMapping(value = "/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @RequestMapping
    public String search(OrdersDto ordersDto, Model model) {
        List<OrdersDto> ordersDtoList = ordersService.selectByPage(ordersDto);
        model.addAttribute("list",ordersDtoList);
        model.addAttribute("searchParam",ordersDto);
        return "/content/orderList";
    }

}
