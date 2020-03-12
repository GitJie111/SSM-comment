package org.xunqi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.xunqi.enums.PageCodeEnum;

/**
 * @author Jerry
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageCodeDto {

    private Integer code;

    private String msg;

    public PageCodeDto(PageCodeEnum pageCodeEnum) {
        this.code = pageCodeEnum.getCode();
        this.msg = pageCodeEnum.getMsg();
    }
}
