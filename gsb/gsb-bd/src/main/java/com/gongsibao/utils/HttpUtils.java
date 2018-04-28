package com.gongsibao.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.List;

public class HttpUtils {
    public static String get(String url) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        String content = null;
        try {
            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String post(String url, List<NameValuePair> nvps) {
        String content = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        try {
            if (nvps != null) {
                post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }
            // 执行请求用execute方法，content用来帮我们附带上额外信息
            HttpResponse response = httpClient.execute(post);
            // 得到相应实体、包括响应头以及相应内容
            HttpEntity entity = response.getEntity();
            // 得到response的内容
            content = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
