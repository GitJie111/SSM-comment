package org.xunqi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.xunqi.dto.ActionDto;
import org.xunqi.mapper.ActionMapper;
import org.xunqi.pojo.Action;
import org.xunqi.service.ActionService;

import javax.annotation.Resource;

/**
 * @author Jerry
 */
@Service("actionService")
public class ActionServiceImpl implements ActionService {

    @Resource
    private ActionMapper actionMapper;

    @Override
    public boolean add(ActionDto actionDto) {
        if (actionMapper.insert(actionDto) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Long id) {
        if (actionMapper.deleteById(id) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(ActionDto actionDto) {
        if (actionMapper.update(actionDto) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ActionDto selectById(Long id) {
        ActionDto actionDto = new ActionDto();
        Action action = actionMapper.selectById(id);
        BeanUtils.copyProperties(action,actionDto);
        return actionDto;
    }
}
