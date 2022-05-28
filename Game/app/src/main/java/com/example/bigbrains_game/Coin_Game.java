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

public class Coin_Game extends AppCompatActivity {

                                            /*              Declarations                */

    private String player;
    private HighScoreOps HSOps;

    ImageView follow_card_id, left_card_id, middle_card_id, right_card_id;
    TextView score_id;
    ImageButton level_up, level_down;

    ArrayList<Integer> cards;
    ArrayList<Integer> card_image;

    Button go_button;

    int n = 0;
    int found = 0;
    int notFound = 0;
    int sc = 0;
    int current_level = 1;
    int s=1;//this my level variable

                                                /*                  OnCreate                 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.find_the_coin_game);

                                            /*                  Find View                   */

        follow_card_id = (ImageView) findViewById(R.id.follow_card_id);
        left_card_id = (ImageView) findViewById(R.id.left_card_id);
        middle_card_id = (ImageView) findViewById(R.id.middle_card_id);
        right_card_id = (ImageView) findViewById(R.id.right_card_id);

        score_id = (TextView) findViewById(R.id.score_id);

        go_button = (Button) findViewById(R.id.go_button);

        level_up = (ImageButton) findViewById(R.id.level_up);
        level_down = (ImageButton) findViewById(R.id.level_down);

                                            /*              Card List               */

        cards = new ArrayList<>();
        cards.add(101); // ace of spades
        cards.add(201); // ace of clubs
        cards.add(301); // ace of diamonds
        Collections.shuffle(cards);

                                         /*                  Card Image List                 */

        card_image = new ArrayList<>();
        card_image.add(R.drawable.ic_ace_of_spades); // ace of spades
        card_image.add(R.drawable.ic_ace_of_clubs); // ace of clubs
        card_image.add(R.drawable.ic_ace_of_diamonds); // ace of diamonds

                                        /*                  Follow Card Listener                    */


        follow_card_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // isClicked
                n++;

                Collections.shuffle(card_image);

