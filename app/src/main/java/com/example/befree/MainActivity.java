package com.example.befree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.browseractions.BrowserActionsIntent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText email_auto;
    private EditText password_auto;
    private Button input;
    private Button regist;

  private FirebaseAuth mAuth;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email_auto = findViewById(R.id.email_auto);
        password_auto = findViewById(R.id.passworda_auto);
        input = findViewById(R.id.input);
        regist = findViewById(R.id.regist);

        mAuth = FirebaseAuth.getInstance();

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email_auto.getText().toString().isEmpty() || password_auto.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Не заполнены поля",Toast.LENGTH_SHORT).show();
                }
                else{
                  mAuth.signInWithEmailAndPassword(email_auto.getText().toString(),password_auto.getText().toString())
                          .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {
                                 if(task.isSuccessful()){
                                     Intent intent = new Intent(MainActivity.this,Main_Menu.class);
                                     startActivity(intent);

                                 }
                                 else {
                                     Toast.makeText(MainActivity.this,"Ошибка",Toast.LENGTH_SHORT).show();
                                 }
                              }
                          });
                }
            }
        });


    }





}