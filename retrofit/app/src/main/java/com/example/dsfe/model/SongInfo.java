
package com.example.dsfe.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SongInfo {

    @SerializedName("songList")
    private List<SongList> mSongList;

    public List<SongList> getSongList() {
        return mSongList;
    }

    public void setSongList(List<SongList> songList) {
        mSongList = songList;
    }

}
