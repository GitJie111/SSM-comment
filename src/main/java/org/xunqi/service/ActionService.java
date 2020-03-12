package org.xunqi.service;

import org.xunqi.dto.ActionDto;
/**
 * @author Jerry
 */
public interface ActionService {


    /**
     * 新增动作
     * @param actionDto
     * @return
     */
    boolean add(ActionDto actionDto);


    /**
     * 根据主键id删除动作
     * @param id
     * @return
     */
    boolean remove(Long id);


    /**
     * 修改动作
     * @param actionDto
     * @return
     */
    boolean update(ActionDto actionDto);


    /**
     * 根据主键id查询动作信息
     * @param id
     * @return
     */
    ActionDto selectById(Long id);

}
