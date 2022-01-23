package com.chufinfo.sorting.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.services.cloudsearchv2.model.BaseException;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chufinfo.sorting.entity.param.*;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.mapper.primary.UserMapper;
import com.chufinfo.sorting.mapper.second.LimsSortingMapper;
import com.chufinfo.sorting.model.User;
import com.chufinfo.sorting.response.ErrorCode;
import com.chufinfo.sorting.service.IUserService;
import com.chufinfo.sorting.service.IYBXXService;
import com.chufinfo.sorting.utils.AdminTokenManager;
import com.chufinfo.sorting.utils.RedisService;
import com.chufinfo.sorting.utils.constants.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litk
 * @since 2022-01-06
 */
@Service
@Slf4j
public class YBXXServiceImpl extends ServiceImpl<LimsSortingMapper, YBXXResultParam> implements IYBXXService {
    @Override
    public List<YBXXResultParam> getYBXXByTxm(String txm) {
        return this.baseMapper.getYBXXByTxm(txm);
    }
}
