package com.example.bigbrains_game;

import android.widget.Button;

public class Pattern_Btn {

    private int ID, ColorID =0;
    private Button BtnID;
    public Pattern_Btn(int ID, int Color, Button btnID){
        this.ColorID =Color;
        this.ID=ID;
        this.BtnID=btnID;
    }
    public Pattern_Btn(int ID, Button btnID){
        this.ID=ID;
        this.BtnID=btnID;
    }

    public void setColorID(int colorID) {
        ColorID = colorID;
    }

    public void nextColor(){
        if(ColorID <5){
            ColorID++;
        }else ColorID =0;
    }

    public int getID() {
        return ID;
    }

    public Button getBtn() {
        return BtnID;
    }

    public int getColorID() {
        return ColorID;
    }
    public void ResetBtn(){
        this.ColorID=0;
    }

}
