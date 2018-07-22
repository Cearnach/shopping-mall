package com.xmut.osm.common.bean;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author 阮胜
 * @date 2018/7/22 23:35
 */
@Data
public class PageBean {
    @Min(0)
    private int page = 0;
    @Min(0)
    @Max(10)
    private int size = 10;

    public PageBean(@Min(0) int page, @Min(0) @Max(10) int size) {
        this.page = page;
        this.size = size;
    }

    public PageBean() {

    }
}
