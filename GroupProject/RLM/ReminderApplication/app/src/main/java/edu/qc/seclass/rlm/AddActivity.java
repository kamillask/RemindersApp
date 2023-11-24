package edu.qc.seclass.rlm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class AddActivity extends AppCompatActivity {
    private LinearLayout ll;
    private EditText editReminder;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Switch alertToggle;

    private int selectedYear, selectedMonth, selectedDay;
    private int selectedHour, selectedMinute;

    EditText reminder_type, actualReminder;
    Button add_button;

    Button saveButton;

    private List<CheckBox> dynamicCB = new ArrayList<>();
    private int checkboxCounter = 1;

    private List<EditText> dynamicReminderNames = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editReminder = findViewById(R.id.editReminder);
        ll = findViewById(R.id.checkboxContainer);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        alertToggle = findViewById(R.id.alertToggle);
        saveButton = findViewById(R.id.btnSave);

        Button addReminderButton = findViewById(R.id.btnAddReminder);
        reminder_type = findViewById(R.id.editReminder);
        add_button = findViewById(R.id.btnAddReminder);

        // Set up DatePicker listener
        datePicker.init(
                datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectedYear = year;
                        selectedMonth = monthOfYear;
                        selectedDay = dayOfMonth;
                    }
                }
        );

        addReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewReminder();
            }
        });

        alertToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int visibility = isChecked ? View.VISIBLE : View.GONE;
                datePicker.setVisibility(visibility);
                timePicker.setVisibility(visibility);

                if (!isChecked) {
                    selectedYear = 0;
                    selectedMonth = 0;
                    selectedDay = 0;
                    selectedHour = 0;
                    selectedMinute = 0;
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveReminder();
                retrieveData();
            }
        });

        TextView addReminderList = findViewById(R.id.addReminderList);
        addReminderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, ReminderListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void retrieveData() {
        MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);


        Cursor cursor = myDB.getAllReminders();

        if (cursor.getCount() == 0) {

            return;
        }


        while (cursor.moveToNext()) {
            int reminderId = cursor.getInt(0);
            String reminderName = cursor.getString(1);
            String reminderType = cursor.getString(2);
            String reminderDay = cursor.getString(3);
            String reminderTime = cursor.getString(4);


            TextView textView = new TextView(getApplicationContext());
            textView.setText("ID: " + reminderId + "\nName: " + reminderName +
                    "\nType: " + reminderType + "\nDay: " + reminderDay + "\nTime: " + reminderTime);

            ll.addView(textView);
        }

        cursor.close();
    }

    private void saveReminder() {
        String reminderTime = selectedYear + " - " +
                (selectedMonth + 1) + "-" +
                selectedDay + " " +
                selectedHour + ":" +
                selectedMinute;


    }

    private void saveData() {
        MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

        // Save reminder type
        String reminderType = reminder_type.getText().toString().trim();
        long reminderTypeId = myDB.addReminderType(reminderType);
        myDB.addReminderType(reminderType);

        // Save reminders
        for (int i = 0; i < dynamicCB.size(); i++) {
            CheckBox checkBox = dynamicCB.get(i);
            EditText actualReminder = dynamicReminderNames.get(i);
            String reminderDay = getDatefromDatePicker(datePicker);
            String reminderName = actualReminder.getText().toString().trim();
            String reminderTime = selectedHour + ":" +
                    selectedMinute;

            if (checkBox.isChecked()) {
                //String reminderName = actualReminder.getText().toString().trim();
                //String reminderDay = getDatefromDatePicker(datePicker);
                //String reminderTime = selectedHour + ":" selectedMinute;


                myDB.addReminder(reminderName, reminderType, reminderDay, reminderTime);
                myDB.addReminderDay(reminderDay);
            }
            myDB.addReminder(reminderName, reminderType, reminderDay, reminderTime);
        }


    }
    private String getDatefromDatePicker (DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();

        return year + " - " + month + " - " + day;
    }

    private void addNewReminder() {

        CheckBox cb = new CheckBox(getApplicationContext());
        //int uniqueCheckBoxId = generateUniqueBox();
        //cb.setId(uniqueCheckBoxId);
        cb.setText("");

        EditText editText = new EditText(getApplicationContext());
        //int uniqueEditTextId = generateUniqueBox();
        //editText.setId(uniqueEditTextId);
        editText.setHint("Reminder Name");


        dynamicCB.add(cb);
        dynamicReminderNames.add(editText);


        LinearLayout reminderLayout = new LinearLayout(getApplicationContext());
        reminderLayout.setOrientation(LinearLayout.HORIZONTAL);
        reminderLayout.addView(cb);
        reminderLayout.addView(editText);


        ll.addView(reminderLayout);






    }

}

