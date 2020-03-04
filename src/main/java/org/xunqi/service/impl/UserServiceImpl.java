package org.xunqi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.xunqi.mapper.UserMapper;
import org.xunqi.pojo.User;
import org.xunqi.service.UserService;
import org.xunqi.util.CommonUtil;
import org.xunqi.util.MD5Util;

import javax.annotation.Resource;

/**
 * @author Jerry
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User validate(String name, String password) {

        if (!CommonUtil.isEmpty(name) && !CommonUtil.isEmpty(password)) {
            User user = new User();
            user = userMapper.login(name);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
