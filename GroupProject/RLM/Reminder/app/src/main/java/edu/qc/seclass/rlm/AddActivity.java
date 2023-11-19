package edu.qc.seclass.rlm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class AddActivity extends AppCompatActivity {
    private LinearLayout ll;
    private EditText editReminder;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Switch alertToggle;


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


        b.setOnClickListener(new View.OnClickListener() {
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
    private void addNewReminder() {
        CheckBox cb = new CheckBox(getApplicationContext());
        cb.setText("");


        EditText editText = new EditText(getApplicationContext());
        editText.setText(editReminder.getText().toString());

        LinearLayout checkboxLayout = new LinearLayout(getApplicationContext());
        checkboxLayout.setOrientation(LinearLayout.HORIZONTAL);
        checkboxLayout.addView(cb);
        checkboxLayout.addView(editText);

        ll.addView(checkboxLayout);
    }

    private void handleReminderAlert(){
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();

        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
    }

}