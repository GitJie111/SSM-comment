package org.xunqi.controller.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xunqi.dto.AdDto;
import org.xunqi.service.AdService;

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

    @Value("${ad.number}")
    private int adNumber;


    @RequestMapping(value = "/homead",method = RequestMethod.GET)
    public List<AdDto> homeads() {

        AdDto adDto = new AdDto();
        adDto.getPage().setPageNumber(adNumber);
        return adService.searchByPage(adDto);
    }

}
