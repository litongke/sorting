package com.chufinfo.sorting.utils.http.test;

import java.util.ArrayList;
import java.util.List;

import com.chufinfo.sorting.utils.http.AsyncHttpClientCallback;
import com.chufinfo.sorting.utils.http.HttpClientUtil;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;

/**
 * http client 使用
 * */
public class HttpClientDemo extends HttpClientUtil {

    public static void main(String[] args) {
        new HttpClientDemo().getResult();
    }

    public void getResult() {

        String url = "http://www.baidu.com";
        String urlParams = "id=1";
        JSONObject json = new JSONObject();
        json.put("id","1");
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("id","1"));

        try {
            httpSyncPost(url,list);
            httpSyncPost(url, json.toString());
            httpSyncGet(url,list);
            httpSyncGet(url,urlParams);

            httpAsyncPost(url, json.toString(), urlParams,  new AsyncHttpClientCallback());
            httpAsyncPost(url, list, list,  new AsyncHttpClientCallback());
            httpAsyncGet(url, urlParams,  new AsyncHttpClientCallback());
            httpAsyncGet(url, list,  new AsyncHttpClientCallback());
            OkAsyncGet(url);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}