package com.xmut.osm.common.bean;

import lombok.Data;

import java.util.List;

/**
 * 传递给客户端的分页信息
 *
 * @author 阮胜
 * @date 2018/7/23 13:52
 */
@Data
public class PageInfo<T> {
    private int currentPage;
    private long totalElements;
    private List<T> data;

    public PageInfo() {
    }

    public PageInfo(int currentPage, long totalElements, List<T> data) {

        this.currentPage = currentPage;
        this.totalElements = totalElements;
        this.data = data;
    }
}
