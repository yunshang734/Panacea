package com.panacea.common.util;

import com.alibaba.fastjson.JSON;
import com.panacea.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OkHttpUtil {

    private final static MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();

    public static String doGet(String url) {
        String requestId = IdUtil.simpleUUID();
        Request request = initRequest(url, null, null, null);
        return doCall(requestId, request, url, null, null, null);
    }

    public static String doGet(String url, Map<String, String> headerMap) {
        String requestId = IdUtil.simpleUUID();
        Request request = initRequest(url, headerMap, null, null);
        log.info("RequestId: {}, OkHttpUtil.doGet({}, {}) start!", requestId, url, JSON.toJSON(headerMap));
        return doCall(requestId, request, url, headerMap, null, null);
    }

    public static String doPostWithFormData(String url, Map<String, String> parameterMap) {
        String requestId = IdUtil.simpleUUID();
        Request request = initRequest(url, null, parameterMap, null);
        log.info("RequestId: {}, OkHttpUtil.doPostWithFormData({}, {}) start!", requestId, url, JSON.toJSON(parameterMap));
        return doCall(requestId, request, url, null, parameterMap, null);
    }

    public static String doPostWithFormData(String url, Map<String, String> headerMap, Map<String, String> parameterMap) {
        String requestId = IdUtil.simpleUUID();
        Request request = initRequest(url, headerMap, parameterMap, null);
        log.info("RequestId: {}, OkHttpUtil.doPostWithFormData({}, {}, {}) start!", requestId, url, JSON.toJSON(headerMap), JSON.toJSON(parameterMap));
        return doCall(requestId, request, url, headerMap, parameterMap, null);
    }

    public static String doPostWithJson(String url, String json) {
        String requestId = IdUtil.simpleUUID();
        Request request = initRequest(url, null, null, json);
        log.info("RequestId: {}, OkHttpUtil.doPostWithFormData({}, {}) start!", requestId, url, json);
        return doCall(requestId, request, url, null, null, json);
    }

    public static String doPostWithJson(String url, Map<String, String> headerMap, String json) {
        String requestId = IdUtil.simpleUUID();
        Request request = initRequest(url, headerMap, null, json);
        log.info("RequestId: {}, OkHttpUtil.doPostWithFormData({}, {}, {}) start!", requestId, url, JSON.toJSON(headerMap), json);
        return doCall(requestId, request, url, headerMap, null, json);
    }

    /************************************************** Private Methods **************************************************/

    private static Request initRequest(String url, Map<String, String> headerMap, Map<String, String> parameterMap, String json) {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if (null != headerMap) {
            Headers.Builder headersBuilder = new Headers.Builder();
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headersBuilder.add(entry.getKey(), entry.getValue());
            }
            requestBuilder.headers(headersBuilder.build());
        }
        if (null != parameterMap) {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            if (null != parameterMap) {
                for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                    formBodyBuilder.add(entry.getKey(), entry.getValue());
                }
            }
            requestBuilder.post(formBodyBuilder.build());
        }
        if (null != json && !"".equals(json)) {
            RequestBody requestBody = FormBody.create(json, jsonMediaType);
            requestBuilder.post(requestBody);
        }
        return requestBuilder.build();
    }

    private static String doCall(String requestId, Request request, String url,
                                 Map<String, String> headerMap, Map<String, String> parameterMap, String json) {
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (200 != response.code()) {
                return CommonConstant.EMPTY_STRING;
            }
            String resultData = response.body().string();
            log.info("RequestId: {}, OkHttpUtil.doCall({}, {}, {}, {}) finish! Result data: {}",
                    requestId, url, JSON.toJSON(headerMap), JSON.toJSON(parameterMap), json, resultData);
            return resultData;
        } catch (IOException exception) {
            log.error("RequestId: {}, OkHttpUtil.doCall({}, {}, {}, {}) with Exception: ",
                    requestId, url, JSON.toJSON(headerMap), JSON.toJSON(parameterMap), json, exception);
            return CommonConstant.EMPTY_STRING;
        }
    }
}