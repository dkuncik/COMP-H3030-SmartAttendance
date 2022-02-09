package com.example.smartattendance.lecturer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;

public class Lecture_Class_Paused extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecturer_class_paused);

        Button resume = findViewById(R.id.lecturerClass_Attending_Paused_Resume);
        Button end = findViewById(R.id.lecturerClass_Attending_Paused_End);

        resume.setOnClickListener(view -> {
            openActivity(Lecture_Class_Attending.class);
            finish();
        });
        end.setOnClickListener(view -> {
            openActivity(Lecturer_Main.class);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
    }

    public void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