/*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button b = findViewById(R.id.btnAddReminder);
        editReminder = findViewById(R.id.editReminder);
        ll = findViewById(R.id.checkboxContainer);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        alertToggle = findViewById(R.id.alertToggle);

        saveButton = findViewById(R.id.btnSave);
        datePicker = findViewById(R.id.datePicker);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                saveData();
            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewReminder();
                //implementDb();

            }
        });



        alertToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int visibility = isChecked ? View.VISIBLE : View.GONE;
                datePicker.setVisibility(visibility);
                timePicker.setVisibility(visibility);

                if(!isChecked) {
                    selectedYear = 0;
                    selectedMonth = 0;
                    selectedDay = 0;
                    selectedHour = 0;
                    selectedMinute = 0;
                }

                datePicker.init(
                        datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        new DatePicker.OnDateChangedListener() {
                            @Override
                            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                selectedYear = year;
                                selectedMonth = monthOfYear;
                                selectedDay = dayOfMonth;
                            }


                        }
                );
                timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute){
                        selectedHour = hourOfDay;
                        selectedMinute = minute;
                    }
                });
            }





        });

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                saveReminder();
            }
        });



        TextView addReminderList = findViewById(R.id.addReminderList);
        addReminderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, ReminderListActivity.class);
                startActivity(intent);
            }
        });


        reminder_type = findViewById(R.id.editReminder);
        add_button = findViewById(R.id.btnAddReminder);

        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

                for(int i = 0; i < dynamicCB.size(); i++){
                    CheckBox checkBox = dynamicCB.get(i);
                    EditText actualReminder = dynamicReminderNames.get(i);

                    if(checkBox.isChecked()) {
                        String reminderName = actualReminder.getText().toString().trim();
                        String reminderType = reminder_type.getText().toString().trim();
                        String reminderDay = selectedYear + "-" +
                                (selectedMonth + 1) + "-" +
                                selectedDay;

                        String reminderTime = selectedHour + ":" +
                                selectedMinute;


                        myDB.addReminder(reminderName, reminderType, reminderDay, reminderTime);
                    }
                }
                retrieveData();
            }

        });
    }


    private void addNewReminder() {

        CheckBox cb = new CheckBox(getApplicationContext());
        int uniqueCheckBoxId = generateUniqueBox();
        cb.setId(uniqueCheckBoxId);
        cb.setText("");

        EditText editText = new EditText(getApplicationContext());
        int uniqueEditTextId = generateUniqueBox();
        editText.setId(uniqueEditTextId);
        editText.setHint("Reminder Name");


        dynamicCB.add(cb);
        dynamicReminderNames.add(editText);


        LinearLayout reminderLayout = new LinearLayout(getApplicationContext());
        reminderLayout.setOrientation(LinearLayout.HORIZONTAL);
        reminderLayout.addView(cb);
        reminderLayout.addView(editText);


        ll.addView(reminderLayout);






    }



    private int generateUniqueBox(){
        return checkboxCounter++;
    }



    private void retrieveData(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);




        Cursor cursor = myDB.getAllReminders();

        if (cursor.getCount() == 0) {

            return;
        }


        while (cursor.moveToNext()) {
            int reminderId = cursor.getInt(0);
            String reminderName = cursor.getString(1);
            String reminderType = cursor.getString(2);
            String reminderDay = cursor.getString(3);
            String reminderTime = cursor.getString(4);


            TextView textView = new TextView(getApplicationContext());
            textView.setText("ID: " + reminderId + "\nName: " + reminderName +
                    "\nType: " + reminderType + "\nDay: " + reminderDay + "\nTime: " + reminderTime);

            ll.addView(textView);
        }

        cursor.close();
    }

    public void implementDb(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

        for(int i = 0; i <dynamicCB.size(); i++){
            CheckBox checkBox = dynamicCB.get(i);
            EditText actualReminder = dynamicReminderNames.get(i);

            if(checkBox.isChecked()){
                String reminderName = actualReminder.getText().toString().trim();
                String reminderType = reminder_type.getText().toString().trim();
                String reminderDay = selectedYear + "-" +
                        (selectedMonth + 1) + "-" + selectedDay;

                String reminderTime = selectedHour + ":" + selectedMinute;

                myDB.addReminder(reminderName, reminderType, reminderDay, reminderTime);
            }
        }

    }







    private void handleReminderAlert(){
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();

        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
    }

    private void saveReminder() {
        String reminderTime = selectedYear + " - " +
                (selectedMonth + 1) + "-" +
                selectedDay + " " +
                selectedHour + ":" +
                selectedMinute;


    }

    private void saveData() {
        MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

        // Save reminder type
        String reminderType = reminder_type.getText().toString().trim();
        long reminderTypeId = myDB.addReminderType(reminderType);
        myDB.addReminderType(reminderType);

        // Save reminders
        for (int i = 0; i < dynamicCB.size(); i++) {
            CheckBox checkBox = dynamicCB.get(i);
            EditText actualReminder = dynamicReminderNames.get(i);
            String reminderDay = getDatefromDatePicker(datePicker);
            String reminderName = actualReminder.getText().toString().trim();
            String reminderTime = selectedHour + ":" +
                    selectedMinute;

            if (checkBox.isChecked()) {
                //String reminderName = actualReminder.getText().toString().trim();
                //String reminderDay = getDatefromDatePicker(datePicker);
                //String reminderTime = selectedHour + ":" selectedMinute;


                myDB.addReminder(reminderName, reminderType, reminderDay, reminderTime);
            }
            myDB.addReminder(reminderName, reminderType, reminderDay, reminderTime);
        }



        Toast.makeText(AddActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
    }

    private String getDatefromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();

        return year + " - " + month + " - " + day;
    }



}


*/
