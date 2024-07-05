package com.mnandi.mnandiapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Mnandi_Database.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_CUSTOMERS = "Customers";
    public static final String COLUMN_CUSTOMER_ID = "customer_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_REGISTRATION_DATE = "registrationDate";

    public static final String TABLE_CUSTOMER_LOGS = "CustomerLogs";
    public static final String COLUMN_LOG_ID = "log_id";
    public static final String COLUMN_CUSTOMER_ID_FK = "customer_id";
    public static final String COLUMN_LOG_TIME = "log_time";

    private static final String TABLE_CREATE_CUSTOMERS =
            "CREATE TABLE " + TABLE_CUSTOMERS + " (" +
                    COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_REGISTRATION_DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ");";

    private static final String TABLE_CREATE_CUSTOMER_LOGS =
            "CREATE TABLE " + TABLE_CUSTOMER_LOGS + " (" +
                    COLUMN_LOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CUSTOMER_ID_FK + " INTEGER, " +
                    COLUMN_LOG_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (" + COLUMN_CUSTOMER_ID_FK + ") REFERENCES " + TABLE_CUSTOMERS + "(" + COLUMN_CUSTOMER_ID + ")" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_CUSTOMERS);
        db.execSQL(TABLE_CREATE_CUSTOMER_LOGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER_LOGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        onCreate(db);
    }
}
