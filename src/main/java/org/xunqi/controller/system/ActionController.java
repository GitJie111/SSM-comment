package org.xunqi.controller.system;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xunqi.dto.ActionDto;
import org.xunqi.dto.PageCodeDto;
import org.xunqi.enums.PageCodeEnum;
import org.xunqi.pojo.Action;
import org.xunqi.service.ActionService;

import javax.annotation.Resource;

/**
 * 动作相关
 * @author Jerry
 */
@Controller
@RequestMapping(value = "/actions")
public class ActionController {

    @Resource
    private ActionService actionService;


    /**
     * 新增动作
     */
    @RequestMapping(method = RequestMethod.POST)
    public PageCodeDto add(ActionDto actionDto) {
        PageCodeDto result;

        if (actionService.add(actionDto)) {
            result = new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.ADD_FAIL);
        }
        return result;
    }


    /**
     * 删除动作
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public PageCodeDto deleteById(@PathVariable("id") Long id) {
        PageCodeDto result;

        if (actionService.remove(id)) {
            result = new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.REMOVE_FAIL);
        }
        return result;
    }


    /**
     * 修改动作
     */
    @RequestMapping(value = "{/id}",method = RequestMethod.PUT)
    public PageCodeDto update(ActionDto actionDto) {
        PageCodeDto result;

        if (actionService.update(actionDto)) {
            result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
        }
        return result;
    }


    /**
     * 根据主键id获取动作信息
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ActionDto getById(@PathVariable("id") Long id) {
        ActionDto actionDto = actionService.selectById(id);
        return actionDto;
    }

}
