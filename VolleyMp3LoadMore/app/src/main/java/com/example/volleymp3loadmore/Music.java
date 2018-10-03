package com.example.volleymp3loadmore;

public class Music {
    private String album_title, artistname, songPic;
    private int songid;
    private String songname, versions;

    public String getAlbum_title() {
        return album_title;
    }

    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public String getSongPic() {
        return songPic;
    }

    public void setSongPic(String songPic) {
        this.songPic = songPic;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public Music(String album_title, String artistname, String songPic, int songid, String songname, String versions) {
        this.album_title = album_title;
        this.artistname = artistname;
        this.songPic = songPic;
        this.songid = songid;
        this.songname = songname;
        this.versions = versions;
    }
}
