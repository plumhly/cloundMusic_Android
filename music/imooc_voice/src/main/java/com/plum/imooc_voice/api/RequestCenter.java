package com.plum.imooc_voice.api;

import com.plum.imooc_voice.model.user.User;
import com.plum.lib_network.CommonOkHttpClient;
import com.plum.lib_network.listener.DisposeDataHandle;
import com.plum.lib_network.listener.DisposeDataListener;
import com.plum.lib_network.request.CommonRequest;
import com.plum.lib_network.request.RequestParams;

import java.net.URL;

import okhttp3.Request;

public class RequestCenter {
    static class RequestConstant {
        private static final String ROOT_URL = "http://imooc.com/api";

        /**
         * 首页请求接口
         */
        private static String HOME_RECOMMEND = ROOT_URL + "/module_voice/home_recommand";
        private static String HOME_RECOMMEND_MORE = ROOT_URL + "/module_voice/home_recommand_more";
        private static String HOME_FRIEND = ROOT_URL + "/module_voice/home_friend";

        /**
         * 登录接口
         */
        private static String LOGIN = ROOT_URL + "/module_voice/login_phone";
    }

    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> aClass) {
        Request request = CommonRequest.createGetRequest(url, params);
        CommonOkHttpClient.post(request, new DisposeDataHandle(listener, aClass));
    }


    /**
     * 登录接口
     */

    public static void login(DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("mb", "18980620405");
        params.put("pwd", "dddddd");
        RequestCenter.postRequest(RequestConstant.LOGIN, params, listener, User.class);
    }
}
