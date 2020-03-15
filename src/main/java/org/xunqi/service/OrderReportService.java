package org.xunqi.service;

import org.xunqi.dto.echarts.Option;

/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-15 15:01
 **/
public interface OrderReportService {

    /**
     * 按商户类别、每小时为一个时间段（00-01点，01-02点……23-00点）
     * 统计当前系统时间前一天订单数量
     * 并把数据组织成echarts所需参数格式
     * @return 报表参数
     */
    Option count();

}
