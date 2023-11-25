package edu.qc.seclass.rlm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Reminders.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "list_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_LIST = "list_name";


    private static final String REMINDER_TABLE_NAME = "reminder_table";
    private static final String REMINDER_COLUMN_ID = "_id";
    private static final String REMINDER_COLUMN_LIST_ID = "list_id";  // Foreign key linking to list_table
    private static final String REMINDER_COLUMN_NAME = "reminder_name";
    private static final String REMINDER_COLUMN_TYPE = "reminder_type";
    private static final String REMINDER_COLUMN_TIME = "time";
    private static final String REMINDER_COLUMN_DATE = "date";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createListTable(db);
        createReminderTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + REMINDER_TABLE_NAME);
        onCreate(db);
    }

    private void createListTable(SQLiteDatabase db) {
        String createListTableQuery = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LIST + " TEXT);";
        db.execSQL(createListTableQuery);
    }

    private void createReminderTable(SQLiteDatabase db) {
        String createReminderTableQuery = "CREATE TABLE " + REMINDER_TABLE_NAME +
                " (" + REMINDER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                REMINDER_COLUMN_LIST_ID + " INTEGER, " +
                REMINDER_COLUMN_NAME + " TEXT, " +
                REMINDER_COLUMN_TYPE + " TEXT, " +
                REMINDER_COLUMN_TIME + " TEXT, " +
                REMINDER_COLUMN_DATE + " TEXT, " +
                "FOREIGN KEY(" + REMINDER_COLUMN_LIST_ID + ") REFERENCES " + TABLE_NAME + "(" + COLUMN_ID + "));";
        db.execSQL(createReminderTableQuery);
    }

    void addList(String list){
        if (list.isEmpty()) {
            Toast.makeText(context, "List name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LIST, list);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
        }
    }

    void addReminder(int listId, String name, String type, String time, String date) {
        if (name.isEmpty()) {
            Toast.makeText(context, "Reminder name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(REMINDER_COLUMN_LIST_ID, listId);
        cv.put(REMINDER_COLUMN_NAME, name);
        cv.put(REMINDER_COLUMN_TYPE, type);
        cv.put(REMINDER_COLUMN_TIME, time);
        cv.put(REMINDER_COLUMN_DATE, date);

        long result = db.insert(REMINDER_TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add reminder", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Reminder added successfully", Toast.LENGTH_SHORT).show();
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

    Cursor readAllReminders(int listId){
        String query = "SELECT * FROM " + REMINDER_TABLE_NAME +
                " WHERE " + REMINDER_COLUMN_LIST_ID + " = " + listId;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
