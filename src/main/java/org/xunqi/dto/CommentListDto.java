package org.xunqi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Jerry
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CommentListDto {

    private boolean hasMore;

    private List<CommentDto> commentDtoList;

}
