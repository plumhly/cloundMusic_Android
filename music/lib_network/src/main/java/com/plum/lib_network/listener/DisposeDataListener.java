package com.plum.lib_network.listener;


public interface DisposeDataListener {

    void onSuccess(Object responseObj);

    void onFailure(Object reasonObj);
}
