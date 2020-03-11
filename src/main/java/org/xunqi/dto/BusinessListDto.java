package org.xunqi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jerry
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessListDto {

    private boolean hasMore;

    private List<BusinessDto> data;

    public BusinessListDto() {
        this.data = new ArrayList<>();
    }

}
