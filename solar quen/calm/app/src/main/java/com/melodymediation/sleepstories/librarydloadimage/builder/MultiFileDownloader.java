package com.melodymediation.sleepstories.librarydloadimage.builder;

import android.content.Context;

import com.melodymediation.sleepstories.librarydloadimage.FileLoader;
import com.melodymediation.sleepstories.librarydloadimage.MultiFileDownloadTask;
import com.melodymediation.sleepstories.librarydloadimage.listener.MultiFileDownloadListener;
import com.melodymediation.sleepstories.librarydloadimage.request.MultiFileLoadRequest;
import com.melodymediation.sleepstories.librarydloadimage.utility.Utils;

import java.util.List;


/**
 * Created by krishna on 15/10/17.
 */

public class MultiFileDownloader {
    private Context context;
    private String directoryName = FileLoader.DEFAULT_DIR_NAME;
    private int directoryType = FileLoader.DEFAULT_DIR_TYPE;

    private MultiFileDownloadListener listener;
    private boolean forceLoadFromNetwork;
    private boolean autoRefresh;
    private boolean checkIntegrity;
    private MultiFileDownloadTask multiFileDownloadTask;

    public MultiFileDownloader(Context context) {
        this.context = context;
    }

    public MultiFileDownloader(Context context, boolean autoRefresh) {
        this.context = context;
        this.autoRefresh = autoRefresh;
    }

    public MultiFileDownloader fromDirectory(String directoryName, @FileLoader.DirectoryType int directoryType) {
        this.directoryName = directoryName;
        this.directoryType = directoryType;
        return this;
    }

    public MultiFileDownloader progressListener(MultiFileDownloadListener listener) {
        this.listener = listener;
        return this;
    }

    public MultiFileDownloader checkFileintegrity(boolean checkIntegrity) {
        this.checkIntegrity = checkIntegrity;
        return this;
    }

    public void loadMultiple(String... uris) {
        MultiFileLoadRequest[] loadRequestArr = new MultiFileLoadRequest[uris.length];
        for (int i = 0; i < uris.length; i++) {
            MultiFileLoadRequest loadRequest = new MultiFileLoadRequest(uris[i], directoryName, directoryType, forceLoadFromNetwork);
            loadRequest.setAutoRefresh(autoRefresh);
            loadRequest.setCheckIntegrity(checkIntegrity);
            loadRequestArr[i] = loadRequest;
        }
        multiFileDownloadTask = new MultiFileDownloadTask(context, listener);
        multiFileDownloadTask.executeOnExecutor(Utils.getThreadPoolExecutor(), loadRequestArr);
    }

    public void loadMultiple(boolean forceLoadFromNetwork, String... uris) {
        this.forceLoadFromNetwork = forceLoadFromNetwork;
        loadMultiple(uris);
    }

    public void loadMultiple(boolean forceLoadFromNetwork, List<MultiFileLoadRequest> multiFileLoadRequestList) {
        this.forceLoadFromNetwork = forceLoadFromNetwork;
        MultiFileLoadRequest[] loadRequestArr = new MultiFileLoadRequest[multiFileLoadRequestList.size()];
        for (int i = 0; i < multiFileLoadRequestList.size(); i++) {
            loadRequestArr[i] = multiFileLoadRequestList.get(i);
            loadRequestArr[i].setAutoRefresh(autoRefresh);
            loadRequestArr[i].setCheckIntegrity(checkIntegrity);
        }
        multiFileDownloadTask = new MultiFileDownloadTask(context, listener);
        multiFileDownloadTask.executeOnExecutor(Utils.getThreadPoolExecutor(), loadRequestArr);
    }

    public void loadMultiple(List<MultiFileLoadRequest> multiFileLoadRequestList) {
        loadMultiple(false, multiFileLoadRequestList);
    }

    public void cancelLoad() {
        if (multiFileDownloadTask != null)
            multiFileDownloadTask.cancel(true);
    }
}
