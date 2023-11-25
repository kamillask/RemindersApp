package edu.qc.seclass.rlm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReminderCustomAdapter extends RecyclerView.Adapter<ReminderCustomAdapter.MyViewHolder> {

    Context context;
    ArrayList reminder_number, reminder_name, reminder_type;

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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reminder_id_text = itemView.findViewById(R.id.reminder_id_text);
            reminder_name_text = itemView.findViewById(R.id.reminder_name_text);
            reminder_type_text = itemView.findViewById(R.id.reminder_type_text);
        }
    }
}