package edu.rijul.hotspots;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
public class RaterDataSource {
    private SQLiteDatabase database;
    private DBRatingHelper dbHelper;

    public RaterDataSource(Context context){
        dbHelper = new DBRatingHelper(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public long insertRating(String bar, String address, float Rbeer, float Rwine, float Rmusic){
        ContentValues values = new ContentValues();
        values.put("barname", bar);
        values.put("address", address);
        values.put("beer", Rbeer);
        values.put("wine", Rwine);
        values.put("music", Rmusic);

        return database.insert("bar", null, values);
    }
}
