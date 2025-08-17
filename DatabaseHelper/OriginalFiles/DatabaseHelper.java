package com.zybooks.finalprojectcs360jorgebermudez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "WeightTrackingApp.db";
    private static final int DATABASE_VERSION = 1;

    // User table
    private static final String USER_TABLE = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Weight entry table
    private static final String WEIGHT_TABLE = "weight_entries";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_WEIGHT = "weight";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create user table
        String createUserTableQuery = "CREATE TABLE " + USER_TABLE + "("
                + COLUMN_USERNAME + " TEXT PRIMARY KEY,"
                + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUserTableQuery);

        // Create weight entry table
        String createWeightTableQuery = "CREATE TABLE " + WEIGHT_TABLE + "("
                + COLUMN_DATE + " TEXT PRIMARY KEY,"
                + COLUMN_WEIGHT + " REAL)";
        db.execSQL(createWeightTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + WEIGHT_TABLE);

        // Create tables again
        onCreate(db);
    }

    // User table methods

    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long result = db.insert(USER_TABLE, null, values);
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_USERNAME};
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(USER_TABLE, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    // Weight entry table methods

    public boolean insertWeightEntry(String date, float weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_WEIGHT, weight);
        long result = db.insert(WEIGHT_TABLE, null, values);
        return result != -1;
    }

    public Cursor getAllWeightEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + WEIGHT_TABLE, null);
    }

    public boolean deleteWeightEntry(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_DATE + " = ?";
        String[] selectionArgs = {date};
        int result = db.delete(WEIGHT_TABLE, selection, selectionArgs);
        return result > 0;
    }

    public boolean updateWeightEntry(String date, float weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WEIGHT, weight);
        String selection = COLUMN_DATE + " = ?";
        String[] selectionArgs = {date};
        int result = db.update(WEIGHT_TABLE, values, selection, selectionArgs);
        return result > 0;
    }
}
