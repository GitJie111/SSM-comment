package org.xunqi.controller.report;
import java.util.ArrayList;
import	java.util.List;
import	java.util.Random;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xunqi.dto.echarts.Option;
import org.xunqi.dto.echarts.Serie;
import org.xunqi.service.OrderReportService;

import javax.annotation.Resource;
import java.util.Arrays;


/**
 * @Created with IntelliJ IDEA.
 * @author: 夏沫止水
 * @create: 2020-03-15 15:24
 **/
@Controller
@RequestMapping(value = "/orderReport")
public class OrderReportController {

    @Resource
    private OrderReportService orderReportService;

    @RequestMapping
    public String index() {
        return "/report/orderCount";
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    @ResponseBody
    public Option count() {
        Option option = orderReportService.count();

        String[] names = new String[]{"电影","结婚","美食"};
        option.getLegend().setData(Arrays.asList(names));
        //生成随机数
        Random random = new Random();
        List<Serie> series = new ArrayList<>();
        //循环读取数据
        for (String name : names) {
            Serie serie = new Serie();
            serie.setName(name);
            serie.setType("line");
            for (int i = 0; i < 24; i++) {
                serie.getData().add(Long.valueOf(random.nextInt(1000)));
            }
            series.add(serie);
        }
        option.setSeries(series);

        return option;
    }

}
