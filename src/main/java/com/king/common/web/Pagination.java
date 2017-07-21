package com.king.common.web;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/12 21:03
 * <p>
 * 分页信息
 */
public class Pagination<T> implements Serializable {

    private List<T> rows;               //数据
    private Integer pageNumber;         //页码
    private Integer pageSize;           //页面记录最大数
    private Integer total;              //记录总数

    public Pagination() {
    }

    public Pagination(HttpServletRequest request) {
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
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
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
}
