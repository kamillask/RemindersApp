package edu.qc.seclass.rlm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ReminderActivity extends AppCompatActivity {

    private Spinner spinnerRemindertype;
    private EditText editNewRemindertype;
    private EditText editTextNewReminder;
    private Switch daySwitch;

    private Switch timeSwitch;

    private EditText timeEditText;

    private EditText dayEditText;
    Button btnAddReminderType;
    Button save_button;

    int listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        spinnerRemindertype = findViewById(R.id.spinnerReminderTypes); //
        editNewRemindertype = findViewById(R.id.editTextNewReminderType);
        editTextNewReminder = findViewById(R.id.editTextNewReminder); //
        daySwitch = findViewById(R.id.daySwitch);
        timeSwitch = findViewById(R.id.timeSwitch);
        timeEditText = findViewById(R.id.timeEditText);
        dayEditText = findViewById(R.id.dayEditText); //
        btnAddReminderType = findViewById(R.id.button);
        save_button = findViewById(R.id.save);
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

        timeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    openTimePicker();
                } else {
                    timeEditText.setText("");
                }
            }
        });



        final String originalHint = editNewRemindertype.getHint().toString().trim();
        btnAddReminderType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editNewRemindertype.getText().toString().trim().isEmpty()) {
                    addReminderType(v);
                    editNewRemindertype.getText().clear();
                    editNewRemindertype.setHint(originalHint);
                }
                else{
                    Toast.makeText(ReminderActivity.this, "Text is empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        save_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(ReminderActivity.this);
                myDB.addReminder(listId, editTextNewReminder.getText().toString().trim(),
                        spinnerRemindertype.getSelectedItem().toString().trim(), dayEditText.getText().toString().trim(),
                        timeEditText.getText().toString().trim());

                finish();
            }
        });

    }

    private void storeSpinner(){
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        List<String> reminderType = dbHelper.getReminderTypesFromDB();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, reminderType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRemindertype.setAdapter(adapter);
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

    private void openTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute){
                        String selectedTime = String.format(Locale.getDefault(),"%02d:%02d", selectedHour, selectedMinute);
                        timeEditText.setText(selectedTime);
                    }
                },
                hour, minute, false
        );
        timePickerDialog.show();
    }

    public void addReminderType(View view){
        String newReminderType = editNewRemindertype.getText().toString().trim();
        if(!TextUtils.isEmpty(newReminderType)){
            MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
            dbHelper.addReminderType(newReminderType);
            List<String> reminderType = dbHelper.getReminderTypesFromDB();
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinnerRemindertype.getAdapter();
            adapter.clear();
            adapter.addAll(reminderType);
            adapter.notifyDataSetChanged();
        }
    }

}

