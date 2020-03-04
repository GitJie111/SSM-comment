package org.xunqi.service.impl;
import	java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xunqi.dto.AdDto;
import org.xunqi.mapper.AdMapper;
import org.xunqi.pojo.Ad;
import org.xunqi.service.AdService;
import org.xunqi.util.FileUtil;

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


    @Value("${adImage.url}")
    private String adImageUrl;


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
            adDtoTemp.setImg(adImageUrl + ad.getImgFileName());
            BeanUtils.copyProperties(ad,adDtoTemp);
        }
        return result;
    }

    @Override
    public boolean update(AdDto adDto) {

        Ad ad = new Ad();
        BeanUtils.copyProperties(adDto,ad);
        String fileName = null;

        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            try {
                fileName = FileUtil.save(adDto.getImgFile(),adImageSavePath);
                ad.setImgFileName(fileName);
            } catch (IllegalStateException | IOException e) {
                return false;
            }
        }

        int updateCount = adMapper.update(ad);
        if (updateCount <= 0) {
            return false;
        }
        if (fileName != null) {
            return FileUtil.delete(adImageSavePath + adDto.getImgFile());
        }
        return true;
    }

    @Override
    public AdDto selectById(Long id) {
        AdDto adDto = new AdDto();
        Ad ad = adMapper.selectById(id);
        BeanUtils.copyProperties(ad,adDto);
        adDto.setImg(adImageUrl + ad.getImgFileName());
        return adDto;
    }

    @Override
    public boolean delete(Long id) {
        int result = adMapper.deleteAd(id);
        if (result > 0) {
            return true;
        } else {
            return false;
        }

    }
}
