
package com.example.dsfe.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class SongList {

    @SerializedName("album_title")
    private String mAlbumTitle;
    @SerializedName("artistname")
    private String mArtistname;
    @SerializedName("songPic")
    private String mSongPic;
    @SerializedName("songid")
    private String mSongid;
    @SerializedName("songname")
    private String mSongname;
    @SerializedName("versions")
    private String mVersions;

    public String getAlbumTitle() {
        return mAlbumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        mAlbumTitle = albumTitle;
    }

    public String getArtistname() {
        return mArtistname;
    }

    public void setArtistname(String artistname) {
        mArtistname = artistname;
    }

    public String getSongPic() {
        return mSongPic;
    }

    public void setSongPic(String songPic) {
        mSongPic = songPic;
    }

    public String getSongid() {
        return mSongid;
    }

    public void setSongid(String songid) {
        mSongid = songid;
    }

    public String getSongname() {
        return mSongname;
    }

    public void setSongname(String songname) {
        mSongname = songname;
    }

    public String getVersions() {
        return mVersions;
    }

    public void setVersions(String versions) {
        mVersions = versions;
    }

}
