package com.example.bigbrains_game;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Matching_Game extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.matching_game_page);
        setUp();
    }

    //--------------------------Variable Declaration------------------------------------------------
        private List <Card> CardLayout = new ArrayList<Card>(),
                        RemainingCards =new ArrayList<Card>();
        private List<int []> PlacesUnavailable = new ArrayList<int [] >();
    //----------------------------------------------------------------------------------------------

    //-----------------------------Basic Methods----------------------------------------------------

    public int getID(String type,String name){
        return  getResources().getIdentifier(name,type, getPackageName());
    }

    //----------------------------------------------------------------------------------------------
    //--------------------------Game Logic----------------------------------------------------------
    public int [] RandomPlace(){
        int position1  =(int)(Math.random()*24+1),
            position2 =(int)(Math.random()*24+1);
        if(position1!=position2){
            return new int []{position1,position2};
        }else return RandomPlace();
    }

    public void ShuffleCards(View v){
        PlacesUnavailable.clear();
        for(int i=0;i<24;i+=2){
            boolean exist=false ;
            int r []=RandomPlace();
            do{
                exist = false;
                for (int[] UnAvailable :
                        PlacesUnavailable) {
                    if (r[0] == UnAvailable[0] || r[1] == UnAvailable[0]
                            || r[0] == UnAvailable[1] || r[1] == UnAvailable[1]) {
                        exist = true;
                        r=RandomPlace();
                        break;
                    }
                }
            }while(exist);

            CardLayout.get(i).setId(r[0]);
            CardLayout.get(i+1).setId(r[1]);
            setCardButton(CardLayout.get(i));
            setCardButton(CardLayout.get(i+1));
            PlacesUnavailable.add(r);
        }
        DisplayCards();
    }

    public void setCardButton(Card c){
        int id=c.getId();

        int btnID = getID("id","button"+id);

        c.setBtnID(findViewById(btnID));
    }

    public void setUp(){
        CardLayout.clear();
        RemainingCards.clear();

        for (int i=1;i<25;i++){
            int btnID = getID("id","button"+i),
                    imageID=(int)( ((i-1)/2)+1 ),
                CardImg=getID("drawable","matching_game_card_"+(imageID) );
            Card btn = new Card(i,(Button) findViewById(btnID),CardImg,imageID),
            btn2=new Card(i,(Button) findViewById(btnID),CardImg,imageID);
            CardLayout.add(btn);
            RemainingCards.add(btn2);
        }
        int i=0;
        while(i<24){
            CardLayout.get(i).setTwin(CardLayout.get(i+1));
            RemainingCards.get(i).setTwin(RemainingCards.get(i+1));
            i=i+2;
        }
    }

    public synchronized void FlashBtn(Card c){
        Button btn = c.getBtnID();
        int colorID =c.getImage();
        btn.setBackgroundResource(colorID);
        btn.setText(btn.getText()+""+c.getImageID());
        new android.os.Handler(Looper.getMainLooper()).postDelayed(
                new Runnable() {
                    public void run() {
                        btn.setBackgroundResource(R.drawable.matching_game_card_0);
                    }
                },
                3000);

    }

    public void DisplayCards(){
        for (Card c :
                CardLayout) {
            FlashBtn(c);
        }
    }

    public void DisplayTwin(View v){
        Button btn = findViewById(v.getId());
        Card selected= CardLayout.get(0);
        for (Card c :
                CardLayout) {
            if(c.getBtnID()==btn) {
                selected = c;
                break;
            }
        }
        FlashBtn(selected);
        FlashBtn(selected.getTwin());
    }
    //----------------------------------------------------------------------------------------------
}
