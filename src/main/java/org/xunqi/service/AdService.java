package org.xunqi.service;

import org.xunqi.dto.AdDto;

import java.util.List;

/**
 * @author Jerry
 */
public interface AdService {


    /** 新增广告
     * @param adDto adDto
     * @return 成功返回true，失败返回false
     */
    boolean add(AdDto adDto);

    /**
     *  分页搜索广告列表
     * @param adDto
     * @return
     */
    List<AdDto> searchByPage(AdDto adDto);


    /**
     *  修改广告信息
     * @param adDto
     * @return  是否修改成功：true-成功;fale-失败
     */
    boolean update(AdDto adDto);

    /**
     *  根据主键id查询信息
     * @param id
     * @return
     */
    AdDto selectById(Long id);

    /**
     *  删除广告信息
     * @param id
     * @return
     */
    boolean delete(Long id);
}
