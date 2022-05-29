package com.example.bigbrains_game;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Score_Board extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.score_board);
        HSOps=new HighScoreOps(getApplicationContext());
        fill();
    }

    private HighScoreOps HSOps;

    public void fill(){
        String gameName =getIntent().getStringExtra("Game");
        ((TextView) findViewById(R.id.Game_Name_txt)).setText(gameName+" Game ScoreBoard");

        ListView list=(ListView)findViewById(R.id.Score_List);

        ArrayList<String> listData = HSOps.getAllScores(gameName);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
        list.setAdapter(arrayAdapter);
    }

    public void exit(View v){
        finish();
    }
}
