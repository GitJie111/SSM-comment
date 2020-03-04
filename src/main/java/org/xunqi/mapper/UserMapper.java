package org.xunqi.mapper;

import org.apache.ibatis.annotations.Param;
import org.xunqi.pojo.User;

/**
 * @author Jerry
 */
public interface UserMapper {

    /**
     *  用户登录
     * @param name
     * @return
     */
    User login(@Param("name") String name);

}
