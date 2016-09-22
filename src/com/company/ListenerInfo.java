package com.company;

import java.util.ArrayList;

/**
 * Created by john.tumminelli on 9/21/16.
 */
public class ListenerInfo {
    String firstName;
    String lastName;
    boolean willBuy;
    boolean notify;
    String artist;
    String album;
    ArrayList<String> genres = new ArrayList<>();

//    default constructor for java parser library
    public ListenerInfo(){

    }

    public ListenerInfo(String firstName, String lastName, boolean willBuy, boolean notify, String artist, String album, ArrayList<String> genres, String listenerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.willBuy = willBuy;
        this.notify = notify;
        this.artist = artist;
        this.album = album;
        this.genres = genres;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isWillBuy() {
        return willBuy;
    }

    public boolean isNotify() {
        return notify;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

}
