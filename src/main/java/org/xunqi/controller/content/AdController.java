package org.xunqi.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xunqi.dto.AdDto;
import org.xunqi.enums.PageCodeEnum;
import org.xunqi.service.AdService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String init(Model model, HttpServletRequest request) {
        AdDto adDto = new AdDto();
        List<AdDto> list = adService.searchByPage(adDto);
        model.addAttribute("list", list);
        model.addAttribute("searchParam", adDto);
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
     * 查询
     */
    @RequestMapping("/search")
    public String search(Model model, AdDto adDto) {
        model.addAttribute("list", adService.searchByPage(adDto));
        model.addAttribute("searchParam", adDto);
        return "/content/adList";
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public String add(AdDto adDto, Model model) {
        if (adService.add(adDto)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
        }
        return "/content/adAdd";
    }


}
