package org.xunqi.service;

import org.xunqi.bean.Page;
import org.xunqi.dto.CommentDto;
import org.xunqi.dto.CommentForSubmitDto;
import org.xunqi.dto.CommentListDto;
import org.xunqi.pojo.Comment;

import java.util.List;

/**
 * @author Jerry
 */
public interface CommentService {

    /**
     *  保存评论
     * @param commentForSubmitDto
     * @return
     */
    boolean add(CommentForSubmitDto commentForSubmitDto);


    /**
     *  按分页条件，根据商户ID获取商户下的评论列表dto
     * @param businessId
     * @param page
     * @return
     */
    CommentListDto getListByBusinessId(Long businessId, Page page);


    /**
     * 根据评论内容进行模糊分页查询
     * @param commentDto
     * @return
     */
    List<CommentDto> selectByCommentPage(CommentDto commentDto);

}
