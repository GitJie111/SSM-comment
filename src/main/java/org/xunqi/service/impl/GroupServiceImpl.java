package org.xunqi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.xunqi.dto.GroupDto;
import org.xunqi.mapper.GroupMapper;
import org.xunqi.pojo.Group;
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

    @Override
    public List<GroupDto> getList() {
        List<GroupDto> result = new ArrayList<>();

        //查询全部信息
        List<Group> groupList = groupMapper.select(new Group());

        for (Group group : groupList) {
            GroupDto groupDto = new GroupDto();
            BeanUtils.copyProperties(group,groupDto);
            groupDto.setPId(0);
            result.add(groupDto);
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
        return null;
    }

    @Override
    public boolean assignMenu(GroupDto groupDto) {
        return false;
    }
}
