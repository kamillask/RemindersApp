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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> list_id, list_name;
    CustomAdapter customadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        list_id = new ArrayList<>();
        list_name = new ArrayList<>();

        storeDataInArrays();

        customadapter = new CustomAdapter(MainActivity.this, list_id, list_name);
        recyclerView.setAdapter(customadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                list_id.add(cursor.getString(0));
                list_name.add(cursor.getString(1));
            }
        }
    }

    private void showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        Menu menu = popupMenu.getMenu();
        menu.add(Menu.NONE, 1, Menu.NONE, "New List");
        menu.add(Menu.NONE, 2, Menu.NONE, "New Reminder");

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1:
                        startActivity(new Intent(MainActivity.this, AddActivity.class));
                        return true;
                    case 2:
                        startActivity(new Intent(MainActivity.this, AddActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }
}