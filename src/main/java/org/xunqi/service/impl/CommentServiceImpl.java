package org.xunqi.service.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xunqi.bean.Page;
import org.xunqi.constant.CommentStateConst;
import org.xunqi.dto.CommentDto;
import org.xunqi.dto.CommentForSubmitDto;
import org.xunqi.dto.CommentListDto;
import org.xunqi.mapper.CommentMapper;
import org.xunqi.mapper.OrdersMapper;
import org.xunqi.pojo.Business;
import org.xunqi.pojo.Comment;
import org.xunqi.pojo.Orders;
import org.xunqi.service.CommentService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jerry
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private OrdersMapper ordersMapper;

    @Override
    @Transactional
    public boolean add(CommentForSubmitDto commentForSubmitDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentForSubmitDto,comment);
        comment.setId(null);
        comment.setOrdersId(commentForSubmitDto.getId());
        comment.setCreateTime(new Date());

        //保存评论
        commentMapper.insert(comment);
        Orders orders = new Orders();
        orders.setId(commentForSubmitDto.getId());
        orders.setCommentState(CommentStateConst.HAS_COMMENT);

        //更新订单评论状态
        ordersMapper.update(orders);
        return true;
    }

    @Override
    public CommentListDto getListByBusinessId(Long businessId, Page page) {

        CommentListDto commentListDto = new CommentListDto();

        //组织查询条件
        Comment comment = new Comment();
        Orders orders = new Orders();
        Business business = new Business();

        //评论里包含了订单对象
        comment.setOrders(orders);
        //订单对象里包含了商户对象
        orders.setBusiness(business);
        //设置商户主键
        business.setId(businessId);
        //前端app页码从0开始计算，这里需要+1
        page.setCurrentPage(page.getCurrentPage() + 1);

        //设置分页条件
        comment.setPage(page);
        List<Comment> commentList = commentMapper.selectByPage(comment);

        //组织返回值
        List<CommentDto> commentDtoList = new ArrayList<>();
        commentListDto.setData(commentDtoList);

        for (Comment commentTemp : commentList) {
            CommentDto commentDto = new CommentDto();
            commentDtoList.add(commentDto);
            BeanUtils.copyProperties(commentTemp,commentDto);
            //隐藏手机号中间4位
            StringBuffer phoneBuffer = new StringBuffer(String.valueOf(commentTemp.getOrders().getMember().getPhone()));
            commentDto.setPhone(phoneBuffer.replace(3,7,"***").toString());
        }

        commentListDto.setHasMore(page.getCurrentPage() < page.getTotalPage());

        return commentListDto;
    }
}
