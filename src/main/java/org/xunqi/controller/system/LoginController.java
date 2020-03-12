package org.xunqi.controller.system;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xunqi.constant.SessionKeyConst;
import org.xunqi.dto.UserDto;
import org.xunqi.enums.PageCodeEnum;
import org.xunqi.pojo.User;
import org.xunqi.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 登录相关controller
 * @author Jerry
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    @Autowired
    private HttpSession session;

    /**
     * 登录页面
     */
    @RequestMapping
    public String index() {
        return "/system/login";
    }

    /**
     * session超时
     */
    @RequestMapping(value = "/sessionTimeout")
    public String sessionTimeout(Model model) {
        model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.SESSION_TIMEOUT);
        return "/system/index";
    }


    /**
     * 验证用户名/密码是否正确 验证通过跳转至后台管理首页，验证失败，返回至登录页。
     */
    @RequestMapping("/validate")
    public String validate(UserDto userDto, RedirectAttributes attr) {
        if (userService.login(userDto)) {
            session.setAttribute(SessionKeyConst.USER_INFO,userDto);
            return "redirect:/index";
        }
        attr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.LOGIN_FAIL);
        return "redirect:/login";
    }

}
