package com.example.smartattendance.student;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;

public class Student_Class_Attending extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecturer_class_attending);

        // Mihai do your geo-fence things here
    }
}
