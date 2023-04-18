package com.example.befree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity   {
    private EditText email_reg;
    private EditText password_reg;
    private Button regist_reg;
    private Button autor;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
        email_reg = findViewById(R.id.email_reg);
        password_reg = findViewById(R.id.password_reg);
        regist_reg = findViewById(R.id.regist_reg);
        autor = findViewById(R.id.autor);

        regist_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email_reg.getText().toString().isEmpty() || password_reg.getText().toString().isEmpty()){
                    Toast.makeText(Registration.this,"Не заполнены поля",Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(email_reg.getText().toString(),password_reg.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(Registration.this,Main_Menu.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(Registration.this,"Ошибка",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });





    }



}