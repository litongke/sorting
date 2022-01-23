package com.chufinfo.sorting.service;

import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.model.Dict;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiejun
 * @since 2022-01-11
 */
public interface IDictService extends IService<Dict> {
    String getDicValue(String bm,String lx);
}
