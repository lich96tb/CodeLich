
package com.example.dsfe.model;
import com.google.gson.annotations.SerializedName;
@SuppressWarnings("unused")
public class Result {

    @SerializedName("result")
    private Result mResult;
    @SerializedName("songInfo")
    private SongInfo mSongInfo;

    public Result getResult() {
        return mResult;
    }

    public void setResult(Result result) {
        mResult = result;
    }

    public SongInfo getSongInfo() {
        return mSongInfo;
    }

    public void setSongInfo(SongInfo songInfo) {
        mSongInfo = songInfo;
    }

}
