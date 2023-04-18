package com.example.befree;


import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener {
    ItemClickListener itemClickListener;
    TextView item_title,item_descr;

    public ListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

        item_title = (TextView) itemView.findViewById(R.id.item_title);
        item_descr = (TextView) itemView.findViewById(R.id.item_descr);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
         contextMenu.setHeaderTitle("Выберите действие");
         contextMenu.add(0,0,getAdapterPosition(),"Удалить");
    }
}
public class Adapter extends RecyclerView.Adapter<ListItemViewHolder> {
    NoteDetalis mainActivity;
    List<ToDo> todoList;

    public Adapter(NoteDetalis mainActivity, List<ToDo> todoList) {
        this.mainActivity = mainActivity;
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity.getBaseContext());
        View view = inflater.inflate(R.layout.list_itemtask,parent,false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {



        holder.item_title.setText(todoList.get(position).getTitle());
        holder.item_descr.setText(todoList.get(position).getDescription());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                mainActivity.title.setText(todoList.get(position).getTitle());
                mainActivity.description.setText(todoList.get(position).getDescription());

                mainActivity.isUpdate = true;
                mainActivity.idUpdate = todoList.get(position).getId();
            }
        });

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
