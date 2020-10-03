package com.example.calories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CDatabaseHelper extends SQLiteOpenHelper {
    public CDatabaseHelper(@Nullable Context context) {
        super(context, CalorieConstants.DB_NAME, null, CalorieConstants.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CalorieConstants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+CalorieConstants.TABLE_NAME);
        onCreate(db);
    }

    //insert data
    public long insertInfo(String calories, String date, String feelings, String notes){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CalorieConstants.CALORIES, calories);
        values.put(CalorieConstants.DATE, date);
        values.put(CalorieConstants.FEELINGS, feelings);
        values.put(CalorieConstants.NOTES, notes);

        long id = db.insert(CalorieConstants.TABLE_NAME, null, values);
        db.close();
        return id;

    }

    //retrieve data
    public ArrayList<CalorieModel> getAllData(String orderBy){

        ArrayList<CalorieModel> arrayList = new ArrayList<>();
        //select query from database
        String selectQuery = "SELECT * FROM " + CalorieConstants.TABLE_NAME + " ORDER BY " + orderBy;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToNext()){

            do{
                CalorieModel model = new CalorieModel(
                        ""+cursor.getInt(cursor.getColumnIndex(CalorieConstants.ID)),
                        ""+cursor.getString(cursor.getColumnIndex(CalorieConstants.CALORIES)),
                        ""+cursor.getString(cursor.getColumnIndex(CalorieConstants.DATE)),
                        ""+cursor.getString(cursor.getColumnIndex(CalorieConstants.FEELINGS)),
                        ""+cursor.getString(cursor.getColumnIndex(CalorieConstants.NOTES))
                );
                arrayList.add(model);
            } while(cursor.moveToNext());

        }
        db.close();
        return arrayList;
    }

    //update data
    public void updateInfo(String id, String calories, String date, String feelings, String notes){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CalorieConstants.CALORIES, calories);
        values.put(CalorieConstants.DATE, date);
        values.put(CalorieConstants.FEELINGS, feelings);
        values.put(CalorieConstants.NOTES, notes);

        db.update(CalorieConstants.TABLE_NAME, values, CalorieConstants.ID + " = ?", new String[]{id});
        db.close();

    }

    //delete data
    public void deleteInfo(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(CalorieConstants.TABLE_NAME, CalorieConstants.ID + " = ? ", new String[]{id});
        db.close();

    }



}
