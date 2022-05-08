package com.example.smartattendance.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;

public class Student_Settings extends AppCompatActivity {
    @Override


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_settings);

        Button logout = findViewById(R.id.student_Settings_Logout);
        Button GoBack = findViewById(R.id.student_Settings_GoBack);
        logout.setOnClickListener(view -> {
            SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("remember", "false");
            editor.apply();
            openActivity(Login.class);

        });
        GoBack.setOnClickListener(view -> openActivity(Student_Main.class));

    }

    public void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
