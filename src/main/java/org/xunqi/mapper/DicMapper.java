package org.xunqi.mapper;

import org.xunqi.pojo.Dic;

import java.util.List;

/**
 * @author Jerry
 */
public interface DicMapper {

    /** 查询全部信息
     * @param dic
     * @return
     */
    List<Dic> findAll(Dic dic);

}
