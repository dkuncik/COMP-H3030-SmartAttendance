package com.example.smartattendance;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
    Button login;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        login = (Button) findViewById(R.id.main_Login_Login);
        username = (EditText) findViewById(R.id.main_Login_StudentID);
        password = (EditText) findViewById(R.id.main_Login_Password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Logging you in...", Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.lecturer_main);
                } else if (username.getText().toString().equals("student") && password.getText().toString().equals("student")) {
                    Toast.makeText(getApplicationContext(), "Logging you in...", Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.student_main);
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect ID or PASSWORD", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

