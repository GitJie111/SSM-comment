package org.xunqi.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xunqi.dto.CommentDto;
import org.xunqi.service.CommentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jerry
 */
@Controller
@RequestMapping(value = "/comments")
public class CommentController {

    @Resource
    private CommentService commentService;

    @RequestMapping
    public String search(CommentDto commentDto, Model model) {
        List<CommentDto> commentDtoList = commentService.selectByCommentPage(commentDto);
        model.addAttribute("list",commentDtoList);
        model.addAttribute("searchParam",commentDto);
        return "/content/commentList";
    }


}
