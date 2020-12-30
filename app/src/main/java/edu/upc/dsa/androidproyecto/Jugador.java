package edu.upc.dsa.androidproyecto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Jugador implements Serializable {

    private String id;
    private String username;
    private String mail;
    private String name;
    private String lastname;
    private String city;
    private String password;
    private String life;
    private String level;
    private int level1;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLevel1() {
        return level1;
    }

    public void setLevel1(int level1) {
        this.level1 = level1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Jugador(String username, String password, String mail, String name, String lastname, String city) {
        this.username = username;
        this.mail = mail;
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.password = password;
    }

    public Jugador(String username, String mail, String name, String lastname, String city, String password, int level1) {
        this.username = username;
        this.mail = mail;
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.password = password;
        this.level1 = level1;
    }

    public Jugador(String id, String username, String mail, String name, String lastname, String city, String password, String life, String level) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.password = password;
        this.life = life;
        this.level = level;
    }

    public Jugador(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Jugador() {
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", password='" + password + '\'' +
                ", life='" + life + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}


