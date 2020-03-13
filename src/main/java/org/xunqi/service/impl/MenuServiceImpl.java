package org.xunqi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.xunqi.dto.MenuDto;
import org.xunqi.dto.MenuForMoveDto;
import org.xunqi.dto.MenuForZtreeDto;
import org.xunqi.mapper.ActionMapper;
import org.xunqi.mapper.MenuMapper;
import org.xunqi.pojo.Action;
import org.xunqi.pojo.Menu;
import org.xunqi.service.MenuService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-13 10:36
 **/
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private ActionMapper actionMapper;

    @Override
    public List<MenuForZtreeDto> getZtreeList() {
        List<MenuForZtreeDto> result = new ArrayList<>();

        //查询全部数据
        List<Menu> menuList = menuMapper.selectWithAction(new Menu());

        //循环读取数据
        for (Menu menu : menuList) {
            MenuForZtreeDto menuForZtreeDto = new MenuForZtreeDto();
            result.add(menuForZtreeDto);
            BeanUtils.copyProperties(menu,menuForZtreeDto);
            menuForZtreeDto.setOpen(true);
            menuForZtreeDto.setComboId(MenuForZtreeDto.PREFIX_MENU + menu.getId());
            menuForZtreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU + menu.getParentId());

            //组装菜单下对应的动作
            for (Action action : menu.getActionList()) {
                menuForZtreeDto = new MenuForZtreeDto();
                result.add(menuForZtreeDto);
                menuForZtreeDto.setComboId(MenuForZtreeDto.PREFIX_ACTION + action.getId());
                menuForZtreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU + action.getMenuId());
                menuForZtreeDto.setName(action.getName());
                menuForZtreeDto.setId(action.getId());
                menuForZtreeDto.setParentId(action.getMenuId());
            }
        }

        return result;
    }

    @Override
    public List<MenuDto> getList(MenuDto menuDto) {
        List<MenuDto> menuDtoList = new ArrayList<>();
        Menu menuForSelect = new Menu();
        BeanUtils.copyProperties(menuDto,menuForSelect);
        List<Menu> menuList = menuMapper.select(menuForSelect);
        for (Menu menu : menuList) {
            MenuDto menuDtoTemp = new MenuDto();
            BeanUtils.copyProperties(menu,menuDtoTemp);
            menuDtoList.add(menuDtoTemp);
        }
        return menuDtoList;
    }

    @Override
    public boolean add(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto,menu);
        if (menuMapper.insert(menu) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Long id) {
        if (menuMapper.delete(id) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public MenuDto getById(Long id) {
        MenuDto menuDto = new MenuDto();
        Menu menu = menuMapper.selectById(id);
        BeanUtils.copyProperties(menu,menuDto);
        return menuDto;
    }

    @Override
    public boolean modify(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto,menu);
        menuMapper.update(menu);
        return false;
    }

    @Override
    public boolean sort(MenuForMoveDto menuForMoveDto) {
        return false;
    }
}
