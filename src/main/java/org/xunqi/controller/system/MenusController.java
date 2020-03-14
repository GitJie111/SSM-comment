package org.xunqi.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xunqi.dto.MenuDto;
import org.xunqi.dto.MenuForMoveDto;
import org.xunqi.dto.MenuForZtreeDto;
import org.xunqi.dto.PageCodeDto;
import org.xunqi.enums.PageCodeEnum;
import org.xunqi.pojo.Menu;
import org.xunqi.service.MenuService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-13 16:06
 **/
@RestController
@RequestMapping(value = "/menus")
public class MenusController {


    @Resource
    private MenuService menuService;

    /**
     * 获取菜单列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<MenuForZtreeDto> getList() {
        return menuService.getZtreeList();
    }


    /**
     * 新增菜单
     */
    @RequestMapping(method = RequestMethod.POST)
    public PageCodeDto add(MenuDto menuDto) {
        PageCodeDto result;

        if (menuService.add(menuDto)) {
            result =  new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.ADD_FAIL);
        }
        return result;
    }


    /**
     * 菜单排序
     */
    @RequestMapping(value="/{dropNodeId}/{targetNodeId}/{moveType}",method = RequestMethod.PUT)
    public PageCodeDto order(MenuForMoveDto menuForMoveDto) {
        PageCodeDto result;

        if (menuService.sort(menuForMoveDto)) {
            result = new PageCodeDto(PageCodeEnum.ASSIGN_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.ASSIGN_FAIL);
        }
        return result;
    }


    /**
     * 根据主键id获取菜单信息
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public MenuDto getById(@PathVariable("id") Long id) {
        MenuDto menuDto = menuService.getById(id);
        return menuDto;
    }


    /**
     * 修改菜单
     */
    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public PageCodeDto update(MenuDto menuDto) {
        PageCodeDto result;

        if (menuService.modify(menuDto)) {
            result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
        }
        return result;
    }


    /**
     * 删除菜单
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public PageCodeDto delete(@PathVariable("id") Long id) {
        PageCodeDto result;
        MenuDto menuDto = new MenuDto();
        menuDto.setParentId(id);
        //查询菜单下是否有子菜单
        List<MenuDto> list = menuService.getList(menuDto);

        //判断list是否有值
        if (list != null && list.size() > 0) {
            result = new PageCodeDto(PageCodeEnum.SUB_MENU_EXISTS);
        } else {
            if (menuService.remove(id)) {
                result = new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
            } else {
                result = new PageCodeDto(PageCodeEnum.REMOVE_FAIL);
            }
        }
        return result;
    }

}
