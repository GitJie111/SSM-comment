package org.xunqi.service.impl;
import	java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xunqi.dto.AdDto;
import org.xunqi.mapper.AdMapper;
import org.xunqi.pojo.Ad;
import org.xunqi.service.AdService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Jerry
 */
@Service("adService")
public class AdServiceImpl implements AdService {

    @Resource
    private AdMapper adMapper;

    @Value("${adImage.savePath}")
    private String adImageSavePath;

    @Override
    //TODO 可以改成获取失败
    public boolean add(AdDto adDto) {
        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setLink(adDto.getLink());
        ad.setWeight(adDto.getWeight());
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + adDto.getImgFile().getOriginalFilename();
            File file = new File(adImageSavePath + fileName);
            File fileFolder = new File(adImageSavePath);
            if (!fileFolder.exists()) {
                fileFolder.mkdirs();
            }
            try {
                adDto.getImgFile().transferTo(file);
                ad.setImgFileName(fileName);
                adMapper.insert(ad);
                return true;
            } catch (IllegalStateException | IOException e) {
                // TODO 需要添加日志
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<AdDto> searchByPage(AdDto adDto) {

        List<AdDto> result = new ArrayList <AdDto>();
        Ad condition = new Ad();
        BeanUtils.copyProperties(adDto,condition);
        List<Ad> adList = adMapper.selectByPage(condition);

        for (Ad ad : adList) {
            AdDto adDtoTemp = new AdDto();
            result.add(adDtoTemp);
            BeanUtils.copyProperties(ad,adDtoTemp);
        }
        return result;
    }
}
