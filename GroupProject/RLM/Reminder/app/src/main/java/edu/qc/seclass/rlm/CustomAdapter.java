package edu.qc.seclass.rlm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList list_id, list_name;

    CustomAdapter(Context context, ArrayList list_id, ArrayList list_name){
        this.context = context;
        this.list_id = list_id;
        this.list_name = list_name;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.list_id_text.setText(String.valueOf(list_id.get(position)));
        holder.list_name_text.setText(String.valueOf(list_name.get(position)));
    }

    @Override
    public int getItemCount() {
        return list_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView list_id_text, list_name_text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_id_text = itemView.findViewById(R.id.list_id_text);
            list_name_text = itemView.findViewById(R.id.list_name_text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);
            }
        }
    }
}
