package org.xunqi.service;

import org.xunqi.dto.UserDto;
import org.xunqi.pojo.User;

/**
 * @author Jerry
 */
public interface UserService {

    /**
     * 校验用户名/密码是否正确
     * @return true：用户名/密码正确，如果正确
     *                false：用户名/密码错误
     */
    User validate(String name, String password);

}
