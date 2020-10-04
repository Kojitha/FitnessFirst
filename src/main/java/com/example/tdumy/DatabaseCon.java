package com.example.tdumy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseCon extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase sqLiteDatabase;

    public static final String DATABASE_NAME = "water.db";
    public static final String TABLE_NAME = "water_consumption";

    public static final String COL_1 = "dailywater";
    public static final String COL_2 = "wakeup";
    public static final String COL_3 = "gotobed";

    public DatabaseCon(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);//Database will creating
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(dailywater TEXT PRIMARY KEY,wakeup TEXT,gotobed TEXT)");//It executs what ever you passed query
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String daily, String wakeups, String gotup) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, daily);
        contentValues.put(COL_2, wakeups);
        contentValues.put(COL_3, gotup);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<ModelWater> getAllData() {
        ArrayList<ModelWater> arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cs = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cs.moveToFirst()) {
            do {
                ModelWater modelWater = new ModelWater();
                modelWater.setWater(cs.getString(0));
                modelWater.setWakeup(cs.getString(1));
                modelWater.setGotup(cs.getString(2));
                arrayList.add(modelWater);
            } while (cs.moveToNext());
        }
        cs.close();
        return arrayList;
    }

    public void updateInfo(String water, String wakeup, String gotobed) {
        sqLiteDatabase = getWritableDatabase();
        String updateQuery = "UPDATE " + TABLE_NAME + " SET wakeup = ?, gotobed = ? WHERE dailywater = ?";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(updateQuery);
        statement.clearBindings();

        statement.bindString(1, wakeup);
        statement.bindString(2, gotobed);
        statement.bindString(3, water);

        statement.execute();
    }

    public void deleteData(String water) {
        sqLiteDatabase = getWritableDatabase();

        String deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE dailywater = ?";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(deleteQuery);
        statement.clearBindings();

        statement.bindString(1, water);
        statement.execute();
    }


}