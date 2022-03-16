package com.example.smartattendance.lecturer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;

public class Lecture_Class_Attending extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecturer_class_attending);

        Button pause = findViewById(R.id.lecturerClass_Attending_Pause);
        Button end = findViewById(R.id.lecturerClass_Attending_Stop);

        pause.setOnClickListener(view -> openActivity(Lecture_Class_Paused.class));
        end.setOnClickListener(view -> {
            openActivity(Lecturer_Main.class);
            finish();
        });
    }

    public void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

}