                left_card_id.setImageResource(card_image.get(0));
                middle_card_id.setImageResource(card_image.get(1)); 
                right_card_id.setImageResource(card_image.get(2));
                follow_card_id.setImageResource(card_image.get(0));
            }
        });

                                        /*                  Go Button Listener                  */

        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.shuffle(cards);
                if (n % 2 == 0) {
                    left_card_id.setImageResource(R.drawable.coin_game_card_from_behind);
                    middle_card_id.setImageResource(R.drawable.coin_game_card_from_behind);
                    right_card_id.setImageResource(R.drawable.coin_game_card_from_behind);
                    follow_card_id.setImageResource(R.drawable.coin_game_card_from_behind);

                } else {
                    left_card_id.setImageResource(R.drawable.coin_game_card_from_behind);
                    middle_card_id.setImageResource(R.drawable.coin_game_card_from_behind);
                    right_card_id.setImageResource(R.drawable.coin_game_card_from_behind);


                    Animation anim_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left);
                    Animation anim_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right);
                    Animation anim_mid = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.middle);

                    left_card_id.startAnimation(anim_left);
                    right_card_id.startAnimation(anim_right);
                    middle_card_id.startAnimation(anim_mid);


                }

            }
        });

                                        /*                  Left Card Listener              */


        left_card_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // isClicked
                n++;

                updateLevel();
                GetLevel();
                //assign images
                if (cards.get(0) == 101) {
                    left_card_id.setImageResource(card_image.get(0));
                    Toast.makeText(getApplicationContext(), "FOUND !", Toast.LENGTH_SHORT).show();

                    middle_card_id.setEnabled(false);
                    right_card_id.setEnabled(false);


                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                    left_card_id.startAnimation(animation);

                    found = 1;
                    notFound = 2; //i changed this to 2 to not get confused with valeur initial

                    score();

                } else if (cards.get(0) == 201) {
                    left_card_id.setImageResource(card_image.get(1));
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    middle_card_id.setEnabled(false);
                    right_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    left_card_id.startAnimation(animation);

                    notFound = 1;
                    found = 2;

                    score();
                } else if (cards.get(0) == 301) {
                    left_card_id.setImageResource(card_image.get(2));
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    middle_card_id.setEnabled(false);
                    right_card_id.setEnabled(false);


                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    left_card_id.startAnimation(animation);

                    notFound = 1;
                    found = 2;

                    score();
                }


            }
        });

                                            /*                  Middle Card Listener                    */


        middle_card_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // isClicked
                n++;

                updateLevel();
                GetLevel();

                //assign images
                if (cards.get(1) == 101) {
                    middle_card_id.setImageResource(card_image.get(0));
                    Toast.makeText(getApplicationContext(), "FOUND !", Toast.LENGTH_SHORT).show();

                    left_card_id.setEnabled(false);
                    right_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                    middle_card_id.startAnimation(animation);


                    found = 1;
                    notFound = 2;

                    score();
                } else if (cards.get(1) == 201) {
                    middle_card_id.setImageResource(card_image.get(1));
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    left_card_id.setEnabled(false);
                    right_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    middle_card_id.startAnimation(animation);

                    notFound = 1;
                    found = 2;

                    score();
                } else if (cards.get(1) == 301) {
                    middle_card_id.setImageResource(card_image.get(2));
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    left_card_id.setEnabled(false);
                    right_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    middle_card_id.startAnimation(animation);

                    notFound = 1;
                    found = 2;

                    score();
                }


            }
        });

                                            /*                  Right Card Listener                 */

        right_card_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //isClicked
                n++;

                updateLevel();
                GetLevel();

                //assign images
                if (cards.get(2) == 101) {
                    right_card_id.setImageResource(card_image.get(0));
                    Toast.makeText(getApplicationContext(), "FOUND !", Toast.LENGTH_SHORT).show();

                    left_card_id.setEnabled(false);
                    middle_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                    right_card_id.startAnimation(animation);


                    found = 1;
                    notFound = 2;

                    score();
                } else if (cards.get(2) == 201) {
                    right_card_id.setImageResource(card_image.get(1));
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    left_card_id.setEnabled(false);
                    middle_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    right_card_id.startAnimation(animation);

                    notFound = 1;
                    found = 2;

                    score();
                } else if (cards.get(2) == 301) {
                    right_card_id.setImageResource(card_image.get(2));
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    left_card_id.setEnabled(false);
                    middle_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    right_card_id.startAnimation(animation);

                    notFound = 1;
                    found = 2;

                    score();
                }


            }
        });

                                                /*                  Level Up Button Listener                    */

        level_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(current_level==3){
                    level_up.setEnabled(false);
                    level_down.setEnabled(true);
                }
                else if(current_level<3 ){

                    current_level++;
                    s=s+1;  // this ill need it f read_level
                }
                updateLevel();
                GetLevel(); // added this to take the new get level genre tetla3 m3a current level

            }
        });

                                                /*                  Level Down Button Listener                  */

        level_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (current_level == 1) {
                    level_down.setEnabled(false);
                    level_up.setEnabled(true);
                }
                else if(current_level>1){
                    current_level--;
                    s=s-1; // this ill need it f read_level
                }
                updateLevel();
                GetLevel();
            }
        });

    }

                                                /*                  End Of OnCreate                 */


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

     /*if(sc==0){
        gameOver();
     }*/
        String j = Integer.toString(sc);
        score_id.setText(j);
        GetScore(); // this bach getscore tkoun updated m3a sc 
    }


    public int GetScore () {
        TextView score_id = (TextView) findViewById(R.id.score_id);
        return Integer.parseInt((String) score_id.getText());
    }

    public void SaveScore () {
        sc = GetScore();
        HighScore score = new HighScore(player, "Coin_Game", sc);
        if (HSOps.addHighScore(score))
            Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_LONG);
        else Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG);
    }

                                            /*                  Game Over Method                    */

    public void gameOver () { //it needs an interface to restart or exit the game and relate this method to that interface
        Toast.makeText(getApplicationContext(), "Game Over !", Toast.LENGTH_LONG).show();
        SaveScore();
    }

}