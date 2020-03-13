package org.xunqi.mapper;

import org.xunqi.pojo.Menu;

import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-13 09:35
 **/
public interface MenuMapper {

    /**
     * 根据查询条件查询菜单列表(关联动作表，结果集里包含了动作列表)
     * @param menu
     * @return
     */
    List<Menu> selectWithAction(Menu menu);


    /**
     * 根据查询条件查询菜单列表(仅本表单表查询)
     * @param menu
     * @return
     */
    List<Menu> select(Menu menu);


    /**
     * 新增菜单
     * @param menu
     * @return
     */
    int insert(Menu menu);


    /**
     * 根据菜单id获取菜单信息
     * @param id
     * @return
     */
    Menu selectById(Long id);


    /**
     * 修改菜单信息
     * @param menu
     * @return
     */
    int update(Menu menu);


    /**
     * 根据菜单id删除菜单信息
     * @param id
     * @return
     */
    int delete(Long id);


    /**
     * 修改指定菜单下所有子菜单的排序数字，修改为原排序数组+1
     * @param parentId
     * @return
     */
    int updateOrderNumByParentId(Long parentId);


    /**
     * 修改排序在指定菜单后面的兄弟节点(包括指定菜单本身)的排序数字，修改为原排序数字+1
     * @param id
     * @return
     */
    int updateOrderNumByIdInclude(Long id);


    /**
     * 修改排序在指定菜单后面的兄弟节点(不包括指定菜单本身)的排序数字，修改为原排序数字+1
     * @param id
     * @return
     */
    int updateOrderNumById(Long id);
}
