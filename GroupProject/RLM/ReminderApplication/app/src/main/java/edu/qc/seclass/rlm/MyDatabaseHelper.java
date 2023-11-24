package edu.qc.seclass.rlm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    Reminder reminder = new Reminder();
    private static final String DATABASE_NAME = "ReminderApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_reminderapp";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_LIST_NAME = "reminder_list";
    private static final String COLUMN_LIST_TYPE = "reminder_type";
    private static final String COLUMN_REMINDER = "reminder_name";

    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_TIME = "time";




    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LIST_NAME+ " TEXT, " +
                COLUMN_LIST_TYPE + " TEXT, " +
                COLUMN_REMINDER + " TEXT, " +
                COLUMN_DAY + " TEXT, "+
                COLUMN_TIME + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addReminder(String type, String actualReminder, String day, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MyDatabaseHelper.COLUMN_LIST_NAME, reminder.getListName());
        cv.put(MyDatabaseHelper.COLUMN_LIST_TYPE, reminder.getReminderType());
        cv.put(MyDatabaseHelper.COLUMN_REMINDER, reminder.getReminder());
        cv.put(MyDatabaseHelper.COLUMN_DAY, reminder.getDay());
        cv.put(MyDatabaseHelper.COLUMN_TIME, reminder.getTime());

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }


    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor getAllReminders() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    long addReminderType(String reminderType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LIST_TYPE, reminderType);

        //cv.put(MyDatabaseHelper.COLUMN_LIST_NAME, listName);
        cv.put(MyDatabaseHelper.COLUMN_LIST_TYPE, reminderType);
        //cv.put(MyDatabaseHelper.COLUMN_REMINDER, actualReminder);
        //cv.put(MyDatabaseHelper.COLUMN_DAY, day);
        //cv.put(MyDatabaseHelper.COLUMN_TIME, time);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add reminder type", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Reminder type added successfully", Toast.LENGTH_SHORT).show();
        }
        db.close();
        return result;
    }

    long addReminderList(String reminderList){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LIST_NAME, reminderList);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add reminder type", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Reminder List added successfully", Toast.LENGTH_SHORT).show();
        }
        db.close();
        return result;
    }

    long addReminderDay(String reminderDay){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

       // cv.put(COLUMN_LIST_NAME, reminderList);
        cv.put(COLUMN_DAY, reminderDay);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add reminder list", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Reminder List added successfully", Toast.LENGTH_SHORT).show();
        }

        db.close();
        return result;
    }

}


