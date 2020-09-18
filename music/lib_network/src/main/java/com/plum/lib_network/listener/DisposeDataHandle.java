package com.plum.lib_network.listener;

public class DisposeDataHandle {

    public DisposeDataListener mListener;
    public Class<?> mClass;
    public String mFilePath;

    public DisposeDataHandle(DisposeDataListener listener) {
        mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> aClass) {
        mListener = listener;
        mClass = aClass;
    }

    public DisposeDataHandle(DisposeDataListener listener, String downloadPath) {
        mListener = listener;
        mFilePath = downloadPath;
    }

}
