package org.xunqi.controller.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xunqi.dto.AdDto;
import org.xunqi.dto.BusinessDto;
import org.xunqi.dto.BusinessListDto;
import org.xunqi.service.AdService;
import org.xunqi.service.BusinessService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jerry
 */
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Resource
    private AdService adService;

    @Resource
    private BusinessService businessService;

    @Value("${ad.number}")
    private int adNumber;

    @Value("${businessHome.number}")
    private int businessHomeNumber;

    @Value("${businessSearch.number}")
    private int businessSearchNumber;


    /**
     * 首页 —— 广告（超值特惠）
     */
    @RequestMapping(value = "/homead",method = RequestMethod.GET)
    public List<AdDto> homead() {

        AdDto adDto = new AdDto();
        adDto.getPage().setPageNumber(adNumber);
        return adService.searchByPage(adDto);
    }


    /**
     * 首页 —— 推荐列表（猜你喜欢）
     */
    @RequestMapping(value = "/homelist/{city}/{page.currentPage}", method = RequestMethod.GET)
    public BusinessListDto homeList(BusinessDto businessDto) {
        businessDto.getPage().setPageNumber(businessHomeNumber);
        return businessService.searchByPageForApi(businessDto);
    }


    /**
     * 搜索结果页 - 搜索结果 - 三个参数
     */
    @RequestMapping(value = "/search/{page.currentPage}/{city}/{category}/{keyword}", method = RequestMethod.GET)
    public BusinessListDto searchByKeyword(BusinessDto businessDto) {
        businessDto.getPage().setPageNumber(businessSearchNumber);
        return businessService.searchByPageForApi(businessDto);
    }


    /**
     * 搜索结果页 - 搜索结果 - 两个参数
     */
    @RequestMapping(value = "/search/{page.currentPage}/{city}/{category}", method = RequestMethod.GET)
    public BusinessListDto search(BusinessDto businessDto) {
        businessDto.getPage().setPageNumber(businessSearchNumber);
        return businessService.searchByPageForApi(businessDto);
    }



    /**
     * 详情页 - 商户信息
     */
    @RequestMapping(value = "/detail/info/{id}",method = RequestMethod.GET)
    public BusinessDto detail(@PathVariable("id")Long id) {
        return businessService.getById(id);
    }

}
