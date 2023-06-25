package com.panacea.common.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OkHttpUtil {

    private final static String EMPTY_STRING = "";
    private final static String DEFAULT_CHARSET = "UTF-8";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doGet(String url, Map<String, String> headerMap) {
        Headers headers = initHeaders(headerMap);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .build();
        return doCall(request, url, headerMap, null);
    }

    public static String doPostWithFormData(String url, Map<String, String> parameterMap) {
        return doPostWithFormData(url, null, parameterMap);
    }

    public static String doPostWithFormData(String url, Map<String, String> headerMap, Map<String, String> parameterMap) {
        Headers headers = initHeaders(headerMap);
        FormBody formBody = initFormBody(parameterMap);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(formBody)
                .build();
        return doCall(request, url, headerMap, parameterMap);
    }

    /************************************************** Private Methods **************************************************/

    private static Headers initHeaders(Map<String, String> headerMap) {
        Headers.Builder builder = new Headers.Builder();
        if (null != headerMap) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }

    private static FormBody initFormBody(Map<String, String> parameterMap) {
        FormBody.Builder builder = new FormBody.Builder();
        if (null != parameterMap) {
            for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }

    private static String doCall(Request request, String url, Map<String, String> headerMap, Map<String, String> parameterMap) {
        try {
            return okHttpClient.newCall(request).execute().body().toString();
        } catch (IOException exception) {
            log.error("OkHttpUtil.doGet({}, {}, {}) with Exception: ",
                    url, JSON.toJSON(headerMap), JSON.toJSON(parameterMap), exception);
            return EMPTY_STRING;
        }
    }
}