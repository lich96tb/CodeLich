package com.melodymediation.sleepstories.service.evenbust;

public class MessageEvent {
    public String url;
    public String nameCategory;
    public String nameSong;
    public boolean checkPlay;
    public int timeCurrent;
    public int timeSong;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public boolean isCheckPlay() {
        return checkPlay;
    }

    public void setCheckPlay(boolean checkPlay) {
        this.checkPlay = checkPlay;
    }

    public int getTimeCurrent() {
        return timeCurrent;
    }

    public void setTimeCurrent(int timeCurrent) {
        this.timeCurrent = timeCurrent;
    }

    public int getTimeSong() {
        return timeSong;
    }

    public void setTimeSong(int timeSong) {
        this.timeSong = timeSong;
    }

    public MessageEvent(String url, String nameCategory, String nameSong, boolean checkPlay, int timeCurrent, int timeSong) {
        this.url = url;
        this.nameCategory = nameCategory;
        this.nameSong = nameSong;
        this.checkPlay = checkPlay;
        this.timeCurrent = timeCurrent;
        this.timeSong = timeSong;
    }

    public MessageEvent(String url) {
        this.url = url;
    }
}
