package org.xunqi.controller.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.xunqi.bean.Page;
import org.xunqi.constant.CommentStateConst;
import org.xunqi.dto.*;
import org.xunqi.enums.ApiCodeEnum;
import org.xunqi.service.*;
import org.xunqi.util.CommonUtil;

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

    @Resource
    private CommentService commentService;

    @Resource
    private MemberService memberService;

    @Resource
    private OrdersService ordersService;

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
    public BusinessListDto homelist(BusinessDto businessDto) {
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


    /**
     *  详情页 --用户评论
     */
    @RequestMapping(value = "/detail/comment/{currentPage}/{businessId}", method = RequestMethod.GET)
    public CommentListDto detail(@PathVariable("businessId") Long businessId, Page page) {
        CommentListDto commentListDto = commentService.getListByBusinessId(businessId,page);
        return commentListDto;
    }


    /**
     *  订单列表
     */
    @RequestMapping(value = "/orderlist/{phone}",method = RequestMethod.GET)
    public List<OrdersDto> orderList(@PathVariable("phone") Long phone) {
        //根据手机号取出会员ID
        Long memberId = memberService.getIdByPhone(phone);
        return ordersService.getListByMemberId(memberId);
    }


    /**
     *  提交评论
     */
    @RequestMapping(value = "/submitComment", method = RequestMethod.POST)
    public ApiCodeDto submitComment(CommentForSubmitDto dto) {
        ApiCodeDto result;
        //1、效验登录信息：token、手机号
        Long phone = memberService.getPhone(dto.getToken());

        if (phone != null) {
            //2、根据手机号取出会员id
            Long memberId = memberService.getIdByPhone(phone);
            //3、根据提交上来的订单ID获取对应的会员ID，效验与当前登录的会员是否一致
            OrdersDto ordersDto = ordersService.selectById(dto.getId());
            if (ordersDto.getMemberId().equals(memberId)) {
                //4、保存评论
                commentService.add(dto);
                result = new ApiCodeDto(ApiCodeEnum.SUCCESS);
            } else {
                result = new ApiCodeDto(ApiCodeEnum.NO_AUTH);
            }
        } else {
            result = new ApiCodeDto(ApiCodeEnum.NOT_LOGGED);
        }
        return result;
    }



    /**
     *  根据手机号下发送短信验证码
     */
    @RequestMapping(value = "/sms",method = RequestMethod.POST)
    public ApiCodeDto sms(@RequestParam("username") Long phone) {

        ApiCodeDto dto = new ApiCodeDto();
        //1、验证用户手机号是否存在（是否注册过）
        if (memberService.exists(phone)) {
            //2、生成6位随机数
            String code = String.valueOf(CommonUtil.random(6));
            //3、保存手机号与对应的mad5(6位随机数)
            if (memberService.saveCode(phone,code)) {
                // 4、调用短信通道，将明文6位随机数发送到对应的手机上。模拟
                if (memberService.sendCode(phone,code)) {
                    dto = new ApiCodeDto(ApiCodeEnum.SUCCESS.getErrno(),dto.getCode());
                } else {
                    dto = new ApiCodeDto(ApiCodeEnum.SEND_FAIL);
                }
            } else {
                dto = new ApiCodeDto(ApiCodeEnum.REPEAT_REQUEST);
            }
        } else {
            dto = new ApiCodeDto(ApiCodeEnum.USER_NOT_EXISTS);
        }
        return dto;
    }


    /**
     *  会员登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ApiCodeDto login(@RequestParam("username") Long phone,@RequestParam("code") String code) {

        ApiCodeDto dto;

        // 1、用手机号取出保存的md5(6位随机数)，能取到，并且与提交上来的code值相同为校验通过
        String saveCode = memberService.getCode(phone);

        if (saveCode != null) {
            if (saveCode.equals(code)){
                //2、如果效验通过，生成一个32位的token
                String token = CommonUtil.getUUID();
                //3、保存手机号与对应的token
                memberService.saveToken(token,phone);
                //4、将这个token返回给前端
                dto = new ApiCodeDto(ApiCodeEnum.SUCCESS);
                dto.setToken(token);
            } else {
                dto = new ApiCodeDto(ApiCodeEnum.CODE_ERROR);
            }
        } else {
            dto = new ApiCodeDto(ApiCodeEnum.CODE_INVALID);
        }
        return dto;
    }


    /**
     *  买单
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ApiCodeDto order(OrderForBuyDto orderForBuyDto) {
        ApiCodeDto dto;
        // 1、校验token是否有效（缓存中是否存在这样一个token，并且对应存放的会员信息（这里指的是手机号）与提交上来的信息一致）
        Long phone = memberService.getPhone(orderForBuyDto.getToken());
        if (phone != null) {
            // 2、根据手机号获取会员主键
            Long memberId = memberService.getIdByPhone(phone);
            // 3、保存订单
            OrdersDto ordersDto = new OrdersDto();
            ordersDto.setNum(orderForBuyDto.getNum());
            ordersDto.setPrice(orderForBuyDto.getPrice());
            ordersDto.setBusinessId(orderForBuyDto.getId());
            ordersDto.setMemberId(memberId);
            ordersService.add(ordersDto);
            dto = new ApiCodeDto(ApiCodeEnum.SUCCESS);
        } else {
            dto = new ApiCodeDto(ApiCodeEnum.NOT_LOGGED);
        }
        return dto;
    }

}
