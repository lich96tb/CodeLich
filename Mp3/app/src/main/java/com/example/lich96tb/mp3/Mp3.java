package com.example.lich96tb.mp3;

import java.io.File;

public class Mp3 {
    private File name;

    public File getName() {
        return name;
    }

    public void setName(File name) {
        this.name = name;
    }

    public Mp3(File name) {
        this.name = name;
    }
}
