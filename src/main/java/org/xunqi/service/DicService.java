package org.xunqi.service;

import org.xunqi.pojo.Dic;

import java.util.List;

/**
 * @author Jerry
 */
public interface DicService {


    /**
     *  根据类型获取字典表列表
     * @param type 类型
     * @return 字典型列表
     */
    public List<Dic> getListByType(String type);

}
