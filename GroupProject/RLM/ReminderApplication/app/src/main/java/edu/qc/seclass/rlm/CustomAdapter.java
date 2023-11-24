package edu.qc.seclass.rlm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    private ArrayList reminder_type, actual_reminder, reminder_list;

    CustomAdapter(Context context,
                  ArrayList reminder_type,
                  ArrayList actual_reminder,
                  ArrayList reminder_list) {
        this.context = context;
        this.reminder_type = reminder_type;
        this.actual_reminder = actual_reminder;
        this.reminder_list= reminder_list;

    }


    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.reminder_type_txt.setText(String.valueOf(reminder_type.get(position)));
        holder.actual_reminder_txt.setText(String.valueOf(actual_reminder.get(position)));
        holder.reminder_list_txt.setText(String.valueOf(reminder_list.get(position)));
    }

    @Override
    public int getItemCount() {
        return reminder_type.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView reminder_type_txt, actual_reminder_txt, reminder_list_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reminder_type_txt = itemView.findViewById(R.id.reminder_type_txt);
            actual_reminder_txt = itemView.findViewById(R.id.reminder_name_txt);
            reminder_list_txt = itemView.findViewById(R.id.reminder_num_txt);
        }
    }
}