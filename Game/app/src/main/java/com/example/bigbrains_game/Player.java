package com.example.bigbrains_game;

public class Player {
    //-------------------------variables------------------------------------------------------------

    private String Name,Password,Email;

    //----------------------------------------------------------------------------------------------

    //-------------------constructors---------------------------------------------------------------
    public Player(String name, String password , String email){
        this.Email=email;
        this.Name=name;
        this.Password=password;
    }

    public Player(String name, String password ){
        this.Name=name;
        this.Password=password;
    }
    //----------------------------------------------------------------------------------------------

    //-------------------Getter methods-------------------------------------------------------------
    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
    //----------------------------------------------------------------------------------------------
}
