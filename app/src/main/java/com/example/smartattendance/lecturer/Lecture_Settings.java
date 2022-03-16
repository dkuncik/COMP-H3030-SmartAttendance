package com.example.smartattendance.lecturer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.Login;
import com.example.smartattendance.R;

public class Lecture_Settings extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecturer_settings);

        Button logout = findViewById(R.id.lecturer_Settings_Logout);
        logout.setOnClickListener(view -> {
            openActivity(Login.class);
            finish();
        });
    }

    public void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
