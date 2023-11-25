package edu.qc.seclass.rlm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    RecyclerView recyclerView2;
    FloatingActionButton add_reminder;

    MyDatabaseHelper myDB;
    ArrayList<String> reminder_id, reminder_number, reminder_name, reminder_type, reminder_time, reminder_date;
    int listId;
    ReminderCustomAdapter reminderCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView2 = findViewById(R.id.recyclerView2);
        add_reminder = findViewById(R.id.add_reminder);
        Intent intent = getIntent();
        listId = intent.getIntExtra("LIST_ID", -1);
        add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, ReminderActivity.class);
                intent.putExtra("LIST_ID", listId);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(DetailActivity.this);
        reminder_number = new ArrayList<>();
        reminder_name = new ArrayList<>();
        reminder_type = new ArrayList<>();
        //reminder_time = new ArrayList<>();
        //reminder_date = new ArrayList<>();

        storeReminderInArrays();

        reminderCustomAdapter = new ReminderCustomAdapter(DetailActivity.this, reminder_number,
                reminder_name, reminder_type);
        recyclerView2.setAdapter(reminderCustomAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
    }

    void storeReminderInArrays(){
        Cursor cursor = myDB.readAllReminders(listId);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                reminder_number.add(cursor.getString(2));
                reminder_name.add(cursor.getString(3));
                reminder_type.add(cursor.getString(4));
                //reminder_time.add(cursor.getString(5));
                //reminder_date.add(cursor.getString(6));
            }
        }
    }
}