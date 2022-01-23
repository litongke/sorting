package com.chufinfo.sorting.utils.bean;

import lombok.Data;

/**
 * @Auther: kenyon
 * @Description:
 *         PageHelper.startPage(null = = billSearchInfoParam.getPageNum () ? 1 : billSearchInfoParam.getPageNum(), 10);
 *         查询列表 List<Entity> list
 *         PageInfo pageInfo = new PageInfo(list);
 *         LivePageVo livePageVo = new LivePageVo();
 *         livePageVo.setResult(list);
 *         livePageVo.setPageInfo(Util.returnData(pageInfo));
 */
@Data
public class LivePageVo {
    Object pageInfo;

    Object result;
}
