package org.xunqi.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Jerry
 */
@Getter
@Setter
public class Group {

    private Long id;
    private String name;

    private List<Menu> menuList;

    private List<Action> actionList;



}
