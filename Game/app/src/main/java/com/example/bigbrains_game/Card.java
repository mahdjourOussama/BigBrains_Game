package com.example.bigbrains_game;

public class Card {
    private int id,btnID,imageID;
    private Card twin;

    public Card(int id,int btnID,int imageID) {
        this.id = id;
        this.btnID=btnID;
        this.imageID=imageID;
    }

    public Card(int id,int btnID, int imageID ,Card twin) {
        this.id = id;
        this.btnID=btnID;
        this.imageID=imageID;
        this.twin=twin;
    }

    public int getId() {
        return id;
    }

    public Card getTwin() {
        return twin;
    }

    public int getBtnID() {
        return btnID;
    }

    public void setBtnID(int id) {
        this.btnID = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTwin(Card twin) {
        this.twin = twin;
        twin.setTwin(this);
    }

    public boolean checkTwin(Card twin){
        if(this.twin==twin) return true;
        else return false;
    }
}
