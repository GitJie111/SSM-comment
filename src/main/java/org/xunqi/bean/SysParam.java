package org.xunqi.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Jerry
 */
@Getter
@Setter
public class SysParam {

    private String paramKey;
    private Date paramValue;
    private String paramDesc;

}
