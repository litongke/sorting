package com.chufinfo.sorting.releam;

import com.chufinfo.sorting.auth.OAuth2Token;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.model.User;
import com.chufinfo.sorting.response.ErrorCode;
import com.chufinfo.sorting.service.IUserService;
import com.chufinfo.sorting.utils.AdminTokenManager;
import com.chufinfo.sorting.utils.RedisService;
import com.chufinfo.sorting.utils.StrUtil;
import com.chufinfo.sorting.utils.constants.CacheConstants;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class OAuth2Realm extends AuthorizingRealm{
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisService redisService;
    /**
     *  密钥
     */
    private final static String SECRET = "CHUFINFO_TOKEN_SECRET";

    /**
     * 签名是谁生成
     */
    private static final String ISSUSER = "CHUFINFOISSADMINUSER";

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User)principals.getPrimaryPrincipal();
        //用户权限列表
        Set<String> permsSet =new HashSet<>(Arrays.asList(user.getLevel().toString()));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        //根据accessToken，查询用户信息
        String userStr=redisService.getCacheObject(CacheConstants.LOGIN_TOKEN_KEY+accessToken);
        //token失效
        if(StrUtil.isEmpty(userStr)){
            throw new RRException(ErrorCode.Login_out.getTip(),ErrorCode.Login_out.getCode());
        }
        //查询用户信息
        Long userId= AdminTokenManager.verifyTokenAndGetUserId(accessToken).getId();
        User user = userService.selectById(userId);
        // 设置过期时间 7天
        redisService.expire(getTokenKey(accessToken), CacheConstants.EXPIRE_TIME, TimeUnit.DAYS);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }

    private String getTokenKey(String token)
    {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }
}
