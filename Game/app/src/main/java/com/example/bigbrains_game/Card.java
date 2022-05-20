package com.example.bigbrains_game;

import android.widget.Button;

public class Card {

    //----------------- Variables ------------------------------------------------------------------
    private int id, image,imageID;
    private Button btnID;
    private Card twin;
    private boolean cardHasTwin =false;
    //----------------------------------------------------------------------------------------------

    //----------------- Constructor Methods---------------------------------------------------------

    public Card(int id,Button btnID,int image,int imageID) {
        this.id = id;
        this.btnID=btnID;
        this.image =image;
        this.imageID=imageID;
    }

    public Card(int id,Button btnID, int image ,int imageID,Card twin) {
        this.id = id;
        this.btnID=btnID;
        this.image =image;
        this.twin=twin;
        this.imageID=imageID;
    }

    //----------------------------------------------------------------------------------------------

    //----------------- Getter Methods--------------------------------------------------------------
    public int getId() {
        return id;
    }

    public Card getTwin() {
        return twin;
    }

    public Button getBtnID() {
        return btnID;
    }

    public boolean checkTwin(Card twin){
        if(this.twin==twin) return true;
        else return false;
    }

    public int getImage() {
        return image;
    }

    public int getImageID() {
        return imageID;
    }
    //----------------------------------------------------------------------------------------------

    //----------------- Setter Methods--------------------------------------------------------------

    public void setBtnID(Button id) {
        this.btnID = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTwin(Card twin) {
        if (!cardHasTwin) {
            cardHasTwin=true;
            this.twin = twin;
            twin.setTwin(this);
        }
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    //----------------------------------------------------------------------------------------------
}
