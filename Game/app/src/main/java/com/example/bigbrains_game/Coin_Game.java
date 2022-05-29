package com.example.bigbrains_game;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Coin_Game extends AppCompatActivity {

                                            /*              Declarations                */

    private String player;
    private HighScoreOps HSOps;


    ImageView follow_card_id, left_card_id, middle_card_id, right_card_id;
    TextView score_id;
    ImageButton level_up, level_down;
    ImageButton restart_button;
    ImageButton exit_btn;

    ArrayList<Integer> card_image;

    ArrayList<Integer> t1;
    ArrayList<Integer> t2;
    ArrayList<Integer> t3;

    Button go_button;

    int n = 0; // for go button
    int found = 0; // for score
    int sc = 1;
    int current_level = 1; // for update level & get level


                                                /*                  OnCreate                 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.find_the_coin_game);

        player =getIntent().getStringExtra("Player");
        TextView Coin_Game_Player_Name=(TextView) findViewById(R.id.Coin_Game_Player_Name);
        if(Coin_Game_Player_Name!=null)  Coin_Game_Player_Name.setText(player);
        else Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG);

        HSOps=new HighScoreOps(getApplicationContext());


        /*                  Find View                   */

        follow_card_id = (ImageView) findViewById(R.id.follow_card_id);
        left_card_id = (ImageView) findViewById(R.id.left_card_id);
        middle_card_id = (ImageView) findViewById(R.id.middle_card_id);
        right_card_id = (ImageView) findViewById(R.id.right_card_id);

        score_id = (TextView) findViewById(R.id.score_id);

        go_button = (Button) findViewById(R.id.go_button);

        level_up = (ImageButton) findViewById(R.id.level_up);
        level_down = (ImageButton) findViewById(R.id.level_down);

        restart_button = (ImageButton) findViewById(R.id.restart_button);
        exit_btn = (ImageButton) findViewById(R.id.exit_btn);


        /*                  Card Image List                 */

        card_image = new ArrayList<>();
        card_image.add(R.drawable.ic_ace_of_spades); // ace of spades
        card_image.add(R.drawable.ic_ace_of_clubs); // ace of clubs
        card_image.add(R.drawable.ic_ace_of_diamonds); // ace of diamonds

        t1 = new ArrayList<>();
        t2 = new ArrayList<>();
        t3 = new ArrayList<>();


    }


                                                /*                  End Of OnCreate                 */


    public void follow_card_click(View v){
                // isClicked

                n++;
                follow_card_id.setEnabled(false);
                middle_card_id.setEnabled(false);
                right_card_id.setEnabled(false);
                left_card_id.setEnabled(false);

                Collections.shuffle(card_image);

                left_card_id.setImageResource(card_image.get(0));
                middle_card_id.setImageResource(card_image.get(1)); 
                right_card_id.setImageResource(card_image.get(2));
                follow_card_id.setImageResource(card_image.get(0));

            }


                                        /*                  Go Button Listener                  */

        public void go_button_click(View v) {

            follow_card_id.setEnabled(true);
            middle_card_id.setEnabled(true);
            right_card_id.setEnabled(true);
            left_card_id.setEnabled(true);
            level_up.setEnabled(true);
            level_down.setEnabled(true);

            //Collections.shuffle(cards);
            if (n % 2 == 0) {
                middle_card_id.setEnabled(false);
                right_card_id.setEnabled(false);
                left_card_id.setEnabled(false);

                left_card_id.setImageResource(R.drawable.coin_game_card_from_behind);
                middle_card_id.setImageResource(R.drawable.coin_game_card_from_behind);
                right_card_id.setImageResource(R.drawable.coin_game_card_from_behind);
                follow_card_id.setImageResource(R.drawable.coin_game_card_from_behind);


            } else {

                left_card_id.setImageResource(R.drawable.coin_game_card_from_behind);
                middle_card_id.setImageResource(R.drawable.coin_game_card_from_behind);
                right_card_id.setImageResource(R.drawable.coin_game_card_from_behind);

                levelSpeed();

                }

            }



                                        /*                  Left Card Listener              */

        public void left_card_click(View v){
                // isClicked

                n++;
                middle_card_id.setEnabled(false);
                right_card_id.setEnabled(false);
                left_card_id.setEnabled(false);

                follow_card_id.setEnabled(false);

                updateLevel();
                GetLevel();
                left_card_id.setImageResource(t1.get(0));
                if (t1.get(0) == card_image.get(0)){
                    Toast.makeText(getApplicationContext(), "FOUND !", Toast.LENGTH_SHORT).show();

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                    left_card_id.startAnimation(animation);

                    found = 1;

                    score();
                } else{
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    left_card_id.startAnimation(animation);

                    found = 2;

                    score();
                }

            assignCard();

            }


                                            /*                  Middle Card Listener                    */

        public void middle_card_click(View v){
                // isClicked

                n++;
                middle_card_id.setEnabled(false);
                right_card_id.setEnabled(false);
                left_card_id.setEnabled(false);

                follow_card_id.setEnabled(false);

                updateLevel();
                GetLevel();
            middle_card_id.setImageResource(t2.get(0));
            if (t2.get(0) == card_image.get(0)){
                Toast.makeText(getApplicationContext(), "FOUND !", Toast.LENGTH_SHORT).show();

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                middle_card_id.startAnimation(animation);

                found = 1;


                score();
            } else{
                Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                middle_card_id.startAnimation(animation);

                found = 2;

                score();
            }


            assignCard();

            }


                                            /*                  Right Card Listener                 */

        public void right_card_click(View v){
                //isClicked

                n++;
                middle_card_id.setEnabled(false);
                right_card_id.setEnabled(false);
                left_card_id.setEnabled(false);

                follow_card_id.setEnabled(false);

                updateLevel();
                GetLevel();
            right_card_id.setImageResource(t3.get(0));
            if (t3.get(0) == card_image.get(0)){
                Toast.makeText(getApplicationContext(), "FOUND !", Toast.LENGTH_SHORT).show();

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                right_card_id.startAnimation(animation);

                found = 1;

                score();
            } else{
                Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                right_card_id.startAnimation(animation);

                found = 2;

                score();
            }


            assignCard();

            }

            public void assignCard(){
            left_card_id.setImageResource(t1.get(0));
            middle_card_id.setImageResource(t2.get(0));
            right_card_id.setImageResource(t3.get(0));
            }


                                                /*                  Level Up Button Listener                    */

        public void level_up_click(View v){

                if(current_level==3){
                    level_up.setEnabled(false);
                    level_down.setEnabled(true);
                }
                else if(current_level<3 ){

                    current_level++;
                }
                updateLevel();
                GetLevel(); // added this to take the new get level genre tetla3 m3a current level

            }


                                                /*                  Level Down Button Listener                  */
        public void level_down_click(View v){

                if (current_level == 1) {
                    level_down.setEnabled(false);
                    level_up.setEnabled(true);
                }
                else if(current_level>1){
                    current_level--;
                }
                updateLevel();
                GetLevel();
            }


        public void restart_click(View v){

            SaveScore();
            current_level=1;
            updateLevel();
            sc=0;
            String j = Integer.toString(sc);
            score_id.setText(j);
            go_button.setEnabled(true);

            }


    public int GetLevel() { //gla3tlha updatelevel fl west 
        TextView level_id = (TextView) findViewById(R.id.level_id);
        return Integer.parseInt(((String) level_id.getText()).replace("Level ", ""));

    }

    public void updateLevel() {
        TextView level_id = (TextView) findViewById(R.id.level_id);
        level_id.setText("Level " + current_level);
    }

                                                /*                  Score According To Level                    */

    public void score() {

        switch (GetLevel()){ // roditha 3awed getlevel 

            case 1:
                if (found == 1 && sc >= 0) {

                    sc = sc + 28;

                } else if (found == 2 && sc >= 0) {
                    sc = sc - 29;
                    if (sc < 0) {
                        sc = 0;
                    }
                }break;

            case 2:
                if (found == 1 && sc >= 0) {
                    sc = sc + 48;

                } else if (found == 2 && sc >= 0) {
                    sc = sc - 49;
                    if (sc < 0) {
                        sc = 0;
                    }
                }break;

            case 3:
                if (found == 1 && sc >= 0) {
                    sc = sc + 68;

                } else if (found == 2 && sc >= 0) {
                    sc = sc - 69;
                    if (sc < 0) {
                        sc = 0;
                    }
                }break;
            default:
                sc=0;
        }

     if(sc==0){
        gameOver();
        SaveScore();

     }
        String j = Integer.toString(sc);
        score_id.setText(j);
        GetScore(); // this bach getscore tkoun updated m3a sc 
    }


    public int GetScore () {
        TextView score_id = (TextView) findViewById(R.id.score_id);
        return Integer.parseInt((String) score_id.getText());
    }


    public void SaveScore() {


        HighScore score = new HighScore(player, "Coin_Game", sc);
        if (HSOps.addHighScore(score))
            Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_LONG);
        else Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG);
        System.out.println(sc);
    }

                                            /*                  Game Over Method                    */

    public void gameOver () { //it needs an interface to restart or exit the game and relate this method to that interface
        Toast.makeText(getApplicationContext(), "Game Over !", Toast.LENGTH_LONG).show();

        left_card_id.setEnabled(false);
        middle_card_id.setEnabled(false);
        right_card_id.setEnabled(false);
        go_button.setEnabled(false);
        restart_button.setEnabled(true);
        follow_card_id.setEnabled(false);
        level_up.setEnabled(false);
        level_down.setEnabled(false);

    }

    public void levelSpeed() {
            if(GetLevel()==1){
            Random random = new Random();
            int num = 3;
            Animation anim_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left);
            Animation anim_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right);
            Animation anim_mid = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.middle);

            Animation anim_left2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left2);
            Animation anim_right2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right2);
            Animation anim_mid2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mid2);

            Animation anim_left3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left3);
            Animation anim_right3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right3);
            Animation anim_mid3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mid3);

            switch (random.nextInt(num)) {
                case 0:

                    left_card_id.startAnimation(anim_left);
                    t1.clear();
                    t1.add(card_image.get(1));
                    right_card_id.startAnimation(anim_right);
                    t2.clear();
                    t2.add(card_image.get(2));
                    middle_card_id.startAnimation(anim_mid);
                    t3.clear();
                    t3.add(card_image.get(0));
                    break;
                case 1:

                    left_card_id.startAnimation(anim_left2);
                    t1.clear();
                    t1.add(card_image.get(2));
                    right_card_id.startAnimation(anim_right2);
                    t2.clear();
                    t2.add(card_image.get(0));
                    middle_card_id.startAnimation(anim_mid2);
                    t3.clear();
                    t3.add(card_image.get(1));
                    break;
                case 2:
                    left_card_id.startAnimation(anim_left3);
                    t1.clear();
                    t1.add(card_image.get(2));
                    right_card_id.startAnimation(anim_right3);
                    t2.clear();
                    t2.add(card_image.get(1));
                    middle_card_id.startAnimation(anim_mid3);
                    t3.clear();
                    t3.add(card_image.get(0));
                    break;

            }
    }
        if (GetLevel() == 2) {
            Random random = new Random();
            int num = 3;
            Animation anim_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left);
            anim_left.setDuration(500);
            Animation anim_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right);
            anim_right.setDuration(500);
            Animation anim_mid = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.middle);
            anim_mid.setDuration(500);

            Animation anim_left2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left2);
            anim_left2.setDuration(500);
            Animation anim_right2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right2);
            anim_right2.setDuration(500);
            Animation anim_mid2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mid2);
            anim_mid2.setDuration(500);

            Animation anim_left3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left3);
            anim_left3.setDuration(500);
            Animation anim_right3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right3);
            anim_right3.setDuration(500);
            Animation anim_mid3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mid3);
            anim_mid3.setDuration(500);

            switch (random.nextInt(num)) {
                case 0:

                    left_card_id.startAnimation(anim_left);
                    t1.clear();
                    t1.add(card_image.get(1));
                    right_card_id.startAnimation(anim_right);
                    t2.clear();
                    t2.add(card_image.get(2));
                    middle_card_id.startAnimation(anim_mid);
                    t3.clear();
                    t3.add(card_image.get(0));
                    break;
                case 1:

                    left_card_id.startAnimation(anim_left2);
                    t1.clear();
                    t1.add(card_image.get(2));
                    right_card_id.startAnimation(anim_right2);
                    t2.clear();
                    t2.add(card_image.get(0));
                    middle_card_id.startAnimation(anim_mid2);
                    t3.clear();
                    t3.add(card_image.get(1));
                    break;
                case 2:
                    left_card_id.startAnimation(anim_left3);
                    t1.clear();
                    t1.add(card_image.get(2));
                    right_card_id.startAnimation(anim_right3);
                    t2.clear();
                    t2.add(card_image.get(1));
                    middle_card_id.startAnimation(anim_mid3);
                    t3.clear();
                    t3.add(card_image.get(0));
                    break;

            }
        }
        if (GetLevel() == 3) {
            Random random = new Random();
            int num = 3;
            Animation anim_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left);
            anim_left.setDuration(200);
            Animation anim_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right);
            anim_right.setDuration(200);
            Animation anim_mid = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.middle);
            anim_mid.setDuration(200);

            Animation anim_left2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left2);
            anim_left2.setDuration(200);
            Animation anim_right2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right2);
            anim_right2.setDuration(200);
            Animation anim_mid2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mid2);
            anim_mid2.setDuration(200);

            Animation anim_left3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left3);
            anim_left3.setDuration(200);
            Animation anim_right3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right3);
            anim_right3.setDuration(200);
            Animation anim_mid3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mid3);
            anim_mid3.setDuration(200);

            switch (random.nextInt(num)) {
                case 0:

                    left_card_id.startAnimation(anim_left);
                    t1.clear();
                    t1.add(card_image.get(1));
                    right_card_id.startAnimation(anim_right);
                    t2.clear();
                    t2.add(card_image.get(2));
                    middle_card_id.startAnimation(anim_mid);
                    t3.clear();
                    t3.add(card_image.get(0));
                    break;
                case 1:

                    left_card_id.startAnimation(anim_left2);
                    t1.clear();
                    t1.add(card_image.get(2));
                    right_card_id.startAnimation(anim_right2);
                    t2.clear();
                    t2.add(card_image.get(0));
                    middle_card_id.startAnimation(anim_mid2);
                    t3.clear();
                    t3.add(card_image.get(1));
                    break;
                case 2:
                    left_card_id.startAnimation(anim_left3);
                    t1.clear();
                    t1.add(card_image.get(2));
                    right_card_id.startAnimation(anim_right3);
                    t2.clear();
                    t2.add(card_image.get(1));
                    middle_card_id.startAnimation(anim_mid3);
                    t3.clear();
                    t3.add(card_image.get(0));
                    break;

            }
        }

}

public void exit_click(View v) {

        SaveScore();
        finish();
    }
}