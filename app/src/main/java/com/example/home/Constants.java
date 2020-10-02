package com.example.home;

public class Constants {

    //dbname
    public static final String DB_NAME = "PERSON_INFO_DB";
    //db version
    public static final int DB_VERSION = 1;
    //db table
    public static final String TABLE_NAME = "PERSON_INFO_TABLE";
    //table columns
    public static final String C_ID = "ID";
    public static final String C_NAME= "NAME";
    public static final String C_AGE = "AGE";
    public static final String C_WEIGHT = "WEIGHT";
    public static final String C_HEIGHT = "HEIGHT";
    public static final String C_IMAGE  =  "IMAGE";
    //create query for table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + C_NAME + " TEXT,"
            + C_AGE + " TEXT,"
            + C_WEIGHT + " TEXT,"
            + C_HEIGHT + " TEXT,"
            + C_IMAGE + " TEXT"
            + ");";

}


