package com.example.smartattendance.student;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
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


public class Login extends AppCompatActivity {
    EditText username, password;
    String NameToSend;
    SharedPreferences StudentName;


    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        Button login = findViewById(R.id.main_Login_Login);
        CheckBox remember = findViewById(R.id.rememberMe);


        username = findViewById(R.id.main_Login_StudentID);
        password = findViewById(R.id.main_Login_Password);

        StudentName = getSharedPreferences("UserName", MODE_PRIVATE);


        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if (checkbox.equals("true")) {
            Intent intent = new Intent(Login.this, Student_Main.class);
            startActivity(intent);
        } else if (!checkbox.equals("false")) {
            Toast.makeText(this, "Please Sign in ", Toast.LENGTH_SHORT).show();
        }



        login.setOnClickListener(view -> sendDataCredential());

        login.setOnClickListener(view -> {

            if (username.getText().toString().trim().equals(GoodUsername) && password.getText().toString().trim().equals(GoodPassword)) {

                NameToSend = username.getText().toString();
                SharedPreferences.Editor editor = StudentName.edit();
                editor.putString("name", NameToSend);
                editor.apply();


                Intent intent2 = new Intent(Login.this, Student_Main.class);
                startActivity(intent2);


            } else {
                Toast.makeText(getApplicationContext(), "Incorrect ID or PASSWORD", Toast.LENGTH_SHORT).show();
            }
        });



        remember.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                SharedPreferences preferences1 = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putString("remember", "true");
                editor.apply();
            } else if (!compoundButton.isChecked()) {
                SharedPreferences preferences1 = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putString("remember", "false");
                editor.apply();

            }

        });
    }
    public void sendDataCredential() {

        String user = username.getText().toString();
        String pass = password.getText().toString();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://46.7.47.239/SmartAttendance/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIsInterface api = retrofit.create(APIsInterface.class);

        Call<ResponseModel> call = api.sendDataCredential(user,pass);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                ResponseModel responseModel = response.body();

                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this, R.style.AlertDialogStyle);
                if (response.isSuccessful()) {

                    if (responseModel.isStatus()) {

                        NameToSend = username.getText().toString();
                        SharedPreferences.Editor editor = StudentName.edit();
                        editor.putString("name", NameToSend);
                        editor.apply();


                        Intent intent2 = new Intent(Login.this, Student_Main.class);
                        startActivity(intent2);

                    } else {
                        builder.setMessage("Wrong Password Or Username")
                                .setNegativeButton("Cancel", null);
                        AlertDialog alert = builder.create();
                        alert.show();
                        alert.getWindow().setLayout(900, 900);


                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                Toast.makeText(Login.this, "Failed", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());

            }
        });
    }



}

