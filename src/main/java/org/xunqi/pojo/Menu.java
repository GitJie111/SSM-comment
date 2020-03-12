package org.xunqi.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jerry
 */
@Getter
@Setter
public class Menu {

    private Long id;
    private String name;
    private String url;
    private Long parentId;
    private Integer orderNum;

}
