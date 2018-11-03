package com.example.admin.doawloadlistimage.thuvien.listener;


import com.example.admin.doawloadlistimage.thuvien.pojo.FileResponse;
import com.example.admin.doawloadlistimage.thuvien.request.FileLoadRequest;

/**
 * Created by krishna on 12/10/17.
 */

public abstract class FileRequestListener<T> {
    public void onStatusChange(int status) {

    }

    public abstract void onLoad(FileLoadRequest request, FileResponse<T> response);

    public abstract void onError(FileLoadRequest request, Throwable t);
}
