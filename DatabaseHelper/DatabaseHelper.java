
package com.zybooks.finalprojectcs360jorgebermudez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "WeightTrackingApp.db";
    private static final int DATABASE_VERSION = 2;

    private static final String USER_TABLE = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    private static final String WEIGHT_TABLE = "weight_entries";
    private static final String COLUMN_ENTRY_ID = "entry_id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_FOREIGN_USER_ID = "user_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + USER_TABLE + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT)";

        String createWeightTable = "CREATE TABLE " + WEIGHT_TABLE + " (" +
                COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_WEIGHT + " REAL, " +
                COLUMN_FOREIGN_USER_ID + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_FOREIGN_USER_ID + ") REFERENCES " + USER_TABLE + "(" + COLUMN_USER_ID + "))";

        db.execSQL(createUserTable);
        db.execSQL(createWeightTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WEIGHT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public boolean insertUser(String username, String password) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, username);
            values.put(COLUMN_PASSWORD, password);
            long result = db.insert(USER_TABLE, null, values);
            return result != -1;
        } catch (Exception e) {
            Log.e("DB", "Insert User Failed", e);
            return false;
        }
    }

    public int getUserId(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_USER_ID + " FROM " + USER_TABLE + " WHERE " + COLUMN_USERNAME + " = ?", new String[]{username});
        if (cursor != null && cursor.moveToFirst()) {
            int userId = cursor.getInt(0);
            cursor.close();
            return userId;
        }
        return -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean insertWeightEntry(String date, float weight, int userId) {
        if (date == null || date.isEmpty() || weight <= 0 || userId < 0) return false;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_DATE, date);
            values.put(COLUMN_WEIGHT, weight);
            values.put(COLUMN_FOREIGN_USER_ID, userId);
            long result = db.insert(WEIGHT_TABLE, null, values);
            return result != -1;
        } catch (Exception e) {
            Log.e("DB", "Insert Weight Entry Failed", e);
            return false;
        }
    }

    public Cursor getWeightEntriesByUserId(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT date, weight FROM " + WEIGHT_TABLE + " WHERE " + COLUMN_FOREIGN_USER_ID + " = ?", new String[]{String.valueOf(userId)});
    }

    public Cursor getWeightsByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT w.weight, w.date " +
                "FROM " + WEIGHT_TABLE + " w " +
                "JOIN " + USER_TABLE + " u ON w." + COLUMN_FOREIGN_USER_ID + " = u." + COLUMN_USER_ID + " " +
                "WHERE u." + COLUMN_USERNAME + " = ?" + "ORDER BY w.date ASC"; //order the data by date

        return db.rawQuery(query, new String[]{username});
    }

    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = -1;
        try {
            result = db.insert(USER_TABLE, null, values);
        } catch (SQLiteConstraintException e) {
            e.printStackTrace(); // log for debug
        }
        return result != -1;
    }
}
