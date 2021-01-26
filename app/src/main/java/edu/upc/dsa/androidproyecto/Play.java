package edu.upc.dsa.androidproyecto;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Play {
    String id;
    String positionX;
    String positionY;
    String totalKills;
    String total;
    String time;

    //hay que recordar que una partida tendrá una serie de mapas
    //como no habrá muchos mapas en el juego nos hemos decidido por una LinkedList
    List<Map> mapsByPlay;

    public Play() {
    }
    public Play(String id, String positionX, String positionY) {
        this.id = id;
        this.positionX = positionX;
        this.positionY = positionY;
        this.mapsByPlay = new LinkedList<>();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    public String getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(String totalKills) {
        this.totalKills = totalKills;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
