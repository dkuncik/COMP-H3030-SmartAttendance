package com.example.smartattendance.student;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;


public class Student_Main extends AppCompatActivity {
    NfcAdapter nfcAdapter;
    Button ReadBtt;
    String[] values = {"E042", "E043", "LT1", "LT2", "LT3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        ReadBtt = findViewById(R.id.ReadBtt);


        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC Avalible ", Toast.LENGTH_LONG).show();
        } else {
            finish();
        }



        Button settings = findViewById(R.id.studentMainMenu_Settings);
        Button timetable = findViewById(R.id.studentMainMenu_Timetable);
        Intent intent3 = new Intent(Student_Main.this, Student_Class_Attending.class);

        ReadBtt.setOnClickListener(view -> openActivity(Student_Class_Attending.class));
        settings.setOnClickListener(view -> openActivity(Student_Settings.class));


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        AlertDialog.Builder builder = new AlertDialog.Builder(Student_Main.this);
        Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if (parcelables != null && parcelables.length > 0) {
            readTag((NdefMessage) parcelables[0]);
        } else {
            builder.setMessage("No NFC Message Found")
                    .setPositiveButton("Okay", null)
                    .setNegativeButton("Cancel", null);
            AlertDialog alert = builder.create();
            alert.show();
        }
    }


    @Override
    protected void onResume() {

        Intent intent = new Intent(this, Student_Main.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentFilter = new IntentFilter[]{};
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
        super.onResume();

    }

    @Override
    protected void onPause() {
        nfcAdapter.disableForegroundDispatch(this);

        super.onPause();
    }

    public String getTextFromNdefRecord(NdefRecord ndefRecord) {
        String insideTag = null;
        try {
            byte[] payload = ndefRecord.getPayload();
            String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
            int languageSize = payload[0] & 0063;
            insideTag = new String(payload, languageSize + 1, payload.length - languageSize - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("GetTExtFromNDEFRECORD", e.getMessage(), e);
        }
        return insideTag;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void readTag(NdefMessage ndefMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Student_Main.this, R.style.AlertDialogStyle);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Student_Main.this, R.style.AlertDialogStyleGood);
        NdefRecord[] ndefRecords = ndefMessage.getRecords();
        if (ndefRecords != null && ndefRecords.length > 0) {
            NdefRecord ndefRecord = ndefRecords[0];
            String insideTag = getTextFromNdefRecord(ndefRecord);
            Toast.makeText(this, "You are Present in " + insideTag, Toast.LENGTH_LONG).show();
            boolean contains = Arrays.asList(values).contains(insideTag);
            if (contains) {
                builder1.setMessage("You are Present in" + insideTag)
                        .setPositiveButton("good", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                openActivity(Student_Class_Attending.class);
                            }
                        })
                        .setNegativeButton("Cancel", null);
                AlertDialog alert1 = builder1.create();
                alert1.show();
                alert1.getWindow().setLayout(900, 900);
            } else {
                builder.setMessage("Not A Classroom")
                        .setNegativeButton("Cancel", null);
                AlertDialog alert = builder.create();
                alert.show();
                alert.getWindow().setLayout(900, 900);
            }

        }
    }


    public void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }


}




