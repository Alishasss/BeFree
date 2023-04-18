package com.example.befree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Categories_idea extends AppCompatActivity {
    ArrayList<State_Categor> states = new ArrayList<State_Categor>();
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_idea);
        setInitialData();
        StateAdapter.OnStateClickListener stateClickListener = new StateAdapter.OnStateClickListener() {
            @Override
            public void onState(State_Categor state, int position) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(Categories_idea.this,Self.class);
                        startActivity(intent);
                        finish();
                    case 1:
                        Intent i = new Intent(Categories_idea.this,Love.class);
                        startActivity(i);
                        finish();




                }
            }
        };
        RecyclerView recyclerView = findViewById(R.id.recyc);
        StateAdapter adapter = new StateAdapter(this,states,stateClickListener);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Categories_idea.this,Fragment_Home.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setInitialData() {
        states.add(new State_Categor("Саморазвитие",R.drawable.poc1));
        states.add(new State_Categor("Любовь",R.drawable.poc2));
        states.add(new State_Categor("Работа",R.drawable.poc3));
        states.add(new State_Categor("Мотивация",R.drawable.poc4));
        states.add(new State_Categor("Жизнь",R.drawable.poc6));
        states.add(new State_Categor("Мир",R.drawable.poc5));
        states.add(new State_Categor("Красота",R.drawable.poc7));
        states.add(new State_Categor("Надежда",R.drawable.poc8));
        states.add(new State_Categor("Музыка",R.drawable.poc9));
        states.add(new State_Categor("Мудрость",R.drawable.poc10));
        states.add(new State_Categor("Успех",R.drawable.poc11));
        states.add(new State_Categor("Знания",R.drawable.poc12));
        states.add(new State_Categor("Искусство",R.drawable.poc13));
        states.add(new State_Categor("Интеллект",R.drawable.poc14));

    }
}