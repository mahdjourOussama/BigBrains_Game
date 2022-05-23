package com.example.bigbrains_game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class HighScoreOps implements Serializable {
    private DataBaseSQLiteHelper dbh;
    private SQLiteDatabase db;

    public  HighScoreOps(Context ctx){
        dbh =new DataBaseSQLiteHelper(ctx);
    }

    public SQLiteDatabase open(){
        db=dbh.getWritableDatabase();
        return db;
    }

    public void close(){
        dbh.close();
    }

    public boolean addHighScore(HighScore score){
        open();
        ContentValues values = new ContentValues();
        values.put("PlayerName",score.getPlayer());
        values.put("Score",score.getScore());
        values.put("GameName",score.getGame());
        db = dbh.getWritableDatabase();
        long id = db.insert("Scores",null, values);
        if(id == -1)
            return false;
        else
            return true;
    }


    public ArrayList<String> getAllScores(String Game) {

        ArrayList<String  > Scores  = new ArrayList<>();
        db = dbh.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Scores where GameName =? Order by Score DESC", new String[]{Game});
        res.moveToFirst();
        int count =1;
        while(res.isAfterLast() == false) {

            String t1 = res.getString(0);
            String t2 = res.getString(2);
            String t3 = res.getString(3);

            Scores.add(count+")_  "+t2+"   :    "+t3);
            res.moveToNext();
            count++;
        }
        return Scores;
    }

}
