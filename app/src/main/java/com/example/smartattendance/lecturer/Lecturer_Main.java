package com.example.smartattendance.lecturer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;

public class Lecturer_Main extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecturer_main);

        Button start = findViewById(R.id.lecturerMainMenu_Start);
        Button settings = findViewById(R.id.lecturerMainMenu_Settings);

        start.setOnClickListener(view -> openActivity(Lecture_Class_Attending.class));
        settings.setOnClickListener(view -> openActivity(Lecture_Settings.class));
    }
    public void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

}
