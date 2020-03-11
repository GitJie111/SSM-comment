package org.xunqi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.xunqi.pojo.Business;

/**
 * @author Jerry
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessDto extends Business{

    private String img;
    private MultipartFile imgFile;
    private String keyword;
    private Integer mumber;
    private Integer star;

}
