package com.example.smartattendance.student;

import android.app.AlertDialog;
import android.app.PendingIntent;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Student_Main extends AppCompatActivity {
    NfcAdapter nfcAdapter;
    Button ReadBtt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);


        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC Avalible ", Toast.LENGTH_LONG).show();
        } else {
            finish();
        }


        Button settings = findViewById(R.id.studentMainMenu_Settings);
        Button timetable = findViewById(R.id.studentMainMenu_Timetable);


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

        NdefRecord[] ndefRecords = ndefMessage.getRecords();
        if (ndefRecords != null && ndefRecords.length > 0) {
            NdefRecord ndefRecord = ndefRecords[0];
            String insideTag = getTextFromNdefRecord(ndefRecord);
            sendData2(insideTag);


        }
    }


    public void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void sendData2(String insidetag) {


        Toast.makeText(this, insidetag, Toast.LENGTH_LONG).show();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://46.7.47.239/SmartAttendance/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIsInterface api = retrofit.create(APIsInterface.class);

        Call<ResponseModel> call = api.sendData2(insidetag);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                ResponseModel responseModel = response.body();
                AlertDialog.Builder builder = new AlertDialog.Builder(Student_Main.this, R.style.AlertDialogStyle);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Student_Main.this, R.style.AlertDialogStyleGood);
                if (response.isSuccessful()) {
                    if (responseModel.isStatus()) {
                        builder1.setMessage("You are Present in" + insidetag)
                                .setPositiveButton("good", (dialogInterface, i) -> openActivity(Student_Class_Attending.class))
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
                } else {

                }
                Toast.makeText(Student_Main.this, responseModel.getRemarks(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                Toast.makeText(Student_Main.this, "Failed 2", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());

            }
        });
    }


}




