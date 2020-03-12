package org.xunqi.mapper;

import org.xunqi.pojo.Group;

import java.util.List;

/**
 * @author Jerry
 */
public interface GroupMapper {

    /**
     * 多条件查询用户组列表
     * @param group
     * @return
     */
    List<Group> select(Group group);


    /**
     * 新增用户组列表
     * @param group
     * @return
     */
    int insert(Group group);


    /**
     * 根据主键id查询用户组列表信息
     * @param id
     * @return
     */
    Group selectById(Long id);


    /**
     * 修改用户组列表信息
     * @param group
     * @return
     */
    int update(Group group);


    /**
     * 根据主键id删除用户组列表信息
     * @param id
     * @return
     */
    int delete(Long id);


    /**
     * 根据主键id获取用户组列表对应的菜单列表
     * @param id
     * @return
     */
    Group selectMenuListById(Long id);

}
