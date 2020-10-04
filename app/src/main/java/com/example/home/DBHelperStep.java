package com.example.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;



public class DBHelperStep extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase sqLiteDatabase;

    public static final String DATABASE_NAME = "STEPS_DB";
    public static final String TABLE_NAME = "STEPS_TABLE";

    public static final String COL_1 = "STEPS";
    public static final String COL_2 = "TYPE";
    public static final String COL_3 = "DATE";



    public DBHelperStep(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(STEPS TEXT PRIMARY KEY,TYPE TEXT,DATE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(sqLiteDatabase);


    }

    public boolean insertInfo(String steps,String type,String date){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_1,steps);
        values.put(COL_2,type);
        values.put(COL_3,date);
        long result =sqLiteDatabase.insert(TABLE_NAME,null, values);
        if(result == -1){
            return false;
        }else{
               return true;
        }

    }

    public ArrayList<com.example.home.ModelStep> getAllData() {
        ArrayList<com.example.home.ModelStep> arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cs = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cs.moveToFirst()) {
            do {
                com.example.home.ModelStep modelStep = new com.example.home.ModelStep();
                modelStep.setSteps(cs.getString(0));
                modelStep.setType(cs.getString(1));
                modelStep.setDate(cs.getString(2));
                arrayList.add(modelStep);
            } while (cs.moveToNext());
        }
        cs.close();
        return arrayList;
    }

    public void updateInfo(String type,String date, String steps) {
        sqLiteDatabase = getWritableDatabase();
        String updateQuery = "UPDATE " + TABLE_NAME + " SET  TYPE = ?, DATE = ? WHERE STEPS = ?";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(updateQuery);
        statement.clearBindings();


        statement.bindString(1, type);
        statement.bindString(2, date);
        statement.bindString(3, steps);

        statement.execute();
    }

    public void deleteinfo(String steps) {
        sqLiteDatabase = getWritableDatabase();

        String deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE STEPS = ?";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(deleteQuery);
        statement.clearBindings();

        statement.bindString(1, steps);
        statement.execute();
    }

    public com.example.home.ModelStep selectModel(String id) {
        sqLiteDatabase = getReadableDatabase();
        com.example.home.ModelStep modelStep = new com.example.home.ModelStep();

        String select = "SELECT * FROM " +TABLE_NAME + " WHERE " + COL_1;
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.moveToFirst() ) {
            modelStep.setSteps(cursor.getString(0));
            modelStep.setType(cursor.getString(1));
            modelStep.setDate(cursor.getString(2));

            cursor.close();
            return modelStep;
        }
        else {
            return null;
        }
    }



}
