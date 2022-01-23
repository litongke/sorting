package com.chufinfo.sorting.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amazonaws.services.cloudsearchv2.model.BaseException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chufinfo.sorting.entity.param.*;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.model.User;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.mapper.primary.UserMapper;
import com.chufinfo.sorting.response.ErrorCode;
import com.chufinfo.sorting.service.ISjxxService;
import com.chufinfo.sorting.service.IUserService;
import com.chufinfo.sorting.utils.AdminTokenManager;
import com.chufinfo.sorting.utils.RedisService;
import com.chufinfo.sorting.utils.SecurityUtils;
import com.chufinfo.sorting.utils.StringUtils;
import com.chufinfo.sorting.utils.constants.CacheConstants;
import com.chufinfo.sorting.utils.sql.PageDataInfo;
import com.chufinfo.sorting.utils.sql.PageHelpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private RedisService redisService;
    @Autowired
    private ISjxxService sjxxService;

    @Override
    public LoginResultParam login(LoginQueryParam userParam) {
        User queryUser=new User();
        queryUser.setUserName(userParam.getUserName());
        queryUser.setPassword(userParam.getPassword());
        queryUser.setDelFlag(SystemEnum.delFlag.OK.getCode());
        User resultUser =  this.baseMapper.selectOne(queryUser);
        if (resultUser == null) {
            throw new RRException("用户名或密码错误！");
        }
        String token = redisService.getCacheObject(CacheConstants.DETAILS_USER_ID + resultUser.getId());
        if(StringUtils.isNotEmpty(token)){
            log.warn("其他登录:{}", JSON.toJSONString(resultUser));
            redisService.expire(CacheConstants.LOGIN_TOKEN_KEY + token,1);
            redisService.expire(CacheConstants.DETAILS_USER_ID + resultUser.getId(),1);
        }
        SysUserResultParam sysUserResultParam=new SysUserResultParam();
        BeanUtils.copyProperties(resultUser,sysUserResultParam);
        LoginResultParam loginResultParam = new LoginResultParam();
        loginResultParam.setSysUser(sysUserResultParam);
        token = AdminTokenManager.createToken(resultUser.getId(), resultUser.getUserName(), resultUser.getLevel());
        loginResultParam.setToken(token);
        redisService.setCacheObject(CacheConstants.LOGIN_TOKEN_KEY + token, JSONObject.toJSONString(sysUserResultParam), CacheConstants.EXPIRE_TIME, TimeUnit.DAYS);
        redisService.setCacheObject(CacheConstants.DETAILS_USER_ID + resultUser.getId(), token, CacheConstants.EXPIRE_TIME, TimeUnit.DAYS);
        return loginResultParam;
    }
    @Override
    public boolean loginOut(String token) {
        try {
            return redisService.deleteObject(CacheConstants.LOGIN_TOKEN_KEY +token);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("loginOut异常",JSON.toJSONString(e));
            throw new RRException("后台错误，请联系管理员");
        }
    }

    @Override
    public boolean modifyStatus(ModifyStatusQueryParam modifyStatusQueryParam) {
        User sysUser = new User();
        Integer gzStatus=modifyStatusQueryParam.getGzStatus();
        sysUser.setId(modifyStatusQueryParam.getId());
        sysUser.setGzStatus(gzStatus);
        if(SystemEnum.gzStatus.START.getCode().equals(gzStatus))
            sysUser.setKsTime(new Date());
        if(SystemEnum.gzStatus.END.getCode().equals(gzStatus)) {
            sysUser.setKsTime(null);
            //下架所有试管架
            sjxxService.offAllSjxx();
        }
        return this.baseMapper.updateById(sysUser) > 0;
    }
    @Override
    public ModifyStatusQueryParam getGzStatus(){
        User queryUser=new User();
        queryUser.setId(SecurityUtils.getUserId());
        queryUser.setDelFlag(SystemEnum.delFlag.OK.getCode());
        User resultUser =  this.baseMapper.selectOne(queryUser);
        if (resultUser == null) {
            throw new RRException("用户错误！");
        }
        ModifyStatusQueryParam param=new ModifyStatusQueryParam();
        param.setId(resultUser.getId());
        param.setGzStatus(resultUser.getGzStatus());
        return param;
    }

    @Override
    public PageDataInfo jjUserList(UserFjkQueryParam userFjkQueryParam) {
        PageHelpUtil.startPage();
        EntityWrapper<User> wrapper=new EntityWrapper<>();
        wrapper.eq("level",SystemEnum.userLevel.JJRY.getCode());
        wrapper.eq("del_flag",SystemEnum.delFlag.OK.getCode());
        List<User> userList=this.baseMapper.selectList(wrapper);
        List<SysUserResultParam> sysUserResultParamList=new ArrayList<>();
        userList.forEach(user -> {
            SysUserResultParam param=new SysUserResultParam();
            BeanUtils.copyProperties(user,param);
            sysUserResultParamList.add(param);
        });
        return PageHelpUtil.getPage(sysUserResultParamList);
    }

    @Override
    public List<SysUserResultParam> statUserCzry(UserFjkQueryParam userFjkQueryParam) {
        EntityWrapper<User> wrapper=new EntityWrapper<>();
        wrapper.eq("level",SystemEnum.userLevel.FJRY.getCode());
        wrapper.eq("del_flag",SystemEnum.delFlag.OK.getCode());
        List<User> userList=this.baseMapper.selectList(wrapper);
        List<SysUserResultParam> sysUserResultParamList=new ArrayList<>();
        userList.forEach(user -> {
            SysUserResultParam param=new SysUserResultParam();
            BeanUtils.copyProperties(user,param);
            sysUserResultParamList.add(param);
        });
        return sysUserResultParamList;
    }
}
