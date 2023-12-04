package edu.qc.seclass.rlm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private static final String REMINDER_COLUMN_NUMBER = "number"; // need to remove


    private static final String REMINDER_TYPE_TABLE_NAME = "reminder_type_table";
    private static final String REMINDER_TYPE_COLUMN_ID = "_id";
    private static final String REMINDER_TYPE_COLUMN_NAME = "type_name";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createListTable(db);
        createReminderTable(db);
        createReminderTypeTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + REMINDER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + REMINDER_TYPE_TABLE_NAME);
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
                REMINDER_COLUMN_NUMBER + " INTEGER, " +
                REMINDER_COLUMN_NAME + " TEXT, " +
                REMINDER_COLUMN_TYPE + " TEXT, " +
                REMINDER_COLUMN_TIME + " TEXT, " +
                REMINDER_COLUMN_DATE + " TEXT, " +
                "FOREIGN KEY(" + REMINDER_COLUMN_LIST_ID + ") REFERENCES " + TABLE_NAME + "(" + COLUMN_ID + "));";
        db.execSQL(createReminderTableQuery);
    }

    private void createReminderTypeTable(SQLiteDatabase db){
        String createReminderTypeTableQuery = "CREATE TABLE " + REMINDER_TYPE_TABLE_NAME +
                " (" + REMINDER_TYPE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                REMINDER_TYPE_COLUMN_NAME + " TEXT);";
        db.execSQL(createReminderTypeTableQuery);

        insertDefaultReminderTypes(db);
    }

    private void insertDefaultReminderTypes(SQLiteDatabase db) {
        List<String> defaultTypes = Arrays.asList("meeting", "appointment");

        for (String type : defaultTypes) {
            ContentValues cv = new ContentValues();
            cv.put(REMINDER_TYPE_COLUMN_NAME, type);
            db.insert(REMINDER_TYPE_TABLE_NAME, null, cv);
        }
    }

    public List<String> getReminderTypesFromDB() {
        List<String> reminderTypes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + REMINDER_TYPE_COLUMN_NAME + " FROM " + REMINDER_TYPE_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndex(REMINDER_TYPE_COLUMN_NAME);
            if (columnIndex != -1) {
                String type = cursor.getString(columnIndex);
                reminderTypes.add(type);
            }
        }
        return reminderTypes;
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

        String maxIdQuery = "SELECT COALESCE(MAX(" + REMINDER_COLUMN_NUMBER + "), 0) FROM " + REMINDER_TABLE_NAME +
                " WHERE " + REMINDER_COLUMN_LIST_ID + " = " + listId;
        Cursor cursor = db.rawQuery(maxIdQuery, null);
        int maxId = 0;
        if (cursor.moveToFirst()) {
            maxId = cursor.getInt(0);
        }
        int newNumber = maxId + 1;

        ContentValues cv = new ContentValues();
        cv.put(REMINDER_COLUMN_LIST_ID, listId);
        cv.put(REMINDER_COLUMN_NUMBER, newNumber);
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

    public void addReminderType(String reminderType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(REMINDER_TYPE_COLUMN_NAME, reminderType);

        long result = db.insert(REMINDER_TYPE_TABLE_NAME, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Failed to add reminder type", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Reminder type added successfully", Toast.LENGTH_SHORT).show();
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

    public void deleteReminderById(long reminderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(REMINDER_TABLE_NAME, REMINDER_COLUMN_ID + "=?",
                new String[]{String.valueOf(reminderId)});
        db.close();
    }

    public void deleteList(long listId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(listId)});
        db.delete(REMINDER_TABLE_NAME, REMINDER_COLUMN_LIST_ID + "=?", new String[]{String.valueOf(listId)});
        db.close();
    }



/*


    public ArrayList<Long> getReminderNumbers() {
        ArrayList<Long> reminderNumbers = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + REMINDER_COLUMN_NUMBER + " FROM " + REMINDER_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndex(REMINDER_COLUMN_NUMBER);
            if (columnIndex != -1) {
                long number = cursor.getLong(columnIndex);
                reminderNumbers.add(number);
            }
        }

        cursor.close();
        return reminderNumbers;
    }

    public ArrayList<String> getReminderNames() {
        ArrayList<String> reminderNames = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + REMINDER_COLUMN_NAME + " FROM " + REMINDER_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndex(REMINDER_COLUMN_NAME);
            if (columnIndex != -1) {
                String name = cursor.getString(columnIndex);
                reminderNames.add(name);
            }
        }

        cursor.close();
        return reminderNames;
    }

    public ArrayList<String> getReminderTypes() {
        ArrayList<String> reminderTypes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + REMINDER_COLUMN_TYPE + " FROM " + REMINDER_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndex(REMINDER_COLUMN_TYPE);
            if (columnIndex != -1) {
                String type = cursor.getString(columnIndex);
                reminderTypes.add(type);
            }
        }

        cursor.close();
        return reminderTypes;
    }


 */



}
