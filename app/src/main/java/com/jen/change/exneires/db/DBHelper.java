package com.jen.change.exneires.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class DBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "exchange_res";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DISPLAY_NAME = "display_name";
    public static final String COLUMN_IMGS_URL = "img_url";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_RES_TYPE = "res_type";
    public static final String COLUMN_TRADE_TYPE = "trade_type";
    public static final String COLUMN_EXCHANGE_WANT = "exchange_want";
    public static final String COLUMN_CREATE_DATE = "date";
    public static final String COLUMN_CONTENT = "content";

    public static final String DATABASE_NAME = "exchange.db";
    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE
            = "CREATE TABLE " + TABLE_NAME
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CREATE_DATE + " CHAR(8) , "
            + COLUMN_CONTENT + " TEXT NOT NULL);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
