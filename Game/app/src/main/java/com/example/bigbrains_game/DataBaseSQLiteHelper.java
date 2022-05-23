package com.example.bigbrains_game;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.Serializable;

class DataBaseSQLiteHelper extends SQLiteOpenHelper implements Serializable {
    private static final String   REQUET_CREATION_Score_Table="create table Scores (  ID integer " +
            "primary key autoincrement, GameName text not null, PlayerName text not null,Score integer not null);";


    public DataBaseSQLiteHelper(@Nullable Context context) {

            super(context, "BigBrains_Game", null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(REQUET_CREATION_Score_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE Scores");
        onCreate(db);

    }
}