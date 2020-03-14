package org.xunqi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xunqi.dto.ActionDto;
import org.xunqi.dto.GroupDto;
import org.xunqi.dto.MenuDto;
import org.xunqi.mapper.GroupActionMapper;
import org.xunqi.mapper.GroupMapper;
import org.xunqi.mapper.GroupMenuMapper;
import org.xunqi.pojo.*;
import org.xunqi.service.GroupService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jerry
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupMapper groupMapper;

    @Resource
    private GroupActionMapper groupActionMapper;

    @Resource
    private GroupMenuMapper groupMenuMapper;

    @Override
    public List<GroupDto> getList() {
        List<GroupDto> result = new ArrayList<>();
        List<Group> groupList = groupMapper.select(new Group());
        for (Group group : groupList) {
            GroupDto groupDto = new GroupDto();
            result.add(groupDto);
            BeanUtils.copyProperties(group, groupDto);
            groupDto.setpId(0);
        }
        return result;
    }

    @Override
    public boolean modify(GroupDto groupDto) {
        Group group = new Group();
        BeanUtils.copyProperties(groupDto,group);
        if (groupMapper.update(group) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean add(GroupDto groupDto) {
        Group group = new Group();
        BeanUtils.copyProperties(groupDto,group);
        if (groupMapper.insert(group) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Long id) {
        if (groupMapper.delete(id) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public GroupDto getById(Long id) {
        GroupDto groupDto = new GroupDto();
        Group group = groupMapper.selectById(id);
        BeanUtils.copyProperties(group,groupDto);
        return groupDto;
    }

    @Override
    public GroupDto getByIdWithMenuAction(Long id) {

        GroupDto result = new GroupDto();
        List<MenuDto> menuDtoList = new ArrayList<>();
        List<ActionDto> actionDtoList = new ArrayList<>();
        result.setMenuDtoList(menuDtoList);
        result.setActionDtoList(actionDtoList);

        Group group = groupMapper.selectMenuListById(id);

        //判断查询出来的数据是否为空
        if (group != null) {
            BeanUtils.copyProperties(group,result);

            for (Menu menu : group.getMenuList()) {
                MenuDto menuDto = new MenuDto();
                menuDtoList.add(menuDto);
                BeanUtils.copyProperties(menu,menuDto);
            }

            for (Action action : group.getActionList()) {
                ActionDto actionDto = new ActionDto();
                actionDtoList.add(actionDto);
                BeanUtils.copyProperties(action,actionDto);
            }
        }

        return result;
    }

    @Override
    @Transactional
    public boolean assignMenu(GroupDto groupDto) {

        //先删除用户组与菜单之间的关系
        groupMenuMapper.deleteByGroupId(groupDto.getId());
        //删除用户组与动作之间的关系
        groupActionMapper.deleteByGroupId(groupDto.getId());

        //保存为用户组分配的菜单
        if (groupDto.getMenuIdList() != null && groupDto.getMenuIdList().size() > 0) {
            List<GroupMenu> groupMenuList = new ArrayList<>();

            for (Long menuId : groupDto.getMenuIdList()) {
                if (menuId != null) {
                    GroupMenu groupMenu = new GroupMenu();
                    groupMenu.setGroupId(groupDto.getId());
                    groupMenu.setMenuId(menuId);
                    groupMenuList.add(groupMenu);
                }
            }
            //批量新增
            groupMenuMapper.insertBatch(groupMenuList);
        }


        //保存为用户组分配的动作
        if (groupDto.getActionIdList() != null && groupDto.getActionIdList().size() > 0) {
            List<GroupAction> groupActionList = new ArrayList<>();

            for (Long actionId : groupDto.getActionIdList()) {
                if (actionId != null) {
                    GroupAction groupAction = new GroupAction();
                    groupAction.setActionId(actionId);
                    groupAction.setGroupId(groupDto.getId());
                    groupActionList.add(groupAction);
                }
            }
            //批量新增
            groupActionMapper.insertBatch(groupActionList);
        }

        return true;
    }
}
