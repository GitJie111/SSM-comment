package org.xunqi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.xunqi.enums.ApiCodeEnum;

/**
 * @author Jerry
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class ApiCodeDto {

    private Integer errno;

    private String msg;

    private String token;

    private String code;


    public ApiCodeDto(Integer errno,String msg) {
        this.errno = errno;
        this.msg = msg;
    }


    public ApiCodeDto(ApiCodeEnum apiCodeEnum) {
        this.errno = apiCodeEnum.getErrno();
        this.msg = apiCodeEnum.getMsg();
    }

}
