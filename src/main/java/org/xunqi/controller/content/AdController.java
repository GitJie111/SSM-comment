package org.xunqi.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xunqi.dto.AdDto;
import org.xunqi.service.AdService;

import javax.annotation.Resource;

/**
 * @author Jerry
 */
@Controller
@RequestMapping("/ad")
public class AdController {

    @Resource
    private AdService adService;

    /**
     * 广告管理页初始化(点广告管理菜单进入的第一个页面)
     */
    @RequestMapping
    public String init() {
        return "/content/adList";
    }

    /**
     * 新增页初始化
     */
    @RequestMapping("/addInit")
    public String addInit() {
        return "/content/adAdd";
    }

    /**
     *  新增方法
     * @param adDto
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(AdDto adDto) {
        adService.add(adDto);
        return "/content/adAdd";
    }


}
