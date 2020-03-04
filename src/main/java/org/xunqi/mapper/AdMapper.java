package org.xunqi.mapper;

import org.apache.ibatis.annotations.Param;
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


    /**
     *  根据主键修改
     * @param ad 待修改的广告对象
     * @return
     */
    int update(Ad ad);


    /**
     *  查询单条广告信息
     * @param id
     * @return
     */
    Ad selectById(@Param("id") Long id);


    /**
     *  删除广告信息
     * @param id
     * @return
     */
    int deleteAd(@Param("id") Long id);
}
