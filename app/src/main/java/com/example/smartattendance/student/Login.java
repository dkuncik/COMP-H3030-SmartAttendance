package com.example.smartattendance.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;


public class Login extends AppCompatActivity {
    private EditText username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        Button login = findViewById(R.id.main_Login_Login);
        CheckBox remember = findViewById(R.id.rememberMe);
        username = findViewById(R.id.main_Login_StudentID);
        password = findViewById(R.id.main_Login_Password);
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if (checkbox.equals("true")){
            Intent intent = new Intent(Login.this,Student_Main.class);
            startActivity(intent);
        }
        else if(!checkbox.equals("false")){
           Toast.makeText(this,"Please Sign in ",Toast.LENGTH_SHORT).show();
        }


        login.setOnClickListener(view -> {

            if (username.getText().toString().equals("s") && password.getText().toString().equals("s")) {
                Intent intent = new Intent(Login.this ,Student_Main.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Incorrect ID or PASSWORD", Toast.LENGTH_SHORT).show();
            }            });


        remember.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()){
                SharedPreferences preferences1 = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putString("remember","true");
                editor.apply();
            }
            else if (!compoundButton.isChecked()) {
                SharedPreferences preferences1 = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putString("remember","false");
                editor.apply();

            }
        });
    }


}

