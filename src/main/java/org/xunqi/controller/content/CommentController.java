package org.xunqi.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jerry
 */
@Controller
@RequestMapping(value = "/comments")
public class CommentController {

    @RequestMapping
    public String init() {
        return "/content/commentList";
    }


}
