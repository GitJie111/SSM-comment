package org.xunqi.pojo;

import lombok.Data;

/** 用户类
 * @author Jerry
 */
@Data
public class User {

    private Long id;
    private String name;
    private String password;
    private String chName;
    private Long groupId;

}
