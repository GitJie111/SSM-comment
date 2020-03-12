package org.xunqi.mapper;

import org.apache.ibatis.annotations.Param;
import org.xunqi.pojo.User;

import java.util.List;

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


    /**
     *  根据查询条件查询用户列表
     * @param user
     * @return
     */
    List<User> select(User user);


    /**
     *  新增用户
     * @param user
     * @return
     */
    int insert(User user);


    /**
     *  根据用户id查询用户信息
     * @param id
     * @return
     */
    User selectById(Long id);


    /**
     *  修改用户信息
     * @param user
     * @return
     */
    int update(User user);


    /**
     *  根据用户id删除用户信息
     * @param id
     * @return
     */
    int delete(Long id);
}
