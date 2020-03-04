package org.xunqi.enums;

import lombok.Getter;

/**
 *  返回的状态枚举
 * @author Jerry
 */
@Getter
public enum PageCodeEnum {

    ADD_SUCCESS(1000,"新增成功!"),
    ADD_FAIL(1001,"新增失败!"),
    MODIFY_SUCCESS(1100,"修改成功!"),
    MODIFY_FAIL(1101,"修改失败!"),
    REMOVE_SUCCESS(1200,"删除成功!"),
    REMOVE_FAIL(1201,"删除失败!"),
    LOGIN_FAIL(1301,"登录失败!用户名密码错误!"),
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
