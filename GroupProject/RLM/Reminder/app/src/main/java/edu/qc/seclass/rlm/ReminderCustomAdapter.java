package edu.qc.seclass.rlm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReminderCustomAdapter extends RecyclerView.Adapter<ReminderCustomAdapter.MyViewHolder> {

    Context context;
    //ArrayList reminder_number, reminder_name, reminder_type;
    ArrayList<Long> reminder_number;
    ArrayList<String> reminder_name;
    ArrayList<String> reminder_type;
    MyDatabaseHelper dbHelper;


    ReminderCustomAdapter(Context context, ArrayList reminder_number, ArrayList reminder_name,
                          ArrayList reminder_type){
        this.context = context;
        this.reminder_number = reminder_number;
        this.reminder_name = reminder_name;
        this.reminder_type = reminder_type;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_reminder_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.reminder_id_text.setText(String.valueOf(reminder_number.get(position)));
        holder.reminder_name_text.setText(String.valueOf(reminder_name.get(position)));
        holder.reminder_type_text.setText(String.valueOf(reminder_type.get(position)));
    }

    @Override
    public int getItemCount() {
        return reminder_number.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView reminder_id_text, reminder_name_text, reminder_type_text;
        ImageView optionsMenu;
        CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reminder_id_text = itemView.findViewById(R.id.reminder_id_text);
            reminder_name_text = itemView.findViewById(R.id.reminder_name_text);
            reminder_type_text = itemView.findViewById(R.id.reminder_type_text);
            checkBox = itemView.findViewById(R.id.checkBox);
            optionsMenu = itemView.findViewById(R.id.options_menu);

            optionsMenu.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    showPopupMenu(view,  getAdapterPosition());
                }
            });
        }
    }

    public void showPopupMenu(View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenu().add("Edit");
        popupMenu.getMenu().add("Delete");

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = item.getTitle().toString();
                if ("Edit".equals(title)) {
                    //editItem();
                    return true;
                } else if ("Delete".equals(title)) {
                    deleteItem(position);
                    return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void deleteItem(int position) {
        if (position >= 0 && position < reminder_number.size()) {
            reminder_number.remove(position);
            reminder_name.remove(position);
            reminder_type.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount() - position);
        }
    }


    /*

    private void deleteItem(int position) {
    if (position >= 0 && position < reminder_number.size()) {
        int listId = // Get the listId associated with this reminder

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        dbHelper.deleteReminderById(position, listId);

        reminder_number.remove(position);
        reminder_name.remove(position);
        reminder_type.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }
}


}

     */



}


