package com.chufinfo.sorting.service;

import com.alibaba.fastjson.JSONObject;
import com.chufinfo.sorting.model.User;
import com.chufinfo.sorting.utils.RedisService;
import com.chufinfo.sorting.utils.constants.CacheConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chufinfo.sorting.utils.AdminTokenManager;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootTestService {
    @Autowired
    private RedisService redisService;

    @Test
    public void Test() throws Exception {
        User user =new User();
        user.setLevel(1);
        user.setId(3L);
        user.setUserName("testUser2");
        user.setRealName("测试用户2");
        String token = AdminTokenManager.createToken(user.getId(),user.getUserName(),user.getLevel());
        redisService.setCacheObject(CacheConstants.LOGIN_TOKEN_KEY+token, JSONObject.toJSONString(user),CacheConstants.EXPIRE_TIME, TimeUnit.DAYS);
        System.out.println(token);
    }

    @Test
    public void TestXML() throws Exception{

    }

    @Test
    public void TestAws() throws  Exception{


    }



}
