package org.xunqi.enums;

import lombok.Getter;

/**
 *  返回的状态枚举
 * @author Jerry
 */
@Getter
public enum PageCodeEnum {

    ADD_SUCCESS(1000,"新增成功"),
    ADD_FAIL(1001,"新增失败"),
        ;

    /** 状态码 */
    private Integer cede;

    /** 提示信息 */
    private String msg;

    public static final String KEY = "pageCode";


    PageCodeEnum(Integer code,String msg) {
        this.cede = code;
        this.msg = msg;
    }




}
