package com.plum.lib_network.listener;

public interface DisposeDownloadListener extends DisposeDataListener {
    void onProgress(int progress);
}
