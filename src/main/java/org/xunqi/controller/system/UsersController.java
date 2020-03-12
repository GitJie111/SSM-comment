package org.xunqi.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xunqi.dto.PageCodeDto;
import org.xunqi.dto.UserDto;
import org.xunqi.enums.PageCodeEnum;
import org.xunqi.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户相关
 * @author Jerry
 */
@Controller
@RequestMapping(value = "/users")
public class UsersController {

    @Resource
    private UserService userService;

    /**
     * 获取用户列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getList() {
        return userService.getList();
    }


    /**
     * 新增用户列表
     */
    @RequestMapping(method = RequestMethod.POST)
    public PageCodeDto add(UserDto userDto) {
        PageCodeDto result;
        //判读新增是否成功
        if (userService.add(userDto)) {
            result = new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.ADD_FAIL);
        }
        return result;
    }


    /**
     * 根据用户id获取用户信息
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public UserDto getById(@PathVariable("id") Long id) {
        UserDto userDto = userService.getById(id);
        return userDto;
    }


    /**
     * 修改用户
     */
    @RequestMapping(method = RequestMethod.POST)
    public PageCodeDto update(UserDto userDto) {
        PageCodeDto result;
        if (userService.modify(userDto)) {
            result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
        }
        return result;
    }


    /**
     * 删除用户
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public PageCodeDto delete(@PathVariable("id") Long id) {
        PageCodeDto result;
        if (userService.remove(id)) {
            result = new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
        }
        return result;
    }

}
