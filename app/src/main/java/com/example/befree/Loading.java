package com.example.befree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Loading extends AppCompatActivity {
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        linearLayout = (LinearLayout) findViewById(R.id.pic);
        Thread welcom = new Thread(){
            @Override
            public void run(){
                try{
                    super.run();
                    sleep(4000);
                } catch (Exception e){}
                finally {
                    Intent i = new Intent(Loading.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcom.start();
    }
}