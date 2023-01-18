package com.lyf.model.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * 分页dto
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月4日 下午12:59:00
 */
public class PageResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //总记录数
    private long totalCount;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页数
    private int currentPage;

    private Boolean desc;
    //列表数据
    private List<T> list;

    /**
     * 分页
     * @param list        列表数据
     * @param totalCount  总记录数
     * @param pageSize    每页记录数
     * @param currPage    当前页数
     * @param desc    排序
     */
    public PageResponse(List<T> list, long totalCount, int pageSize, int currPage, Boolean desc) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currPage;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
        this.desc = desc;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currPage) {
        this.currentPage = currPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Boolean getDesc() {
        return desc;
    }

    public void setDesc(Boolean desc) {
        this.desc = desc;
    }
}

