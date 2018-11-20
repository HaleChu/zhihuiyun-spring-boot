package com.zhy.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: Hao.Chu
 * @create: 2018/11/20 16:29
 */

@Data
public class PageResult<T> {

    private Long total; // 总条数
    private Integer totlePage; // 总页数
    private List<T> items; // 当前页数据

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totlePage, List<T> items) {
        this.total = total;
        this.totlePage = totlePage;
        this.items = items;
    }
}
