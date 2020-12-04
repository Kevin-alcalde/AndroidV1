package edu.upc.dsa.androidproyecto;

import java.io.Serializable;

public class Track implements Serializable {

    private String id;
    private String singer;
    private  String title;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Track(String id, String singer, String title) {
        this.id = id;
        this.singer = singer;
        this.title = title;
    }

    public Track() {
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id='" + id + '\'' +
                ", singer='" + singer + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}


