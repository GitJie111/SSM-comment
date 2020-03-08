package org.xunqi.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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


    @RequestMapping("/remove")
    public String remove(@RequestParam("id") Long id,Model model) {
        if(adService.delete(id)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_FAIL);
        }
        return "forward:/ad";
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
        return "forward:/ad";
    }


    /**
     *  修改页面初始化
     * @return
     */
    @RequestMapping("/modifyInit")
    public String modifyInit(Model model,@RequestParam("id")Long id) {
        AdDto adDto = adService.selectById(id);
        model.addAttribute("modifyObj",adDto);
        return "/content/adModify";
    }


    /**
     *  修改信息
     * @param model
     * @param adDto
     * @return
     */
    @RequestMapping(value = "/modify")
    public String modify(Model model,AdDto adDto) {
        model.addAttribute("modifyObj",adDto);

        if (adService.update(adDto)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
        }
        return "forward:/ad";
    }
}
