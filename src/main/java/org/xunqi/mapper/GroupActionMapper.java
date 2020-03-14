package org.xunqi.mapper;

import org.xunqi.pojo.GroupAction;

import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-14 11:34
 **/
public interface GroupActionMapper {


    /**
     * 根据用户组主键，删除用户组与动作之间的联系
     * @param groupId
     * @return
     */
    int deleteByGroupId(Long groupId);


    /**
     * 批量新增
     * @param list
     * @return
     */
    int insertBatch(List<GroupAction> list);

}
