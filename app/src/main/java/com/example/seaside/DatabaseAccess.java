package com.example.seaside;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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
    public long addUser(String name, String email, String password, String type) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("email", email);
        cv.put("password", password);

        String[] arguments = {email};
        Cursor student_ids;
        Cursor admin_ids;
        try {
            student_ids = db.rawQuery("SELECT id FROM students WHERE email = ?", arguments, null);
            admin_ids = db.rawQuery("SELECT id FROM admins WHERE email = ?", arguments, null);
        } catch (Exception e) {
            db.close();
            return -1;
        }

        if (!student_ids.moveToFirst() && !admin_ids.moveToFirst()) {
            long success;
            try {
                success = db.insert(type, null, cv);
            } catch (Exception e) {
                student_ids.close();
                admin_ids.close();
                db.close();
                return -1;
            }
            student_ids.close();
            admin_ids.close();
            db.close();
            return success;
        }

        student_ids.close();
        admin_ids.close();
        db.close();
        return -2;

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

        long success;
        try {
            success = db.insert("events", null, cv);
        } catch (Exception e) {
            success = -1;
        }

        if (success == -1) {
            db.close();
            return false;
        }

        db.close();
        return true;

    }

    // Checks a user's login, type is either "students" or "admins", returns the user's id or -1 if failed
    public boolean checkLogin(String email, String password) {

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
                return true;
            }
        }

        db.close();
        correctPassword.close();
        return false;

    }

    // Gets the id of each event and returns it
    public java.util.ArrayList<Integer> loadEvents() {

        SQLiteDatabase db = this.getReadableDatabase();

        java.util.ArrayList<Integer> ids = new java.util.ArrayList<Integer>();

        Cursor events;
        try {
            events = db.rawQuery("SELECT id FROM events", null, null);
        } catch (Exception e) {
            ids.add(-1);
            db.close();
            return ids;
        }

        if (!events.moveToFirst()) {
            ids.add(-1);
            events.close();
            db.close();
            return ids;
        }

        do {
            ids.add(events.getInt(0));
        } while (events.moveToNext());

        if (ids.isEmpty()) {
            ids.add(-1);
        }

        events.close();
        db.close();
        return ids;

    }

    // Registers a student for an event, returns true or false based on success
    public boolean register(int user_id, int event_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("user_id", user_id);
        cv.put("event_id", event_id);

        long success;
        try {
            success = db.insert("registered", null, cv);
        } catch (Exception e) {
            success = -1;
        }

        if (success == -1) {
            db.close();
            return false;
        }

        db.close();
        return true;

    }

    // Returns the title, description, location, time, # of volunteers, and # of registered users, returns null if failed
    public EventInfo eventInfo(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] arguments = {Integer.toString(id)};

        Cursor information, registered;
        try {
            information = db.rawQuery("SELECT title, description, location, time, volunteers FROM events WHERE id = ?", arguments, null);
            registered = db.rawQuery("SELECT COUNT(*) FROM registered WHERE event_id = ?", arguments, null);
        } catch(Exception e) {
            return null;
        }

        if (information.moveToFirst() && registered.moveToFirst() && information.getString(0).compareTo("0") != 0) {
            EventInfo info = null;
            String[] when = information.getString(3).split("&");

            info.title = information.getString(0);
            info.description = information.getString(1);
            info.location = information.getString(2);
            info.time = when[0];
            info.date = when[1];
            info.volunteers = information.getInt(4);
            info.registered = registered.getInt(0);
            return info;
        }

        return null;

    }

    public class EventInfo {

        String title;
        String description;
        String location;
        String time;
        String date;
        int volunteers;
        int registered;

        public EventInfo(String title, String description, String location, String time, String date, int volunteers, int registered) {
            this.title = title;
            this.description = description;
            this.location = location;
            this.time = time;
            this.date = date;
            this.volunteers = volunteers;
            this.registered = registered;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getVolunteers() {
            return volunteers;
        }

        public void setVolunteers(int volunteers) {
            this.volunteers = volunteers;
        }

        public int getRegistered() {
            return registered;
        }

        public void setRegistered(int registered) {
            this.registered = registered;
        }
    }
}
