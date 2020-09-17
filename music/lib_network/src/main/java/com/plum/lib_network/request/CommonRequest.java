package com.plum.lib_network.request;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.Key;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CommonRequest {

    public static Request createPostRequest(String url, RequestParams body) {
        return createPostRequest(url, body, null);
    }

    /**
     * 创建POST请求
     *
     * @param url
     * @param body
     * @param header
     * @return
     */
    public static Request createPostRequest(String url, RequestParams body, RequestParams header) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (body != null) {
            for (Map.Entry<String, String> entry : body.urlParams.entrySet()) {
                formBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        Headers.Builder headerBuilder = new Headers.Builder();
        if (header != null) {
            for (Map.Entry<String, String> entry : header.urlParams.entrySet()) {
                headerBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        FormBody formBody = formBuilder.build();
        Headers headers = headerBuilder.build();
        return new Request.Builder()
                .url(url)
                .post(formBody)
                .headers(headers)
                .build();
    }

    /**
     * 创建GET请求
     *
     * @param url
     * @param params
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params) {
        return createGetRequest(url, params, null);
    }

    public static Request createGetRequest(String url, RequestParams params, RequestParams header) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }

        Headers.Builder headerBuilder = new Headers.Builder();
        if (header != null) {
            for (Map.Entry<String, String> entry : header.urlParams.entrySet()) {
                headerBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        Headers headers = headerBuilder.build();
        return new Request.Builder()
                .url(urlBuilder.substring(0, urlBuilder.length() - 1))
                .get()
                .headers(headers)
                .build();
    }
}