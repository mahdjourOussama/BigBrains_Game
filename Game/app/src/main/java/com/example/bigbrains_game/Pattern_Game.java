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
    public List <Pattern_Btn> SequenceExpected = new ArrayList<Pattern_Btn>() ,
            SequenceEntered = new ArrayList<Pattern_Btn>();
    public int Score =0, GameLevel =2 ,Lives=3;
    private String player;
    private HighScoreOps HSOps;
    //-----------------------End Variable Declaration-----------------------------------------------
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.pattern_game_page);
        HSOps=new HighScoreOps(getApplicationContext());
        updateLevel();
        setUp();

           player =getIntent().getStringExtra("Player");
           TextView t1=(TextView) findViewById(R.id.Pattern_Game_Player_Name);
           if(t1!=null)  t1.setText(player);
           else Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG);
    }

    //-----------------------Start Setters Methods--------------------------------------------------
    public void updateLevel(){
        TextView t= (TextView) findViewById(R.id.Pattern_Game_Level_txt);
        t.setText("Level "+GameLevel);
    }

    public void updateLives(){
        TextView t= (TextView) findViewById(R.id.Pattern_Game_Lives_txt);
        t.setText("Lives : "+Lives);
    }

    public void updateScore(){
        TextView t= (TextView) findViewById(R.id.Pattern_Game_Score_txt);
        t.setText(String.valueOf(Score));
    }
    //-----------------------End Setters Methods----------------------------------------------------

    //-----------------------Start Getters Methods--------------------------------------------------
    public int GetLevel(){
        TextView t= (TextView) findViewById(R.id.Pattern_Game_Level_txt);
        return Integer.parseInt(((String) t.getText()).replace("Level ",""));
    }

    public int GetLives(){
        TextView t= (TextView) findViewById(R.id.Pattern_Game_Lives_txt);
        return Integer.parseInt(((String) t.getText()).replace("Lives : ",""));
    }

    public int GetScore(){
        TextView t= (TextView) findViewById(R.id.Pattern_Game_Score_txt);
        return Integer.parseInt((String) t.getText());
    }

    public int getID(String type,String name){
        return  getResources().getIdentifier(name,type, getPackageName());
    }
    //-----------------------End Getters Methods----------------------------------------------------

    //-----------------------Start Game Logic Methods-----------------------------------------------

    public  void GameOver(){
        GameStatus(false);
        SaveScore();
   }

    public void SaveScore(){
       Score=GetScore();
       HighScore score = new HighScore(player,"Pattern",Score);
       if(HSOps.addHighScore(score)) Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_LONG);
       else Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG);
   }

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
        for (Pattern_Btn i:
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
                if(Lives <1){
                    GameOver();
                    return true;
                }else{
                    Lives=GetLives()-1;
                    updateLives();
                }
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
        Pattern_Btn Pbtn =SequenceEntered.get(0) ;
        for (Pattern_Btn b:
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
        NewGame();
        GameStatus(true);
        CreateSequence();
    }

    public void clearDisplay(){
        for (Pattern_Btn btn : SequenceExpected){
            btn.getBtn().setBackgroundResource(R.drawable.pattern_game_btn_0);
            btn.getBtn().setText("");
        }
    }

    public void DisplayBtnColor(Pattern_Btn BTN){
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
        NewGame();
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
         for (Pattern_Btn btn :
                 SequenceExpected) {
             btn.getBtn().setEnabled(status);
         }
     }

     public void setUp(){
         updateLives();
         updateLevel();
         updateScore();
         NewGame();
     }

     public void NewGame(){
         SequenceExpected.clear();
         SequenceEntered.clear();

         for (int i=1;i<25;i++){
             int btnID = getID("id","button"+i);
             Pattern_Btn btn = new Pattern_Btn(i,(Button) findViewById(btnID));
             SequenceExpected.add(btn);
         }
         for (int i=1;i<25;i++){
             int btnID = getID("id","button"+i);
             Pattern_Btn btn = new Pattern_Btn(i,(Button) findViewById(btnID));
             SequenceEntered.add(btn);
         }
     }
    //-----------------------End Game Logic Methods-------------------------------------------------







}
