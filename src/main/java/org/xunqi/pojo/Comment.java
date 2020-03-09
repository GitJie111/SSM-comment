package org.xunqi.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.xunqi.bean.BaseBean;

import java.util.Date;

/**
 *  评论实体类
 * @author Jerry
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment extends BaseBean {

    private Long id;

    private String comment;

    private int star;

    private Long orderId;

    private Date createTime;

    private Orders orders;

}
