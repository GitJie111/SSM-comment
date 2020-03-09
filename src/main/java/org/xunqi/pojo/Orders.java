package org.xunqi.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 *  订单实体类
 * @author Jerry
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Orders {

    private Long id;
    private Long memberId;
    private Long businessId;
    private Integer num;
    private Integer commentState;
    private Double price;
    private Business business;
    private Member member;

}
