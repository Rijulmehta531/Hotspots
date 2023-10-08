package edu.rijul.hotspots;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBRatingHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="mybars.db";
    private static final int DATABASE_VERSION = 1;

    //Database creation
    private static final String CREATE_TABLE_BAR =
            "create table bar (_id integer primary key autoincrement, "+
                    "barname text not null, address text, "+
                    "beer float , wine float, "+
                    "music float);";
    public DBRatingHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS bar");
        onCreate(db);
    }
}