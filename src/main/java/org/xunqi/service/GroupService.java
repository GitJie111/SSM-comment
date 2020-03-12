package org.xunqi.service;

import org.xunqi.dto.GroupDto;
import org.xunqi.pojo.Group;

import java.util.List;

/**
 * @author Jerry
 */
public interface GroupService {

    /**
     * 获取用户组列表
     * @return
     */
    List<GroupDto> getList();


    /**
     * 修改用户组
     * @param groupDto
     * @return
     */
    boolean modify(GroupDto groupDto);


    /**
     * 新增用户组
     * @param groupDto
     * @return
     */
    boolean add(GroupDto groupDto);


    /**
     * 删除用户组
     * @param id
     * @return
     */
    boolean remove(Long id);


    /**
     * 根据主键id获取用户组列表信息
     * @param id
     * @return
     */
    GroupDto getById(Long id);


    /**
     * 根据主键获取用户组(包括用户组对应可以访问的菜单和动作)
     * @param id
     * @return
     */
    GroupDto getByIdWithMenuAction(Long id);


    /**
     * 为用户组分配可以访问的菜单
     * @param groupDto
     * @return
     */
    boolean assignMenu(GroupDto groupDto);

}
