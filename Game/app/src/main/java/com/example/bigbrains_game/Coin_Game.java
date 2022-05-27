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
    private String player;
    private HighScoreOps HSOps;

    ImageView follow_card_id, left_card_id, middle_card_id, right_card_id;
    TextView score_id;
    ImageButton level_up, level_down;

    ArrayList<Integer> cards;
    ArrayList<Integer> card_image;

    Button go_button;

    int n = 0;
    int found = 0, notFound = 0;
    int sc = 0, current_level = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.find_the_coin_game);

        follow_card_id = (ImageView) findViewById(R.id.follow_card_id);
        left_card_id = (ImageView) findViewById(R.id.left_card_id);
        middle_card_id = (ImageView) findViewById(R.id.middle_card_id);
        right_card_id = (ImageView) findViewById(R.id.right_card_id);

        score_id = (TextView) findViewById(R.id.score_id);

        go_button = (Button) findViewById(R.id.go_button);

        level_up = (ImageButton) findViewById(R.id.level_up);
        level_down = (ImageButton) findViewById(R.id.level_down);


        cards = new ArrayList<>();
        cards.add(101); // ace of spades
        cards.add(201); // ace of clubs
        cards.add(301); // ace of diamonds
        Collections.shuffle(cards);

        card_image = new ArrayList<>();
        card_image.add(R.drawable.ic_ace_of_spades); // ace of spades
        card_image.add(R.drawable.ic_ace_of_clubs); // ace of clubs
        card_image.add(R.drawable.ic_ace_of_diamonds); // ace of diamonds


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


        left_card_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // isClicked
                n++;

                //assign images
                if (cards.get(0) == 101) {
                    left_card_id.setImageResource(card_image.get(0));
                    Toast.makeText(getApplicationContext(), "FOUND !", Toast.LENGTH_SHORT).show();

                    middle_card_id.setEnabled(false);
                    right_card_id.setEnabled(false);


                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                    left_card_id.startAnimation(animation);

                    found = 1;
                    notFound = 0;
                    score();

                } else if (cards.get(0) == 201) {
                    left_card_id.setImageResource(card_image.get(1));
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    middle_card_id.setEnabled(false);
                    right_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    left_card_id.startAnimation(animation);

                    notFound = 1;
                    found = 0;
                    score();
                } else if (cards.get(0) == 301) {
                    left_card_id.setImageResource(card_image.get(2));
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    middle_card_id.setEnabled(false);
                    right_card_id.setEnabled(false);


                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    left_card_id.startAnimation(animation);

                    notFound = 1;
                    found = 0;
                    score();
                }


            }
        });


        middle_card_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // isClicked
                n++;

                //assign images
                if (cards.get(1) == 101) {
                    middle_card_id.setImageResource(card_image.get(0));
                    Toast.makeText(getApplicationContext(), "FOUND !", Toast.LENGTH_SHORT).show();

                    left_card_id.setEnabled(false);
                    right_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                    middle_card_id.startAnimation(animation);


                    found = 1;
                    notFound = 0;
                    score();
                } else if (cards.get(1) == 201) {
                    middle_card_id.setImageResource(card_image.get(1));
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    left_card_id.setEnabled(false);
                    right_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    middle_card_id.startAnimation(animation);

                    notFound = 1;
                    found = 0;
                    score();
                } else if (cards.get(1) == 301) {
                    middle_card_id.setImageResource(card_image.get(2));
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    left_card_id.setEnabled(false);
                    right_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    middle_card_id.startAnimation(animation);

                    notFound = 1;
                    found = 0;
                    score();
                }


            }
        });

        right_card_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //isClicked
                n++;

                //assign images
                if (cards.get(2) == 101) {
                    right_card_id.setImageResource(card_image.get(0));
                    Toast.makeText(getApplicationContext(), "FOUND !", Toast.LENGTH_SHORT).show();

                    left_card_id.setEnabled(false);
                    middle_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                    right_card_id.startAnimation(animation);


                    found = 1;
                    notFound = 0;
                    score();
                } else if (cards.get(2) == 201) {
                    right_card_id.setImageResource(card_image.get(1));
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    left_card_id.setEnabled(false);
                    middle_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    right_card_id.startAnimation(animation);

                    notFound = 1;
                    found = 0;
                    score();
                } else if (cards.get(2) == 301) {
                    right_card_id.setImageResource(card_image.get(2));
                    Toast.makeText(getApplicationContext(), "Not Found ", Toast.LENGTH_SHORT).show();

                    left_card_id.setEnabled(false);
                    middle_card_id.setEnabled(false);

                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    right_card_id.startAnimation(animation);

                    notFound = 1;
                    found = 0;
                    score();
                }


            }
        });

        level_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(current_level==3){
                    level_up.setEnabled(false);
                    level_down.setEnabled(true);
                }
                else if(current_level<3 ){

                    current_level++;
                }
                updateLevel();

            }
        });

        level_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (current_level == 1) {
                    level_down.setEnabled(false);
                    level_up.setEnabled(true);
                }
                else if(current_level>1){
                    current_level--;
                }
                updateLevel();

            }
        });

    }


    public int GetLevel() {
        TextView level_id = (TextView) findViewById(R.id.level_id);
        updateLevel();
        return Integer.parseInt(((String) level_id.getText()).replace("Level ", ""));

    }

    public void updateLevel() {
        TextView level_id = (TextView) findViewById(R.id.level_id);
        level_id.setText("Level " + current_level);
    }

    public void score() {

       switch (current_level){

        case 1:
        if (found == 1 && sc >= 0) {
            sc = sc + 28;
        } else if (found == 0 && sc >= 0) {
            sc = sc - 29;
            if (sc < 0) {
                sc = 0;
            }
        }

        case 2:
        if (found == 1 && sc >= 0) {
            sc = sc + 48;
        } else if (found == 0 && sc >= 0) {
            sc = sc - 49;
            if (sc < 0) {
                sc = 0;
            }
        }

        case 3:
        if (found == 1 && sc >= 0) {
            sc = sc + 68;
        } else if (found == 0 && sc >= 0) {
            sc = sc - 69;
            if (sc < 0) {
                sc = 0;
            }
        }
    }

    /*if(sc==0){
        gameOver();
    }*/
        String j = Integer.toString(sc);
        score_id.setText(j);
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

        public void gameOver () {
            Toast.makeText(getApplicationContext(), "Game Over !", Toast.LENGTH_LONG).show();
            SaveScore();
        }

}




