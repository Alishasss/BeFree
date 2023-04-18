package com.example.befree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Love extends AppCompatActivity {
    ImageView nazad2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        nazad2 = (ImageView) findViewById(R.id.nazad2);
        nazad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Love.this,Categories_idea.class);
                startActivity(intent);
                finish();
            }
        });
    }
}