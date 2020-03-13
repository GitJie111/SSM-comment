package org.xunqi.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xunqi.constant.SessionKeyConst;
import org.xunqi.dto.MenuDto;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-13 19:25
 **/
@Controller
@RequestMapping(value = "/session")
public class SessionController {

    @Autowired
    private HttpSession session;


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/menus",method = RequestMethod.GET)
    @ResponseBody
    public List<MenuDto> getUserMenuList(MenuDto menuDto) {
        return (List<MenuDto>) session.getAttribute(SessionKeyConst.MENU_INFO);
    }


    /**
     * 退出系统
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public String signOut() {
        //清除session
        session.invalidate();
        return "redirect:/login";
    }
}
