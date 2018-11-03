package com.melodymediation.sleepstories.librarydloadimage.listener;


import com.melodymediation.sleepstories.librarydloadimage.pojo.FileResponse;
import com.melodymediation.sleepstories.librarydloadimage.request.FileLoadRequest;

/**
 * Created by krishna on 12/10/17.
 */

public abstract class FileRequestListener<T> {
    public void onStatusChange(int status) {

    }

    public abstract void onLoad(FileLoadRequest request, FileResponse<T> response);

    public abstract void onError(FileLoadRequest request, Throwable t);
}
