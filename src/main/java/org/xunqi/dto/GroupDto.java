package org.xunqi.dto;

import lombok.Getter;
import lombok.Setter;
import org.xunqi.pojo.Group;

import java.util.List;

/**
 * @author Jerry
 */
@Getter
@Setter
public class GroupDto extends Group {

    private Integer pId;

    private List<Long> menuIdList;

    private List<Long> actionIdList;

    private List<MenuDto> menuDtoList;

    private List<ActionDto> actionDtoList;

}
