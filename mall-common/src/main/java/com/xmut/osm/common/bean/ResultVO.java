package com.xmut.osm.common.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author 阮胜
 * @date 2018/7/21 15:56
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    private String message;
    private Boolean success;
    private T data;
}
