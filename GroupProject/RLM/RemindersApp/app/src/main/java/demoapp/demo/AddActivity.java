package demoapp.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText type_input, name_input, num_input;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        type_input = findViewById(R.id.reminder_type);
        name_input = findViewById(R.id.reminder_name);
        num_input = findViewById(R.id.reminder_num);
        button = findViewById(R.id.button);

       button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addReminder(type_input.getText().toString().trim(),
                        name_input.getText().toString().trim(),
                        Integer.valueOf(num_input.getText().toString().trim()));
            }

        });

    }
}