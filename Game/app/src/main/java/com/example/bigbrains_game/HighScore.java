package com.example.bigbrains_game;

import java.io.Serializable;

public class HighScore implements Serializable {

    private String player;
    private String Game;
    private int Score=0;

    public HighScore(String User,String Game,int Score){
        this.Game=Game;
        this.player =User;
        this.Score=Score;
    }

    public HighScore(String User,String Game){
        this.Game=Game;
        this.player =User;
    }

    public int getScore() {
        return Score;
    }

    public String getPlayer() {
        return player;
    }

    public String getGame() {
        return Game;
    }

    public void setScore(int score) {
        Score = score;
    }


}
