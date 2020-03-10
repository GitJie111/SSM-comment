package org.xunqi.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.xunqi.bean.SysParam;
import org.xunqi.constant.SysParamKeyConst;
import org.xunqi.mapper.BusinessMapper;
import org.xunqi.mapper.SysParamMapper;

/**
 * 商户相关的定时任务
 * @author Jerry
 */
@Component("BusinessTask")
public class BusinessTask {

	private static final Logger logger = LoggerFactory.getLogger(BusinessTask.class);
	
	@Resource
	private BusinessMapper businessMapper;
	
	@Resource
	private SysParamMapper sysParamMapper;

	/**
	 * 同步已售数量
	 */
	public void synNumber() {
		logger.info("已同步！");
	}
	
	/**
	 * 同步星级
	 */
	@Transactional
	public void synStar() {
		logger.info("synStar start");
		// 先获取上次同步的时间(最后同步时间)
		SysParam sysParam = sysParamMapper.selectByKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
		Map<String,Date> map = new HashMap<>();
		map.put("startTime", sysParam.getParamValue());
		// 以当前系统时间做为同步的截止时间，同时也做为下次同步的起始时间
		Date endTime = new Date();
		map.put("endTime", endTime);
		// 按这样起始时间-结束时间同步：商户对应的【星星总数】、【评论总次数】
		// 如果起始时间为NULL，那说明是第一次同步，需要将历史数据全步同步，一直同步到当前系统时间为止。
		businessMapper.updateStar(map);
		// 将当前这个系统时间更新到系统参数表中，做为下次同步的起始时间
		SysParam sysParamForUpdate = new SysParam();
		sysParamForUpdate.setParamKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
		sysParamForUpdate.setParamValue(endTime);
		sysParamMapper.updateKey(sysParamForUpdate);
		logger.info("synStar end");
	}
}