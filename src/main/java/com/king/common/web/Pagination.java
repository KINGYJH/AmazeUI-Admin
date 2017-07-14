package com.king.common.web;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/12 21:03
 * <p>
 * 分页信息
 */
public class Pagination<T> {

    private List<T> rows;               //数据
    private Integer dataStartNumber;    //数据库数据开始数
    private Integer pageNumber;         //页码
    private Integer pageSize;           //页面记录最大数
    private Integer total;              //记录总数
    private T queryParams;              //查询参数
    private String orderBy;             //排序  多个用','分隔

    public Pagination() {
    }

    public Pagination(HttpServletRequest request, T queryParams) {
        this.queryParams = queryParams;

        Integer pageNumber = 1;
        Integer pageSize = 20;

        try {
            pageNumber = Integer.parseInt(request.getParameter("page"));
            pageSize = Integer.parseInt(request.getParameter("rows"));
        } catch (Exception ignored) {
            System.out.println("获取分页参数失败");
        }
        //计算当前页
        if (pageNumber < 0) {
            this.pageNumber = 1;
        } else {
            //当前页
            this.pageNumber = pageNumber;
        }
        //记录每页显示的记录数
        if (pageSize < 0) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = pageSize;
        }
        //计算开始的记录和结束的记录
        this.dataStartNumber = (this.pageNumber - 1) * this.pageSize;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getDataStartNumber() {
        return dataStartNumber;
    }

    public void setDataStartNumber(Integer dataStartNumber) {
        this.dataStartNumber = dataStartNumber;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public T getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(T queryParams) {
        this.queryParams = queryParams;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
