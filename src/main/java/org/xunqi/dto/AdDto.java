package org.xunqi.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.xunqi.pojo.Ad;

/**
 * @author Jerry
 *  数据传输对象
 */
@Data
public class AdDto extends Ad {

    /** 图片 */
    private String img;

    /** 图片文件 */
    private MultipartFile imgFile;
}
