package edu.qc.seclass.rlm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReminderListActivity extends AppCompatActivity {
    private Spinner reminderListSpinner;
    private EditText reminderListET;
    private ArrayAdapter<String> spinnerAdapter;
    private List<String> reminderLists;

    private Button addButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);


        reminderListSpinner = findViewById(R.id.reminderListSpinner);
        reminderListET = findViewById(R.id.editTextText);
        addButton = findViewById(R.id.button);
        saveButton = findViewById(R.id.savebutton);


        setupSpinner();

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                addNewReminderList();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                saveReminderList();
            }
        });


    }

    private void setupSpinner() {
        reminderLists = new ArrayList<>(Arrays.asList("School", "Family", "FirstList"));
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, reminderLists);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reminderListSpinner.setAdapter(spinnerAdapter);

    }

    private void addNewReminderList(){
        String newReminderList = reminderListET.getText().toString().trim();
        if(!newReminderList.isEmpty()){
            spinnerAdapter.add(newReminderList);
            spinnerAdapter.notifyDataSetChanged();
            reminderListET.getText().clear();
        }
    }

    private void saveReminderList() {
        String selectListName = reminderListSpinner.getSelectedItem().toString();
        MyDatabaseHelper mydb = new MyDatabaseHelper(ReminderListActivity.this);

        long result = mydb.addReminderList(selectListName);

        if (result != -1) {
            // Successful insertion
            Toast.makeText(this, "Reminder list saved successfully!", Toast.LENGTH_SHORT).show();
        } else {
            // Failed insertion
            Toast.makeText(this, "Failed to save reminder list", Toast.LENGTH_SHORT).show();
        }
    }

}