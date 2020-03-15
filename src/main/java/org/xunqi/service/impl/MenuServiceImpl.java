package org.xunqi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public List<MenuForZtreeDto> getZtreeList() {

        List<MenuForZtreeDto> result = new ArrayList<>();
        List<Menu> menuList = menuMapper.selectWithAction(new Menu());

        for(Menu menu : menuList) {
            MenuForZtreeDto menuForZtreeDto = new MenuForZtreeDto();
            result.add(menuForZtreeDto);
            BeanUtils.copyProperties(menu, menuForZtreeDto);
            menuForZtreeDto.setOpen(true);
            menuForZtreeDto.setComboId(MenuForZtreeDto.PREFIX_MENU + menu.getId());
            menuForZtreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU + menu.getParentId());

            // 组装菜单下对应的动作
            for(Action action : menu.getActionList()) {
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

        //如果移动到目标节点，成为目标节点的子节点
        if (MenuForMoveDto.MOVE_TYPE_INNER.equals(menuForMoveDto.getMoveType())) {
            //先将目标节点下所有子节点排序数字+1（让出最前面的位置）
            menuMapper.updateOrderNumByParentId(menuForMoveDto.getTargetNodeId());
            //将移动的节点排序数字为1，成为目标下节点下最前面的子节点
            Menu menu = new Menu();
            menu.setOrderNum(1);
            menu.setId(menuForMoveDto.getDropNodeId());
            menu.setParentId(menuForMoveDto.getTargetNodeId());
            menuMapper.update(menu);
        } else {
            //获取目标节点排序数字
            Menu menu = menuMapper.selectById(menuForMoveDto.getTargetNodeId());
            if (menu != null) {
                //如果移动到目标节点的前一个节点
                if (MenuForMoveDto.MOVE_TYPE_PREV.equals(menuForMoveDto.getMoveType())) {
                    // 将目标节点和目标节点后面的兄弟节点的排序数字加1
                    //（留出一个空位，也就是原本目标节点的位置）
                    menuMapper.updateOrderNumByIdInclude(menuForMoveDto.getTargetNodeId());

                    //移动到的节点的排序数字更新为目标节点的排序数字
                    Menu menuForUpdate = new Menu();
                    menuForUpdate.setOrderNum(menu.getOrderNum());
                    menuForUpdate.setId(menuForMoveDto.getDropNodeId());
                    menuForUpdate.setParentId(menu.getParentId());
                    menuMapper.update(menuForUpdate);

                } else if (MenuForMoveDto.MOVE_TYPE_NEXT.equals(menuForMoveDto.getMoveType())){
                    //如果移动到目标节点的后一个节点
                    //将目标节点后面的兄弟节点的排序数字加1（留出一个空位，也就是原本目标节点后面一个节点的位置）
                    menuMapper.updateOrderNumByParentId(menuForMoveDto.getTargetNodeId());
                    //将移动的节点的排序数字更新为目标节点的原排序数字加1（排到目标节点的后一个节点位置）
                    Menu menuForUpdate = new Menu();
                    menuForUpdate.setOrderNum(menu.getOrderNum() + 1);
                    menuForUpdate.setId(menuForMoveDto.getDropNodeId());
                    menuForUpdate.setParentId(menu.getParentId());
                    menuMapper.update(menuForUpdate);
                } else {
                    //移动方式不可识别
                    return false;
                }
            } else {
                //目标节点已不存在
                return false;
            }
        }
        return true;
    }
}
