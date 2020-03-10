package org.xunqi.mapper;

import org.apache.ibatis.annotations.Param;
import org.xunqi.bean.SysParam;

/**
 * @author Jerry
 */
public interface SysParamMapper {

    /**
     * 根据KEY获取对应的系统参数值
     * @param key
     * @return
     */
    SysParam selectByKey(@Param("key") String key);


    /**
     *  根据KEY修改对应的系统参数值
     * @param sysParam
     * @return
     */
    int updateKey(SysParam sysParam);

}
