package com.chufinfo.sorting.utils.sql;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 * 
 * @author kenyon
 */
@Data
public class PageDataInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private long total;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 当前页数量
     */
    private Integer pageSize;

    /** 列表数据 */
    private List<?> rows;


    /**
     * 表格数据对象
     */
    public PageDataInfo()
    {
    }

    /**
     * 分页
     * 
     * @param list 列表数据
     * @param total 总记录数
     */
    public PageDataInfo(List<?> list, int total)
    {
        this.rows = list;
        this.total = total;
    }


}