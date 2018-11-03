package com.solar.calm.web.rest.param;

import java.io.Serializable;

public abstract class BaseParams implements Serializable {

    private static final long serialVersionUID = -103658650614029839L;

    private Integer pageNo;

    private Integer pageSize = 20;

    private String sortBy;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
