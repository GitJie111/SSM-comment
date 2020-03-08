package org.xunqi.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xunqi.constant.DicTypeConst;
import org.xunqi.dto.BusinessDto;
import org.xunqi.enums.PageCodeEnum;
import org.xunqi.pojo.Dic;
import org.xunqi.service.BusinessService;
import org.xunqi.service.DicService;

import javax.annotation.Resource;
import java.util.List;

/**
 *  商户controller
 * @author Jerry
 */
@Controller
@RequestMapping(value = "/businesses")
public class BusinessController {

    @Resource
    private BusinessService businessService;

    @Resource
    private DicService dicService;

    /**
     *  商户列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String search(BusinessDto dto, Model model){
        List<BusinessDto> list = businessService.searchByPage(dto);
        model.addAttribute("list",list);
        model.addAttribute("searchParam",dto);
        return "/content/businessList";
    }


    /**
     *  删除用户
     */
    @RequestMapping(value = "/{id}")
    public String delete(@PathVariable("id") Long id,Model model) {
        if (businessService.delete(id)) {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_FAIL);
        }
        return "redirect:/businesses";
    }


    /**
     *  商户新增页面初始化
     */
    @RequestMapping(value = "/addPage",method = RequestMethod.GET)
    public String addInit(Model model) {
        List<Dic> cityList = dicService.getListByType(DicTypeConst.CITY);
        List<Dic> categoryList = dicService.getListByType(DicTypeConst.CATEGORY);
        model.addAttribute("cityList",cityList);
        model.addAttribute("categoryList",categoryList);
        return "/content/businessAdd";
    }

    /**
     *  商户新增
     */
    @RequestMapping(method = RequestMethod.POST)
    public String add(BusinessDto dto,RedirectAttributes attr) {
        if(businessService.add(dto)) {
            attr.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
            return "redirect:/businesses";
        } else {
            attr.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
            return "redirect:/businesses/addPage";
        }
    }


    /**
     *  商户修改页面初始化
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String modifyInit(@PathVariable("id") Long id,Model model) {
        BusinessDto modifyObj = businessService.getById(id);
        List<Dic> cityList = dicService.getListByType(DicTypeConst.CITY);
        List<Dic> categoryList = dicService.getListByType(DicTypeConst.CATEGORY);
        model.addAttribute("cityList",cityList);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("modifyObj",modifyObj);
        return "/content/businessModify";
    }

    /**
     *  商户修改
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String modify(@PathVariable("id") Long id,BusinessDto businessDto,Model model) {
        model.addAttribute("modifyObj",businessDto);

        if (businessService.update(businessDto)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_FAIL);
        }
        return "/content/businessModify";
    }

}
