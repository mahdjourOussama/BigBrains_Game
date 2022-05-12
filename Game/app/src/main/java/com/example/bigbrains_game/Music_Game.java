package com.example.bigbrains_game;

import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Music_Game extends AppCompatActivity {
    public List <Integer> SequenceExpected = new ArrayList<Integer>() ,
            SequenceEntred = new ArrayList<Integer>(),
            test =new ArrayList<Integer>();
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.music_game_page);
           for (int i=0;i<5;i++)
               test.add(i);
    }

    public void AppendRandom(){
        int randomNumber = (int) (Math.random()*24);
        boolean exist=false;
        for (int i:
             SequenceExpected) {
            if (randomNumber==i) {
                exist = true;
                break;
            }
        }
        if(!exist  )
            SequenceExpected.add(randomNumber);
        else if(SequenceExpected.size()<24) AppendRandom();

    }
    public void GameLogic(Button btn){
        if(GameSystemTest()){
            btn.setBackgroundResource(R.drawable.music_game_btn_error);
        }else{

        }
    }
    public boolean GameSystemTest(){
           boolean GameOver =false;
           for (int i =1;i< SequenceEntred.size();i++){
               if(SequenceExpected.get(i)!=SequenceEntred.get(i)){
                   GameOver=true;
               }
           }
           return GameOver;
    }
    public void EnterSequence(View v){
        Button btn = (Button) findViewById(v.getId());
        btn.setBackgroundResource(R.drawable.music_game_btn_active);
        String btn_ID_String=getResources().getResourceName(v.getId()).replace(getPackageName()+":id/button","");
        int btn_ID =Integer.parseInt(btn_ID_String);
        SequenceEntred.add(btn_ID);
        GameLogic(btn);
    }
    public synchronized void FlashBtn(@NonNull Button btn){
        btn.setBackgroundResource(R.drawable.music_game_btn_active);
        new android.os.Handler(Looper.getMainLooper()).postDelayed(
                        new Runnable() {
                            public void run() {
                                btn.setBackgroundResource(R.drawable.music_game_btn);
                            }
                        },
                        1000);

    }
    public synchronized void  DisplaySequence(View v){
           //AppendRandom();
        for (int i=0; i<  test.size();i++) {
            TextView t= (TextView) findViewById(R.id.Score_txt);
            int resID = getResources().getIdentifier("button"+(test.get(i)+1),
                    "id", getPackageName());
            Button btn = (Button) findViewById(resID);

            if(btn != null) {
                FlashBtn(btn);
            }
        }
    }
}
