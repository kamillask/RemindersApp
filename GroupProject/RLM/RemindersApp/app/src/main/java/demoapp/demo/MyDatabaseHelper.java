package demoapp.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ReminderApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_reminderapp";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TYPE = "reminder_type";
    private static final String COLUMN_NAME = "reminder_name";
    private static final String COLUMN_NUMBER = "reminder_num";




    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TYPE + "TEXT, " +
                        COLUMN_NAME + "TEXT, " +
                        COLUMN_NUMBER + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addReminder(String type, String name, int num){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_NUMBER, num);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }


    }
}
