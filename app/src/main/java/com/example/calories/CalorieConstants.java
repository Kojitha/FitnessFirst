package com.example.calories;

public class CalorieConstants {

    public static final String DB_NAME = "calorie_db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "calorie_table";


    //columns
    public static final String ID = "ID";
    public static final String CALORIES = "CALORIES";
    public static final String DATE = "DATE";
    public static final String FEELINGS = "FEELINGS";
    public static final String NOTES = "NOTES";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + CALORIES + " TEXT" +
            ","
            + DATE + " TEXT,"
            + FEELINGS + " TEXT,"
            + NOTES + " TEXT"
            + ");";

}

