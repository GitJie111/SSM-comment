package org.xunqi.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xunqi.constant.DicTypeConst;
import org.xunqi.pojo.Dic;
import org.xunqi.service.DicService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-13 16:28
 **/
@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @Resource
    private DicService dicService;

    @RequestMapping
    public String index(Model model) {
        List<Dic> list = dicService.getListByType(DicTypeConst.HTTP_METHOD);
        model.addAttribute("httpMethodList",list);
        return "/system/auth";
    }

}
