package org.xunqi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 菜单排序
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-13 10:34
 **/
@Getter
@Setter
public class MenuForMoveDto {

    /**
     * 被拖拽的节点的ID
     */
    private Long dropNodeId;

    /**
     * 移动到目标节点的ID
     */
    private Long targetNodeId;

    /**
     * 移动方式
     */
    private String moveType;

    /**
     * 移动方式 - 成为目标节点的子节点
     */
    public static final String MOVE_TYPE_INNER = "inner";
    /**
     * 移动方式 - 移动到目标节点的前一个节点
     */
    public static final String MOVE_TYPE_PREV = "prev";
    /**
     * 移动方式 - 移动到目标节点的后一个节点
     */
    public static final String MOVE_TYPE_NEXT = "next";
}
