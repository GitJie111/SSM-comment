package org.xunqi.service.impl;
import java.util.*;

import org.springframework.stereotype.Service;
import org.xunqi.dto.echarts.Option;
import org.xunqi.dto.echarts.Serie;
import org.xunqi.mapper.ReportMapper;
import org.xunqi.service.OrderReportService;

import javax.annotation.Resource;

/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-15 15:09
 **/
@Service("orderReportService")
public class OrderReportServiceImpl implements OrderReportService {

    @Resource
    private ReportMapper reportMapper;

    @Override
    public Option count() {
        Option option = new Option();
        List<Map<String,String>> list = reportMapper.countOrder();

        // 类别
        Set<String> categoryNameSet = new TreeSet<>();

        //类别+时间为KEY，数量为VALUE
        Map<String,Long> countMap = new HashMap<String, Long> ();
        for (Map<String, String> map : list) {
            categoryNameSet.add(map.get("categoryName"));
            countMap.put(map.get("categoryName") + map.get("hour"),Long.valueOf(map.get("count")));
        }

        //设置参数线条中的分类
        option.getLegend().setData(new ArrayList<String>(categoryNameSet));
        //设置参数的X轴坐标
        List<String> hours = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            hours.add(String.format("%02d",i));
        }
        option.getxAxis().setData(hours);

        //设置线条的名称和数值
        for(String categoryName : option.getLegend().getData()) {
            Serie serie = new Serie();
            option.getSeries().add(serie);
            serie.setName(categoryName);
            serie.setType("line");
            for (String hour : hours) {
                serie.getData().add(countMap.get(categoryName + hour) == null ? 0 : countMap.get(categoryName + hour));
            }
        }
        return option;
    }
}
