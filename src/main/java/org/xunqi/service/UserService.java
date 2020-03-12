package org.xunqi.service;

import org.xunqi.dto.UserDto;
import org.xunqi.pojo.User;

import java.util.List;

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


    /**
     * 校验用户名/密码是否正确
     * @param userDto
     * @return
     */
    boolean login(UserDto userDto);


    /**
     * 查询所有用户信息
     * @return
     */
    List<UserDto> getList();


    /**
     * 修改用户信息
     * @param userDto
     * @return
     */
    boolean modify(UserDto userDto);


    /**
     * 新增用户信息
     * @param userDto
     * @return
     */
    boolean add(UserDto userDto);


    /**
     * 删除用户信息
     * @param id
     * @return
     */
    boolean remove(Long id);


    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    UserDto getById(Long id);

}
