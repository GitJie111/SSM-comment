package org.xunqi.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xunqi.dto.GroupDto;
import org.xunqi.dto.PageCodeDto;
import org.xunqi.enums.PageCodeEnum;
import org.xunqi.service.GroupService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户组相关
 * @author Jerry
 */
@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    @Resource
    private GroupService groupService;

    /**
     * 获取用户组列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<GroupDto> getList() {
        return groupService.getList();
    }


    /**
     * 新增用户列表
     */
    @RequestMapping(method = RequestMethod.POST)
    public PageCodeDto add(GroupDto groupDto) {
        PageCodeDto result;

        if (groupService.add(groupDto)) {
            result = new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.ADD_FAIL);
        }
        return result;
    }


    /**
     * 根据用户主键id获取用户组信息
     */
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public GroupDto getById(@PathVariable("id") Long id) {
        GroupDto groupDto = groupService.getById(id);
        return groupDto;
    }


    /**
     * 修改用户组
     */
    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public PageCodeDto update(GroupDto groupDto) {
        PageCodeDto result;

        if (groupService.modify(groupDto)) {
            result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
        }
        return result;
    }


    /**
     * 删除用户组
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public PageCodeDto delete(@PathVariable("id") Long id) {
        PageCodeDto result;

        if (groupService.remove(id)) {
            result = new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.REMOVE_FAIL);
        }
        return result;
    }


    /**
     * 获取用户组对应可以访问的菜单和动作
     */
    @RequestMapping(value="/{id}/menus",method = RequestMethod.GET)
    public GroupDto getMenuList(@PathVariable("id") Long id) {
        return groupService.getByIdWithMenuAction(id);
    }


    /**
     * 为用户组分配可以访问的菜单
     */
    @RequestMapping(value = "/{id}/menus",method = RequestMethod.POST)
    public PageCodeDto assignMenu(GroupDto groupDto) {
        PageCodeDto result;

        if (groupService.assignMenu(groupDto)) {
            result = new PageCodeDto(PageCodeEnum.ASSIGN_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.ASSIGN_FAIL);
        }
        return result;
    }


}
