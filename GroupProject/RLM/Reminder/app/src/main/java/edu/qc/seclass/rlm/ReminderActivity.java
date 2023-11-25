package edu.qc.seclass.rlm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReminderActivity extends AppCompatActivity {

    private Spinner spinnerRemindertype;
    private EditText editNewReminderype;
    private EditText editTextNewReminder;
    private Switch daySwitch;

    private EditText dayET;

    private EditText dayEditText;

    int listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        spinnerRemindertype = findViewById(R.id.spinnerReminderTypes);
        editNewReminderype = findViewById(R.id.editTextNewReminderType);
        editTextNewReminder = findViewById(R.id.editTextNewReminder);
        daySwitch = findViewById(R.id.daySwitch);
        dayEditText = findViewById(R.id.dayEditText);
        Intent intent = getIntent();
        listId = intent.getIntExtra("LIST_ID", -1);

        storeSpinner();

        spinnerRemindertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedRemindertype = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        daySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    openDayPicker();
                } else {
                    dayEditText.setText("");
                }
            }
        });

        Button btnAddReminderType = findViewById(R.id.button);
        btnAddReminderType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReminderType(v);

            }
        });

        Button btnAddReminder = findViewById(R.id.save);
        btnAddReminder.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //saveReminder(view);
            }
        });

    }

    private void storeSpinner(){
        List<String> reminderType = getReminderTypesFromDB();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, reminderType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRemindertype.setAdapter(adapter);

    }

    private List<String> getReminderTypesFromDB() {
        List<String> manualList = new ArrayList<>();
        manualList.add("meeting");
        manualList.add("appointment");
        return manualList;
    }

    private void openDayPicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int monthOfYear, int dayOfMonth) {
                        String selectedDate = selectedYear + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        dayEditText.setText(selectedDate);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }



    public void addReminderType(View view){
        String newReminderType = editNewReminderype.getText().toString().trim();
        if(!TextUtils.isEmpty(newReminderType)){
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinnerRemindertype.getAdapter();
            adapter.add(newReminderType);
            adapter.notifyDataSetChanged();

            //editNewReminderype.setText("Enter new type");
        }
    }

/*

    public void saveReminder(View view){
        String selectedRT = spinnerRemindertype.getSelectedItem().toString();
        String newReminder = editTextNewReminder.getText().toString().trim();

        MyDatabaseHelper myDB = new MyDatabaseHelper(this);

        if(!TextUtils.isEmpty(newReminder)) {
            long result = myDB.addReminder(selectedRT, newReminder);

            if (result != -1) {
                Toast.makeText(this, "Reminder saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to save reminder", Toast.LENGTH_SHORT).show();
            }
        }
    }

*/


}

