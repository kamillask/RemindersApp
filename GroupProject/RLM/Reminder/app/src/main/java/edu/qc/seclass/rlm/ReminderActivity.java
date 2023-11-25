package edu.qc.seclass.rlm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ReminderActivity extends AppCompatActivity {

    int listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        Intent intent = getIntent();
        listId = intent.getIntExtra("LIST_ID", -1);
    }
}