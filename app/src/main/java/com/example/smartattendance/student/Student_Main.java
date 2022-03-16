package com.example.smartattendance.student;

<<<<<<< Updated upstream
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
=======
import android.content.Intent;
import android.os.Bundle;
>>>>>>> Stashed changes
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;

public class Student_Main extends AppCompatActivity {
<<<<<<< Updated upstream
    private Intent intent;
    Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
    NfcAdapter nfcAdapter;
    String classroom = "E043";
=======
>>>>>>> Stashed changes


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);

        Button start = findViewById(R.id.studentMainMenu_ScanTag);
<<<<<<< Updated upstream
        Button settings = findViewById(R.id.studentMainMenu_Settings);start.setOnClickListener(view -> {
=======
        Button settings = findViewById(R.id.studentMainMenu_Settings);
        Button timetable = findViewById(R.id.studentMainMenu_Timetable);
        start.setOnClickListener(view -> {
>>>>>>> Stashed changes
            // Mihai add the NFC things here to ask for scanning
            onNewIntent(intent);PendingIntent pendingIntent = PendingIntent.getActivity(
                   this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        });
        settings.setOnClickListener(view -> openActivity(Student_Settings.class));
//        timetable.setOnClickListener(view -> openActivity(Student_Timetable.class));
    }
    public void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
<<<<<<< Updated upstream
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages =
                    intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    messages[i] = (NdefMessage) rawMessages[i];
                }
                // Process the messages array.
            }
        }
    }

=======
>>>>>>> Stashed changes
}
