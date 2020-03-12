package org.xunqi.mapper;

import org.xunqi.pojo.Action;

/**
 * @author Jerry
 */
public interface ActionMapper {

    /**
     * 根据菜单id删除动作
     * @param menuId
     * @return
     */
    int deleteByMenuId(Long menuId);


    /**
     * 根据主键id删除动作
     * @param id
     * @return
     */
    int deleteById(Long id);


    /**
     * 新增动作
     * @param action
     * @return
     */
    int insert(Action action);


    /**
     * 修改动作
     * @param action
     * @return
     */
    int update(Action action);


    /**
     * 根据主键id获取动作信息
     * @param id
     * @return
     */
    Action selectById(Long id);

}
