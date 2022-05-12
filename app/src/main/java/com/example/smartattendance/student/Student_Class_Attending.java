package com.example.smartattendance.student;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Student_Class_Attending extends AppCompatActivity {

    public EditText moduleCode;
    Button sendDatabtn;
    Button goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_class_attending);

        SharedPreferences StudentName = getApplicationContext().getSharedPreferences("UserName", MODE_PRIVATE);
        String UsernameToSend = StudentName.getString("name", "");
        SharedPreferences NFCtag = getApplicationContext().getSharedPreferences("NFCtags", MODE_PRIVATE);
        String NFCtoSend = NFCtag.getString("nfc", "");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss-dd/MM/yyyy");

        String datetime = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(Student_Class_Attending.this,datetime,Toast.LENGTH_SHORT).show();

        moduleCode = findViewById(R.id.Module_Code);
        sendDatabtn = findViewById(R.id.Click_Present);
        goBack = findViewById(R.id.studentClass_Attending_GOback);

        goBack.setOnClickListener(view -> openActivity(Student_Main.class));


        sendDatabtn.setOnClickListener(view -> {
            if (moduleCode.getText().toString().isEmpty()) {
                Toast.makeText(Student_Class_Attending.this, "Module Code Required", Toast.LENGTH_SHORT).show();
            } else {
                sendData3(UsernameToSend,NFCtoSend,datetime);}




        });


    }

    public void sendData(String Usernametosend, String NFCtoSend, String datetime) {


        String moduleCodes = moduleCode.getText().toString().trim();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://46.7.47.239/SmartAttendance/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIsInterface api = retrofit.create(APIsInterface.class);

        Call<ResponseModel> call = api.sendData(Usernametosend, NFCtoSend, moduleCodes, datetime);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                ResponseModel responseModel = response.body();

                if (response.isSuccessful()) {
                    Toast.makeText(Student_Class_Attending.this, "Success", Toast.LENGTH_SHORT).show();
                    openActivity(Student_Main.class);
                } else {
                    Toast.makeText(Student_Class_Attending.this, "Failed ", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(Student_Class_Attending.this, responseModel.getRemarks(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                Toast.makeText(Student_Class_Attending.this, "Failed ", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());

            }
        });
    }

    public void sendData3( String UsernameToSend, String NFCtoSend, String datetime) {
        String moduleCodes = moduleCode.getText().toString().trim();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://46.7.47.239/SmartAttendance/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIsInterface api = retrofit.create(APIsInterface.class);

        Call<ResponseModel> call = api.sendData3(moduleCodes);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                ResponseModel responseModel = response.body();
                if (response.isSuccessful()) {
                    if(responseModel.isStatus()){

                        sendData(UsernameToSend, NFCtoSend, datetime);
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Student_Class_Attending.this, R.style.AlertDialogStyle);
                        builder.setMessage("Not A Module")
                                .setNegativeButton("Cancel", null);
                        AlertDialog alert = builder.create();
                        
                        alert.show();
                        alert.getWindow().setLayout(900, 900);
                    }


                }
            }


            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                Log.e("Error", t.getMessage());

            }
        });

    }

    public void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

}
