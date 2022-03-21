package com.example.smartattendance.student;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;

import java.util.List;

public class Student_Main extends AppCompatActivity {

    CalendarList calendarList = new CalendarList();
    List<CalendarListEntry> calendarListEntryList = calendarList.getItems();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);

        Button start = findViewById(R.id.studentMainMenu_ScanTag);
        Button settings = findViewById(R.id.studentMainMenu_Settings);
        Button timetable = findViewById(R.id.studentMainMenu_Timetable);

        timetable.setOnClickListener(view -> {
            Log.i("TAG", String.valueOf(calendarList.getItems()));
        });
    }

    public void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}



