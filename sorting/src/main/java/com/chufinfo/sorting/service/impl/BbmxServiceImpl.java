package com.chufinfo.sorting.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chufinfo.sorting.entity.requestParam.BbmxManageListQueryParam;
import com.chufinfo.sorting.entity.requestParam.JobCountStatQueryParam;
import com.chufinfo.sorting.entity.responseParam.BbmxResultParam;
import com.chufinfo.sorting.entity.responseParam.JobCountStatResultParam;
import com.chufinfo.sorting.entity.vo.DictVo;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.mapper.primary.UserMapper;
import com.chufinfo.sorting.model.Bbmx;
import com.chufinfo.sorting.mapper.primary.BbmxMapper;
import com.chufinfo.sorting.model.User;
import com.chufinfo.sorting.service.IBbmxService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chufinfo.sorting.utils.SecurityUtils;
import com.chufinfo.sorting.utils.sql.PageDataInfo;
import com.chufinfo.sorting.utils.sql.PageHelpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
@Service
public class BbmxServiceImpl extends ServiceImpl<BbmxMapper, Bbmx> implements IBbmxService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageDataInfo getManageList(BbmxManageListQueryParam bbmxManageListQueryParam) {
        if (bbmxManageListQueryParam.getPage() != null && bbmxManageListQueryParam.getPage().equals(1)) {
            //分拣页面明细列表
            User user = userMapper.selectById(SecurityUtils.getUserId());
            if (user == null) {
                throw new RRException("用户不存在");
            }
            Instant instant = user.getKsTime().toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            bbmxManageListQueryParam.setBeginTime(instant.atZone(zoneId).toLocalDate());
        }
        if (bbmxManageListQueryParam.getEndTime() != null) {
            bbmxManageListQueryParam.setEndTime(bbmxManageListQueryParam.getEndTime().plusDays(1L));
        }
        PageHelpUtil.startPage();
        List<BbmxResultParam> resultParamList = this.baseMapper.getManageList(bbmxManageListQueryParam);
        resultParamList.forEach(t->{
            t.setStatusName(this.getBbmxStatusName(t.getStatus()));
        });
        return PageHelpUtil.getPage(resultParamList);
    }

    @Override
    public List<DictVo> getSgjBhList(Long fjkId) {
        Long userId = SecurityUtils.getUserId();
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RRException("用户不存在");
        }
        Instant instant = user.getKsTime().toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime createTime = instant.atZone(zoneId).toLocalDateTime();
        List<DictVo> resultList = this.baseMapper.getSgjBhList(fjkId, userId, createTime);
        return resultList;
    }

    @Override
    public PageDataInfo getJobCountStatistics(JobCountStatQueryParam queryParam) {
        if (queryParam.getEndTime() != null) {
            queryParam.setEndTime(queryParam.getEndTime().plusDays(1L));
        }
        PageHelpUtil.startPage();
        List<JobCountStatResultParam> resultParamList = this.baseMapper.getJobCountStatistics(queryParam);
        return PageHelpUtil.getPage(resultParamList);
    }

    /**
     * 获取标本明细状态名称
     * @param status
     * @return
     */
    private String getBbmxStatusName(Integer status){
        String statusName=null;
        switch (status){
            case 0: statusName="未交接";break;
            case 1: statusName="已交接";break;
        }
        return statusName;
    }
}
