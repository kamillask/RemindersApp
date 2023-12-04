package edu.qc.seclass.rlm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    MyDatabaseHelper myDB;

    Context context;
    ArrayList list_id, list_name;
    ArrayList<Long> reminder_id;

    CustomAdapter(Context context, ArrayList list_id, ArrayList list_name){
        this.context = context;
        this.list_id = list_id;
        this.list_name = list_name;
        myDB = new MyDatabaseHelper(context);
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
        ImageView optionsMenu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_id_text = itemView.findViewById(R.id.list_id_text);
            list_name_text = itemView.findViewById(R.id.list_name_text);

            itemView.setOnClickListener(this);

            optionsMenu = itemView.findViewById(R.id.options_menu);

            optionsMenu.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    showPopupMenu(view, getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                int selectedListId = Integer.parseInt(String.valueOf(list_id.get(position)));
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("LIST_ID", selectedListId);
                context.startActivity(intent);
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
                        // Perform edit action
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
            if (position >= 0 && position < list_id.size()) {
                long id = Long.parseLong(list_id.get(position).toString());
                myDB.deleteList(id);

                list_id.remove(position);
                list_name.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount() - position);
            }
        }
    }
}
