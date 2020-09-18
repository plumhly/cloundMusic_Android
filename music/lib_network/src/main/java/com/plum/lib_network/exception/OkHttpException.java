package com.plum.lib_network.exception;

public class OkHttpException {
    private int mCode;

    private Object mMsg;

    public OkHttpException(int code, Object msg) {
        this.mCode = code;
        this.mMsg = msg;
    }

    public int getCode() {
        return mCode;
    }

    public Object getMsg() {
        return mMsg;
    }
}
