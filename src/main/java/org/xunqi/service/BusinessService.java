package org.xunqi.service;

import org.xunqi.dto.BusinessDto;
import org.xunqi.dto.BusinessListDto;
import org.xunqi.pojo.Business;

import java.util.List;

/**
 * @author Jerry
 */
public interface BusinessService {

    /**
     *  新增商户信息
     * @param businessDto
     * @return
     */
    boolean add(BusinessDto businessDto);


    /**
     *  根据id注解查询商户
     * @param id
     * @return
     */
    BusinessDto getById(Long id);


    /**
     *  分页搜索商户列表
     * @param businessDto
     * @return
     */
    List<BusinessDto> searchByPage(BusinessDto businessDto);


    /**
     *  分页搜索商户列表(接口专用)
     * @param businessDto   businessDto 查询条件(包含分页对象)
     * @return
     */
    BusinessListDto searchByPageForApi(BusinessDto businessDto);


    /**
     *  根据主键删除用户信息
     * @param id
     * @return
     */
    boolean delete(Long id);


    /**
     *  修改商户信息
     * @param businessDto
     * @return
     */
    boolean update(BusinessDto businessDto);

}
