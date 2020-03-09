package org.xunqi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jerry
 */
@Getter
@Setter
public class CommentForSubmitDto {

    /**
     *  订单表主键
     */
    private Long id;

    /**
     *  提交的评论内容
     */
    private String comment;

    /**
     *  提交的评价星级
     */
    private Integer star;

    /**
     *  登录成功后的token
     */
    private String token;

    /**
     *  会员手机号
     */
    private String phone;

}
