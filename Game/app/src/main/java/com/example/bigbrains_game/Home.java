package com.example.bigbrains_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.home_page);
    }
    public void Play_Pattern_Game(View v){
        Intent i = new Intent(this, Pattern_Game.class);
        startActivity(i);
    }
    public void Play_Card_Game(View v){
        Intent i = new Intent(this, Pattern_Game.class);
        startActivity(i);
    }
    public void Play_Cup_Game(View v){
        Intent i = new Intent(this, Pattern_Game.class);
        startActivity(i);
    }
    public void Play_4_Game(View v){
        Intent i = new Intent(this, Pattern_Game.class);
        startActivity(i);
    }
    public void Exit(View v){

    }
    public void LogOut(View v){
        Intent i = new Intent(this,StartPage.class);
        startActivity(i);
    }
}
