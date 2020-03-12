package org.xunqi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.xunqi.dto.UserDto;
import org.xunqi.mapper.UserMapper;
import org.xunqi.pojo.User;
import org.xunqi.service.UserService;
import org.xunqi.util.CommonUtil;
import org.xunqi.util.MD5Util;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean login(UserDto userDto) {
        //判断传来的userDto是否为空，用户名密码是否为空
        if (userDto != null && !CommonUtil.isEmpty(userDto.getName())
                && !CommonUtil.isEmpty(userDto.getPassword())) {
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            //查询用户
            List<User> list = userMapper.select(user);

            if (list.size() > 0) {
                BeanUtils.copyProperties(list.get(0),userDto);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public List<UserDto> getList() {
        List<UserDto> list = new ArrayList<>();
        //查询全部数据
        List<User> userList = userMapper.select(new User());
        for (User user : userList) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user,userDto);
            userDto.setPId(0);
            list.add(userDto);
        }
        return list;
    }

    @Override
    public boolean modify(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        if (!CommonUtil.isEmpty(userDto.getPassword())) {
            user.setPassword(MD5Util.getMD5(userDto.getPassword()));
        }
        if (userMapper.update(user) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean add(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setPassword(MD5Util.getMD5(userDto.getPassword()));
        if (userMapper.insert(user) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Long id) {
        if (userMapper.delete(id) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public UserDto getById(Long id) {
        User user = userMapper.selectById(id);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }
}
