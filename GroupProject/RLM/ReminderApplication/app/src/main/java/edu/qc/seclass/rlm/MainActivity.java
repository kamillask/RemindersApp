package edu.qc.seclass.rlm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    //MyDatabaseHelper myDB;
    ArrayList<String> reminder_type, actual_reminder, reminder_list;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);


        add_button.setOnClickListener((View view) -> {
                    Intent intent = new Intent(MainActivity.this, AddActivity.class);
                    startActivity(intent);

                }
        );

        reminder_type = new ArrayList<>();
        actual_reminder = new ArrayList<>();
        reminder_list = new ArrayList<>();

        customAdapter = new CustomAdapter(MainActivity.this, reminder_type, actual_reminder, reminder_list);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }
}