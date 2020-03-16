package org.xunqi.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.xunqi.bean.BaseBean;

import java.util.Date;

/**
 *  订单实体类
 * @author Jerry
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Orders extends BaseBean {

    private Long id;
    private Long memberId;
    private Long businessId;
    private Integer num;
    private Integer commentState;
    private Double price;
    private Business business;
    private Member member;
    private Date createTime;

}
