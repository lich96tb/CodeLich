package com.melodymediation.sleepstories.service.evenbust;

public class MessegaeEventService {
    public boolean checkPlay;
    private int timeCurrent;

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

    public MessegaeEventService(boolean checkPlay, int timeCurrent) {
        this.checkPlay = checkPlay;
        this.timeCurrent = timeCurrent;
    }
}
