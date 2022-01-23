package com.chufinfo.sorting.utils;

import com.chufinfo.sorting.utils.bean.LivePageVo;
import com.chufinfo.sorting.utils.constants.CacheConstants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

    public static Map returnData( PageInfo<Map> pageInfo){
        return new HashMap(){{
            this.put("pages",pageInfo.getPages());
            this.put("total",pageInfo.getTotal());
            this.put("pageNum",pageInfo.getPageNum());
            this.put("pageSize",pageInfo.getPageSize());
        }};
    }

    public static LivePageVo paginate(int page,int pageSize,List rows){
        PageHelper.startPage(page, pageSize);
        PageInfo pageInfo = new PageInfo(rows);
        LivePageVo pageVo = new LivePageVo();
        pageVo.setResult(rows);
        pageVo.setPageInfo(Util.returnData(pageInfo));
        return  pageVo;
    }
}
