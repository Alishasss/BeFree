package com.example.befree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<State_Categor>states;
    interface OnStateClickListener{
        void onState(State_Categor state,int position);
    }
    public OnStateClickListener onStateClickListener;
   public StateAdapter(Context context, List<State_Categor>states,OnStateClickListener onStateClickListener){
        this.states = states;
        this.inflater = LayoutInflater.from(context);
        this.onStateClickListener = onStateClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(StateAdapter.ViewHolder holder,int position){
      State_Categor state = states.get(position);
      holder.categorView.setImageResource(state.getIdeaResource());
      holder.nameView.setText(state.getName());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              onStateClickListener.onState(state,position);
          }
      });

    }
    public int getItemCount(){
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView categorView;
        final TextView nameView;
        ViewHolder(View view){
            super(view);
            categorView = view.findViewById(R.id.photo_card);
            nameView = view.findViewById(R.id.name_card);
        }
    }
}
