package com.chufinfo.sorting.utils.sql;

import com.chufinfo.sorting.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * TODO
 *
 * @author xj
 * @date 2022/1/10 14:58
 */
public class PageHelpUtil {
    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }
    public static void startPage(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
    }

    public static PageDataInfo getPage(List<?> pagelist, List<?> resultList)
    {
        PageDataInfo rspData = new PageDataInfo();
        PageInfo pageInfo = new PageInfo(pagelist);
        BeanUtils.copyProperties(pageInfo,rspData);
        rspData.setRows(resultList);
        return rspData;
    }

    public static PageDataInfo getPage(List<?> pagelist)
    {
        PageDataInfo rspData = new PageDataInfo();
        PageInfo pageInfo = new PageInfo(pagelist);
        BeanUtils.copyProperties(pageInfo,rspData);
        rspData.setRows(pagelist);
        return rspData;
    }
}
