package org.xunqi.dto;

import lombok.Data;
import org.xunqi.pojo.Orders;

/**
 * @author Jerry
 */
@Data
public class OrdersDto extends Orders {

    private String img;

    private String title;

    private Integer count;

}
