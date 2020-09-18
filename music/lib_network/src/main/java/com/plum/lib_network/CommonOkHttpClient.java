package com.plum.lib_network;

import com.plum.lib_network.listener.DisposeDataHandle;
import com.plum.lib_network.response.CommonFileCallback;
import com.plum.lib_network.response.CommonJsonCallback;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommonOkHttpClient {
    private static OkHttpClient sHttpClient;

    private static final int TIME_OUT = 30;

    static  {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        builder.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("User-Agent", "imooc").build();
                return chain.proceed(request);
            }
        });

        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.followRedirects(true);
        sHttpClient = builder.build();
    }


    public static Call get(Request request, DisposeDataHandle dataHandle) {
        Call call = sHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(dataHandle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle dataHandle) {
        Call call = sHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(dataHandle));
        return call;
    }

    public static Call downloadFile(Request request, DisposeDataHandle dataHandle) {
        Call call = sHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(dataHandle));
        return call;
    }
}
