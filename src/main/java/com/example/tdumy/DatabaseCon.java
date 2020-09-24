package com.example.tdumy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.ColorSpace;
import android.provider.SyncStateContract;
import android.view.Display;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import java.nio.ByteOrder;
import java.util.ArrayList;

public class DatabaseCon extends SQLiteOpenHelper {
    Context context;

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
    

    public Cursor getallData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //Cursor - Random Read and Write Access
        Cursor cs = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return cs;
    }

    public ArrayList<Model> getAllData() {
        ArrayList<Model> arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cs = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cs.moveToFirst()) {
            do {
                Model model = new Model();
                model.setWater(cs.getString(0));
                model.setWakeup(cs.getString(1));
                model.setGotup(cs.getString(2));
                arrayList.add(model);
            } while (cs.moveToNext());
        }
        cs.close();
        return arrayList;
    }
}