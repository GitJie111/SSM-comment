package org.xunqi.mapper;

import org.xunqi.pojo.Comment;

import java.util.List;

/**
 * @author Jerry
 */
public interface CommentMapper {

    /**
     *  根据查询条件分页查询评论列表
     * @param comment
     * @return
     */
    List<Comment> selectByPage(Comment comment);


    /**
     *  新增
     * @param comment
     * @return
     */
    int insert(Comment comment);


    /**
     * 根据评论内容进行模糊分页查询
     * @param comment
     * @return
     */
    List<Comment> selectByCommentPage(Comment comment);

}
