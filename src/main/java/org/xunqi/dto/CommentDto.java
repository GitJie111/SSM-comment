package org.xunqi.dto;

import lombok.Getter;
import lombok.Setter;
import org.xunqi.pojo.Comment;

/**
 * @author Jerry
 */
@Getter
@Setter
public class CommentDto extends Comment {

    /**
     *  隐藏中间4位手机号
     */
    private String phone;


}
