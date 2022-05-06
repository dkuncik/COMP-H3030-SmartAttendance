package com.example.smartattendance.student;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Student_Class_Attending extends AppCompatActivity {

    public EditText moduleCode;
    Button sendDatabtn;
    String username = "hey";
    String password3 = "boss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_class_attending);


        moduleCode = findViewById(R.id.Module_Code);
        sendDatabtn = findViewById(R.id.Click_Present);
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (moduleCode.getText().toString().isEmpty()) {
                    Toast.makeText(Student_Class_Attending.this, "Module Code Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendData();
            }
        });


    }

    private void sendData() {
        String name = username;
        String password = password3;
        String moduleCodes = moduleCode.getText().toString().trim();


//        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
//        Toast.makeText(this, password, Toast.LENGTH_LONG).show();
//        Toast.makeText(this, moduleCodes, Toast.LENGTH_LONG).show();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://46.7.47.239/SmartAttendance/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIsInterface api = retrofit.create(APIsInterface.class);

        Call<ResponseModel> call = api.sendData(name, password, moduleCodes);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                ResponseModel responseModel = response.body();

                if (response.isSuccessful()) {
                    Toast.makeText(Student_Class_Attending.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Student_Class_Attending.this, "Failed 1", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(Student_Class_Attending.this, responseModel.getRemarks(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                Toast.makeText(Student_Class_Attending.this, "Failed 2", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());

            }
        });
    }
}
