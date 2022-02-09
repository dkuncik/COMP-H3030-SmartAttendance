package com.example.smartattendance.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;

public class Student_Main extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);

        Button start = findViewById(R.id.studentMainMenu_ScanTag);
        Button timetable = findViewById(R.id.studentMainMenu_Timetable);
        Button settings = findViewById(R.id.studentMainMenu_Settings);

        start.setOnClickListener(view -> openActivity(Student_Class_Attending.class));
//        timetable.setOnClickListener(view -> openActivity());
        settings.setOnClickListener(view -> openActivity(Student_Settings.class));
    }
    public void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

}
