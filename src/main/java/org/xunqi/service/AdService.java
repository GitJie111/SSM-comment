package org.xunqi.service;

import org.xunqi.dto.AdDto;

/**
 * @author Jerry
 */
public interface AdService {


    /** 新增广告
     * @param adDto adDto
     * @return 成功返回true，失败返回false
     */
    boolean add(AdDto adDto);

}
