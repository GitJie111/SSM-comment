package org.xunqi.mapper;

import org.xunqi.pojo.Ad;

import java.util.List;

/**
 * @author Jerry
 */
public interface AdMapper {

    /**
     *  新增方法
     * @param ad 对象参数类型
     * @return
     */
    int insert(Ad ad);

    /**
     *  根据标题进行模糊查询
     * @param condition
     * @return
     */
    List<Ad> selectByPage(Ad condition);
}
