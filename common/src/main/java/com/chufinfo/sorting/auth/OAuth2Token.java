package com.chufinfo.sorting.auth;

import org.apache.shiro.authc.AuthenticationToken;

public class OAuth2Token implements AuthenticationToken{
   
    private static final long serialVersionUID = 2578050018783578200L;
    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
