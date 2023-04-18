package com.example.befree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Fragment_Home extends Fragment {

    public Fragment_Home(){
    }
    public static Fragment_Home newInstance(){
        return new Fragment_Home();
    }
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.activity_fragment_home,container,false);
        Button mytask = (Button) view.findViewById(R.id.mytask);
        Button categor = (Button) view.findViewById(R.id.categor);
        categor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Categories_idea.class);
                startActivity(intent);
            }
        });
        mytask.setOnClickListener(new View.OnClickListener() {
         @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),NoteDetalis.class);
                startActivity(intent);
           }
        });

    return view;

    }



}