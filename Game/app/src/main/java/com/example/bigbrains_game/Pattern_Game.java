package com.example.bigbrains_game;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Pattern_Game extends AppCompatActivity {
    //-----------------------Start Variable Declaration---------------------------------------------
    public List <PattrenBtn> SequenceExpected = new ArrayList<PattrenBtn>() ,
            SequenceEntered = new ArrayList<PattrenBtn>();
    public double Score =0;
    public  int GameLevel =2;
    //-----------------------End Variable Declaration-----------------------------------------------
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.pattern_game_page);

        TextView level_txt= (TextView) findViewById(R.id.level_txt);
        level_txt.setText("Level "+GameLevel);
        setUp();
    }

    //-----------------------Start Setters Methods--------------------------------------------------
    public void updateLevel(){
        TextView t= (TextView) findViewById(R.id.level_txt);
        t.setText("Level "+GameLevel);
    }

    public void updateScore(){
        TextView t= (TextView) findViewById(R.id.Score_txt);
        t.setText(String.valueOf(Score));
    }
    //-----------------------End Setters Methods----------------------------------------------------

    //-----------------------Start Getters Methods--------------------------------------------------
    public int GetLevel(){
        TextView t= (TextView) findViewById(R.id.level_txt);
        return Integer.parseInt(((String) t.getText()).replace("Level ",""));
    }

    public double GetScore(){
        TextView t= (TextView) findViewById(R.id.Score_txt);
        return Double.parseDouble((String) t.getText());
    }

    public int getID(String type,String name){
        return  getResources().getIdentifier(name,type, getPackageName());
    }
    //-----------------------End Getters Methods----------------------------------------------------

    //-----------------------Start Game Logic Methods-----------------------------------------------


    public void LevelUP(View v){
        GameLevel= GetLevel()+1;
        updateLevel();
    }

    public void LevelDOWN(View v){
        if(GameLevel>0){
            GameLevel = GetLevel() - 1;
            updateLevel();
        }else{
            Toast.makeText(getApplicationContext(),"Minimal Level",Toast.LENGTH_LONG).show();
        }
    }

    public void AppendRandom(){
        int randomNumber = (int) (Math.random()*24);
        int randomColor = (int)(Math.random()*5);
        boolean exist=false;
        for (PattrenBtn i:
                SequenceExpected) {
            if (randomNumber==i.getID() && randomColor ==i.getColorID()) {
                exist = true;
                break;
            }
        }
        if(!exist) {
            SequenceExpected.get(randomNumber).setColorID(randomColor);
        }
        else if(SequenceExpected.size()<24) AppendRandom();
    }

    public boolean GameSystemTest(){
        for (int i = 0; i< SequenceEntered.size(); i++){
            if(SequenceExpected.get(i).getBtn() ==SequenceEntered.get(i).getBtn()
                && SequenceExpected.get(i).getColorID() != SequenceEntered.get(i).getColorID()) {
                SequenceEntered.get(i).getBtn().setBackgroundResource(R.drawable.pattern_game_btn_error);
                SequenceEntered.get(i).getBtn().setText("Error");
                new android.os.Handler(Looper.getMainLooper()).postDelayed(
                        new Runnable() {
                            public void run() {
                                DisplaySequence();
                            }
                        },
                        3000);
                return true;
            }


        }
        return false;
    }

    public void EnterSequence(View v){
        Button btn = (Button) findViewById(v.getId());
        PattrenBtn Pbtn =SequenceEntered.get(0) ;
        for (PattrenBtn b:
             SequenceEntered) {
            if(b.getBtn()==btn){
                Pbtn= b;
                break;
            }
        }

        Pbtn.nextColor();
        DisplayBtnColor(Pbtn);

    }

    public synchronized void FlashBtn(@NonNull Button btn,int colorID){

        btn.setBackgroundResource(colorID);

        new android.os.Handler(Looper.getMainLooper()).postDelayed(
                new Runnable() {
                    public void run() {
                        btn.setBackgroundResource(R.drawable.pattern_game_btn_0);
                    }
                },
                3000);

    }

    public void restartGame (View v){
        clearDisplay();
        setUp();
        GameStatus(true);
        CreateSequence();
    }

    public void clearDisplay(){
        for (PattrenBtn btn : SequenceExpected){
            btn.getBtn().setBackgroundResource(R.drawable.pattern_game_btn_0);
            btn.getBtn().setText("");
        }
    }

    public void DisplayBtnColor(PattrenBtn BTN){
        Button btn = BTN.getBtn();
        int colorID = getID("drawable","pattern_game_btn_"+BTN.getColorID());
        btn.setBackgroundResource(colorID);
    }

    public synchronized void  DisplaySequence(){

        clearDisplay();
        for (int i=0; i<SequenceExpected.size();i++) {

            int colorNum =SequenceExpected.get(i).getColorID();

            int colorID = getID("drawable","pattern_game_btn_"+colorNum);

            Button btn = SequenceExpected.get(i).getBtn();

            if(btn != null) {
                FlashBtn(btn,colorID);
            }
        }

    }

    public void CreateSequence(){
        GameLevel = GetLevel();
        setUp();
        for (int i =0;i<GameLevel;i++){
            AppendRandom();
        }
        DisplaySequence();
    }

    public void CalculateScore(){
        Score =(GetScore()+GetLevel()*SequenceExpected.size());
    }

     public void ValidateSequence(View v){
         if(GameSystemTest())
            Toast.makeText(getApplicationContext(),"Wrong Pattern",Toast.LENGTH_LONG).show();
         else {
             CalculateScore();
             Toast.makeText(getApplicationContext(),"Congratulation",Toast.LENGTH_LONG).show();
             updateScore();
             GameStatus(false);
         }
     }

     public void GameStatus(boolean status){
         for (PattrenBtn btn :
                 SequenceExpected) {
             btn.getBtn().setEnabled(status);
         }
     }
     public void setUp(){
         SequenceExpected.clear();
         SequenceEntered.clear();
           for (int i=1;i<25;i++){
               int btnID = getID("id","button"+i);
               PattrenBtn btn = new PattrenBtn(i,(Button) findViewById(btnID));
               SequenceExpected.add(btn);
           }
         for (int i=1;i<25;i++){
             int btnID = getID("id","button"+i);
             PattrenBtn btn = new PattrenBtn(i,(Button) findViewById(btnID));
             SequenceEntered.add(btn);
         }
     }

    //-----------------------End Game Logic Methods-------------------------------------------------







}
