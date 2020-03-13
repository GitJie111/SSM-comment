package org.xunqi.service;

import org.xunqi.dto.MenuDto;
import org.xunqi.dto.MenuForMoveDto;
import org.xunqi.dto.MenuForZtreeDto;

import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-13 10:21
 **/
public interface MenuService {

    /**
     * 获取菜单树列表
     * @return
     */
    List<MenuForZtreeDto> getZtreeList();


    /**
     * 根据条件获取菜单列表
     * @param menuDto
     * @return
     */
    List<MenuDto> getList(MenuDto menuDto);


    /**
     * 新增菜单
     * @param menuDto
     * @return
     */
    boolean add(MenuDto menuDto);


    /**
     * 根据id删除菜单信息
     * @param id
     * @return
     */
    boolean remove(Long id);


    /**
     * 通过id获取菜单信息
     * @param id
     * @return
     */
    MenuDto getById(Long id);


    /**
     * 修改菜单
     * @param menuDto
     * @return
     */
    boolean modify(MenuDto menuDto);


    /**
     * 菜单排序
     * @param menuForMoveDto
     * @return
     */
    boolean sort(MenuForMoveDto menuForMoveDto);

}
