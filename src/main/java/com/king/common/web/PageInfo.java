package com.king.common.web;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/12 21:03
 * <p>
 * 分页信息
 */
public class PageInfo<T> {

    private final static int DEFAULT_PAGE_SIZE = 20; //默认显示的记录数

    private final static int DEFAULT_PAGE = 1;//默认页

    private Integer total; // 总记录
    private List<T> rows; //显示的记录

    private Integer from;
    private Integer size;
    private Integer nowPage; // 当前页
    private Integer pageSize; // 每页显示的记录数

    public PageInfo() {
    }

    /**
     * 初始化获取page信息
     *
     * @param request 请求信息
     */
    public PageInfo(HttpServletRequest request) {
        initPage();

        try {
            this.nowPage = Integer.parseInt(request.getParameter("page"));
            this.pageSize = Integer.parseInt(request.getParameter("rows"));
        } catch (Exception ignored) {

        }
        //计算当前页
        if (this.nowPage < 0) {
            this.nowPage = 1;
        }
        //记录每页显示的记录数
        if (this.pageSize < 0) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }

        //计算开始的记录和结束的记录
        this.from = (this.nowPage - 1) * this.pageSize;
        this.size = this.pageSize;
    }

    /**
     * 初始化信息
     */
    public final void initPage() {
        this.nowPage = DEFAULT_PAGE;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
