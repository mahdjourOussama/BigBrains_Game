package com.example.bigbrains_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.home_page);
        player =getIntent().getStringExtra("Player");
        ((TextView) findViewById(R.id.Home_Player_Name_txt)).setText(player);
    }
    private String player;

    public void Play_Pattern_Game(View v){
        Intent i = new Intent(this, Pattern_Game.class);
        i.putExtra("Player",player);
        startActivity(i);
    }
    public void Play_Card_Game(View v){
        Intent i = new Intent(this, Matching_Game.class);
        i.putExtra("Player",player);
        startActivity(i);
    }
    public void Play_Cup_Game(View v){
        Intent i = new Intent(this, Pattern_Game.class);
        i.putExtra("Player",player);
        startActivity(i);
    }

    public void Exit(View v){
        finishAffinity();
    }

    public void OpenCardScoreBoard(View v){
        Intent i = new Intent(this, Score_Board.class);
        i.putExtra("Game","Card");
        startActivity(i);
    }

    public void OpenCoinScoreBoard(View v){
        Intent i = new Intent(this, Score_Board.class);
        i.putExtra("Game","Coin");
        startActivity(i);
    }

    public void OpenPatternScoreBoard(View v){
        Intent i = new Intent(this, Score_Board.class);
        i.putExtra("Game","Pattern");
        startActivity(i);
    }

    public void LogOut(View v){
        finish();
    }

}
