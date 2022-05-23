package com.example.bigbrains_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Getting_Started extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.getting_started_page);
    }




    public void SignIn(View v){

        Intent i = new Intent(this,Home.class);

        TextView name_txt= (TextView) findViewById(R.id.name_txt);

        String name= String.valueOf(name_txt.getText());

        if(!name.isEmpty() ){
                    Toast.makeText(getApplicationContext(),"Starting",Toast.LENGTH_LONG).show();
                    i.putExtra("Player",name);
                    startActivity(i);

            }else{
            Toast.makeText(getApplicationContext(),"Please Enter you information",Toast.LENGTH_LONG).show();
        }

    }
}
