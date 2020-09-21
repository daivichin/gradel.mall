package com.gradel.common.shared.response;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> datas;
    private long pageOffset; //当前的页数
    private long pageSize; //一页显示多少条
    private long totalRecord; //总记录数
    private long totalPage; //总页数

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public long getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(long pageOffset) {
        this.pageOffset = pageOffset;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
        this.calculateTotalPage(totalRecord);
    }

    public void calculateTotalPage(long totalRecord){
        if(totalRecord == 0) {
            this.totalPage = 0;
        } else {
            if (totalRecord % pageSize == 0) {
                this.totalPage = totalRecord / pageSize;
            } else {
                this.totalPage = (totalRecord / pageSize) + 1;
            }
        }
    }
    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
}