package com.webteq.guesstheinstruments.Models;


/**
 * Created by user on 19/01/2017.
 */

public class PlayersModel  {

    private String player_name;

    private String player_score;

    private String date_played;

    public PlayersModel(String player_name, String player_score, String date_played) {
        this.player_name = player_name;
        this.player_score = player_score;
        this.date_played = date_played;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_score() {
        return player_score;
    }

    public void setPlayer_score(String player_score) {
        this.player_score = player_score;
    }

    public String getDate_played() {
        return date_played;
    }

    public void setDate_played(String date_played) {
        this.date_played = date_played;
    }
}
