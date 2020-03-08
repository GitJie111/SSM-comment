package org.xunqi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xunqi.bean.Page;
import org.xunqi.constant.CategoryConst;
import org.xunqi.dto.BusinessDto;
import org.xunqi.dto.BusinessListDto;
import org.xunqi.mapper.BusinessMapper;
import org.xunqi.pojo.Business;
import org.xunqi.service.BusinessService;
import org.xunqi.util.CommonUtil;
import org.xunqi.util.FileUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jerry
 */

@Service("businessService")
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessMapper businessMapper;

    /**
     *  图片保存路径
     */
    @Value("${businessImage.savePath}")
    private String savePath;

    /**
     *  图片访问路径
     */
    @Value("${businessImage.url}")
    private String url;

    @Override
    public boolean add(BusinessDto businessDto) {

        Business business = new Business();
        BeanUtils.copyProperties(businessDto,business);

        if (businessDto.getImgFile() != null && businessDto.getImgFile().getSize() > 0) {
            try {
                String fileName = FileUtil.save(businessDto.getImgFile(),savePath);
                business.setImgFileName(fileName);

                //默认已售数量为0
                business.setNumber(0);
                //默认评论总数为0
                business.setCommentTotalNum(0L);
                //默认评论星星总数为0
                business.setStarTotalNum(0L);
                //调用新增方法

                int addResult = businessMapper.insert(business);
                if (addResult > 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                log.error("【商铺新增】e={}",e);
                return false;
            }
        }

        return false;
    }

    @Override
    public BusinessDto getById(Long id) {

        BusinessDto businessDto = new BusinessDto();
        Business business = businessMapper.selectById(id);
        BeanUtils.copyProperties(business,businessDto);

        businessDto.setImg(url + business.getImgFileName());
        businessDto.setStar(this.getStar(business));

        return businessDto;
    }

    @Override
    public List<BusinessDto> searchByPage(BusinessDto businessDto) {

        List<BusinessDto> businessDtoList = new ArrayList<>();
        Business business = new Business();
        BeanUtils.copyProperties(businessDto,business);

        //循环读取数据
        List<Business> businessList = businessMapper.selectByPage(business);
        for (Business business1 : businessList) {
            BusinessDto businessDtoTemp = new BusinessDto();
            businessDtoList.add(businessDtoTemp);
            BeanUtils.copyProperties(business1,businessDtoTemp);
            businessDtoTemp.setImg(url + business1.getImgFileName());
            businessDtoTemp.setStar(this.getStar(business1));
        }

        return businessDtoList;
    }

    @Override
    public BusinessListDto searchByPageForApi(BusinessDto businessDto) {

        BusinessListDto result = new BusinessListDto();

        //组织查询条件
        Business businessForSelect = new Business();
        BeanUtils.copyProperties(businessDto,businessForSelect);

        //当关键字不为空时，把关键字的值分别设置到标题、副标题、描述中
        if (CommonUtil.isEmpty(businessDto.getKeyword())) {
            businessForSelect.setTitle(businessDto.getKeyword());
            businessForSelect.setSubtitle(businessDto.getKeyword());
            businessForSelect.setDesc(businessDto.getKeyword());
        }
        //当类别为全部(all)时，需要将类别清空，不作为过滤条件
        if (businessDto.getCategory() != null && CategoryConst.ALL.equals(businessDto.getCategory())) {
            businessForSelect.setCategory(null);
        }
        //前端app页码从0开始计算，这里需要+1
        int currentPage = businessForSelect.getPage().getCurrentPage();
        businessForSelect.getPage().setCurrentPage(currentPage + 1);

        List<Business> list =  businessMapper.selectLikeByPage(businessForSelect);

        //经过查询后根据page对象设置hasMore
        Page page = businessForSelect.getPage();
        result.setHasMore(page.getCurrentPage() < page.getTotalNumber());

        //对查询出的结果进行格式化
        for (Business business : list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.getData().add(businessDtoTemp);
            BeanUtils.copyProperties(business,businessDtoTemp);
            businessDtoTemp.setImg(url + business.getImgFileName());
            // 为兼容前端mumber这个属性
            businessDtoTemp.setNumber(business.getNumber());
            businessDtoTemp.setStar(this.getStar(business));
        }

        return result;
    }

    @Override
    public boolean delete(Long id) {
        //先查出来用户
        Business business = businessMapper.selectById(id);
        int deleteResult = businessMapper.delete(id);
        FileUtil.delete(savePath + business.getImgFileName());
        if (deleteResult > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(BusinessDto businessDto) {

        Business business = new Business();
        BeanUtils.copyProperties(businessDto,business);

        String fileName = null;

        if (businessDto.getImgFile() != null && businessDto.getImgFile().getSize() > 0) {
            try {
                fileName = FileUtil.save(businessDto.getImgFile(),savePath);
                business.setImgFileName(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("【商铺修改】e={}",e);
                return false;
            }
        }

        int updateCount = businessMapper.update(business);

        if (updateCount <= 0) {
            return false;
        }
        if (fileName != null) {
            return FileUtil.delete(savePath + businessDto.getImgFile());
        }

        return true;
    }


    private int getStar(Business business) {
        if (business.getStarTotalNum() != null && business.getCommentTotalNum() != null && business.getCommentTotalNum() != 0) {
            int result = (int) (business.getStarTotalNum() / business.getCommentTotalNum());
            return result;
        } else {
            return 0;
        }
    }
}
