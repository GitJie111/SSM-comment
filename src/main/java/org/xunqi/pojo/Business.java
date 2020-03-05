package org.xunqi.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.xunqi.bean.BaseBean;

/**
 *  商户类
 * @author Jerry
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Business extends BaseBean {

    private Long id;
    private String imgFileName;
    private String title;
    private String subtitle;
    private Double price;
    private Integer distance;
    private Integer number;
    private String desc;
    private String city;
    private String category;
    private Long starTotalNum;
    private Long commentTotalNum;

    private Dic cityDic;
    private Dic categoryDic;

}
