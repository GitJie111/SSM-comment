package org.xunqi.mapper;

import java.util.List;
import java.util.Map;

/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-15 14:51
 **/
public interface ReportMapper {

    /**
     * 按商户类别和时间段统计订单数
     * @return 订单数统计结果集，key值说明：
     *                categoryName 商户类别的中文名
     *                hour 小时数，如：01，表示凌晨1点至2点这个时间段
     *                count 订单数量
     */
    List<Map<String,String>> countOrder();

}
