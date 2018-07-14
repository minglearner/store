package model;

import java.util.List;

public class PageBean<E> {
    private List<E> list;
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPage;
    private Integer totalCount;

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage() {
        this.totalPage = (int) Math.ceil(totalCount*1.0/pageSize);
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public PageBean (List<E> list,Integer pageSize,Integer currentPage,Integer totalCount){
        super();
        this.list = list;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalCount = totalCount;
    }
}
