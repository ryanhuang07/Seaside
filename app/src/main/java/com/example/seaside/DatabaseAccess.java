package com.example.seaside;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DatabaseAccess extends SQLiteOpenHelper {

    public DatabaseAccess(@Nullable Context context) {
        super(context, "seaside-service.db", null, 1);
    }

    // Creates table when needed
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT)";
        db.execSQL(createTable);

        createTable = "CREATE TABLE admins (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT)";
        db.execSQL(createTable);

        createTable = "CREATE TABLE events (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, location TEXT, time TEXT, volunteers INTEGER)";
        db.execSQL(createTable);

        createTable = "CREATE TABLE registered (event_id INTEGER, user_id INTEGER, FOREIGN KEY(event_id) REFERENCES events(id), FOREIGN KEY(user_id) REFERENCES students(id))";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // for updating app
    }

    // Adds a user to the table, type is either "students" or "admins", returns true or false based on it's success
    public boolean addUser(String name, String email, String password, String type) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("email", email);
        cv.put("password", password);

        long success = db.insert(type, null, cv);

        if (success == -1) {
            db.close();
            return false;
        }

        db.close();
        return true;

    }

    // Adds an event to the table, returns true or false based on success
    public boolean addEvent(String title, String description, String location, String time, int volunteers) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("title", title);
        cv.put("description", description);
        cv.put("location", location);
        cv.put("time", time);
        cv.put("volunteers", volunteers);

        long success = db.insert("events", null, cv);

        if (success == -1) {
            db.close();
            return false;
        }

        db.close();
        return true;

    }

    // Checks a user's login, type is either "students" or "admins", returns the user's id or -1 if failed
    public int checkLogin(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] inputs = {email};

        Cursor correctPassword = null;
        try {
            correctPassword = db.rawQuery("SELECT password, id FROM students WHERE email = ?", inputs, null);
        } catch (Exception e) {
            // L
        }

        if (!correctPassword.moveToFirst()) {
            try {
                correctPassword = db.rawQuery("SELECT password, id FROM admins WHERE email = ?", inputs, null);
            } catch (Exception e) {
                // L
            }
        }
        
        if (correctPassword.moveToFirst()) {
            if (password.equals(correctPassword.getString(0))) {
                db.close();
                correctPassword.close();
                return correctPassword.getInt(1);
            }
        }

        db.close();
        correctPassword.close();
        return -1;

    }

    // TODO
    public void loadEvents() {

        SQLiteDatabase db = this.getReadableDatabase();

        int failed = 0;

        try {
            // db.rawQuery("SELECT id FROM events WHERE ", null, null);
        } catch (Exception e) {
            failed = 1;
        }
    }

    // Registers a student for an event, returns true or false based on success
    public boolean register(int user_id, int event_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("user_id", user_id);
        cv.put("event_id", event_id);

        long success = db.insert("registered", null, cv);

        if (success == -1) {
            db.close();
            return false;
        }

        db.close();
        return true;

    }
}
