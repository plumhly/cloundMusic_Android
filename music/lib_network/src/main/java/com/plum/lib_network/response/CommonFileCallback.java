package com.plum.lib_network.response;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.plum.lib_network.exception.OkHttpException;
import com.plum.lib_network.listener.DisposeDataHandle;
import com.plum.lib_network.listener.DisposeDownloadListener;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CommonFileCallback implements Callback {

    protected final String EMPTY_MSG = "";

    protected final int NETWORK_ERROR = -1;
    protected final int IO_ERROR = -2;

    private static final int PROGRESS_MESSAGE = 1;

    private Handler mHandler;
    private DisposeDownloadListener mDownloadListener;
    private String mFilePath;

    private int mProgress;

    public CommonFileCallback(DisposeDataHandle handle) {
        mDownloadListener = (DisposeDownloadListener) handle.mListener;
        mFilePath = handle.mFilePath;
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case PROGRESS_MESSAGE:
                        mDownloadListener.onProgress((int)msg.obj);
                        break;
                }
            }
        };
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mDownloadListener.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        final File file = handleResponse(response);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (file == null) {
                    mDownloadListener.onFailure(new OkHttpException(IO_ERROR, EMPTY_MSG));
                    return;
                } else {
                    mDownloadListener.onSuccess(file);
                }
            }
        });
    }

    private File handleResponse(Response response) {
        if (response == null) {
            return null;
        }

        InputStream inputStream = null;
        File file = null;
        FileOutputStream fos = null;
        byte[] bytes = new byte[2048];
        int length;
        double totalLength;
        int currentLength = 0;

        try {
            checkLocalFilePath(mFilePath);
            file = new File(mFilePath);
            fos = new FileOutputStream(file);
            inputStream = response.body().byteStream();
            totalLength = response.body().contentLength();
            while ((length = inputStream.read(bytes)) != -1) {
                fos.write(bytes);
                currentLength += length;
                mProgress = (int) (currentLength / totalLength * 100);
                mHandler.obtainMessage(PROGRESS_MESSAGE, mProgress).sendToTarget();
            }
            fos.flush();
        } catch (Exception e) {
            file = null;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private void checkLocalFilePath(String localFilePath) {
        File path = new File(mFilePath.substring(0, mFilePath.lastIndexOf("/") + 1));
        File localFile = new File(mFilePath);
        if (!path.exists()) {
            path.mkdir();
        }
        if (!localFile.exists()) {
            try {
                localFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
