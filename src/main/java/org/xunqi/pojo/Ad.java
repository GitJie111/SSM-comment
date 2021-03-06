package org.xunqi.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.xunqi.bean.BaseBean;

/**
 * @author Jerry
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Ad extends BaseBean {


    /** id */
    private Long id;

    /** 标题 */
    private String title;

    /** 图片名称 */
    private String imgFileName;

    /** 图片链接 */
    private String link;

    /** 权重 */
    private Long weight;



}
