package com.chufinfo.sorting.utils.http;

import okhttp3.OkHttpClient;

public class OkClient {

    private static OkHttpClient client = new OkHttpClient();

    public OkHttpClient getHttpClient(){
        return client;
    }

}