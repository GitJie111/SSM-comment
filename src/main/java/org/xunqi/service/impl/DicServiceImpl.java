package org.xunqi.service.impl;

import org.springframework.stereotype.Service;
import org.xunqi.mapper.DicMapper;
import org.xunqi.pojo.Dic;
import org.xunqi.service.DicService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jerry
 */
@Service("dicService")
public class DicServiceImpl implements DicService {

    @Resource
    private DicMapper dicMapper;

    @Override
    public List<Dic> getListByType(String type) {
        Dic dic = new Dic();
        dic.setType(type);
        return dicMapper.findAll(dic);
    }
}
