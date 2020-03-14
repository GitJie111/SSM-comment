package org.xunqi.mapper;

import org.xunqi.pojo.GroupMenu;

import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-14 11:35
 **/
public interface GroupMenuMapper {


    /**
     * 根据用户组主键，删除用户组与菜单之间的关系
     * @param groupId
     * @return
     */
    int deleteByGroupId(Long groupId);


    int insertBatch(List<GroupMenu> list);

}
