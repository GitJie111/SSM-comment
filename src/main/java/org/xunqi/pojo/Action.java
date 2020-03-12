package org.xunqi.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jerry
 */
@Getter
@Setter
public class Action {
	
	private Long id;
	private String name;
	private String url;
	private Long menuId;
	private String method;

}
