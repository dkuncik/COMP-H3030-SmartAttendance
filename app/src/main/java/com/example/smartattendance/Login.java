package com.example.smartattendance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.lecturer.Lecturer_Main;
import com.example.smartattendance.student.Student_Main;

public class Login extends AppCompatActivity {
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        Button login = (Button) findViewById(R.id.main_Login_Login);
        username = (EditText) findViewById(R.id.main_Login_StudentID);
        password = (EditText) findViewById(R.id.main_Login_Password);

        login.setOnClickListener(view -> {
                    if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                        Toast.makeText(getApplicationContext(), "Logging you in...", Toast.LENGTH_SHORT).show();
                        openActivity(Lecturer_Main.class);
                    } else if (username.getText().toString().equals("student") && password.getText().toString().equals("student")) {
                        Toast.makeText(getApplicationContext(), "Logging you in...", Toast.LENGTH_SHORT).show();
                        openActivity(Student_Main.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect ID or PASSWORD", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

}

