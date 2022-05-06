package com.example.smartattendance.student;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendance.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_Class_Attending extends AppCompatActivity  {

    Button sendDatabtn;
    public EditText moduleCode;
    String username = "hey";
    String password3 = "boss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_class_attending);


        moduleCode = findViewById(R.id.Module_Code);
        sendDatabtn = findViewById(R.id.Click_Present);
        sendDatabtn.setOnClickListener(view -> sendData());


    }

    private void sendData(){
        String name = username;
        String  password =password3;
        String moduleCodes = moduleCode.getText().toString().trim();
        if(moduleCodes.isEmpty()){

            moduleCode.setError("Module Code Required");
            moduleCode.requestFocus();
            return;
        }

        Toast.makeText(this,name,Toast.LENGTH_LONG).show();
        Toast.makeText(this,password,Toast.LENGTH_LONG).show();
        Toast.makeText(this,moduleCodes,Toast.LENGTH_LONG).show();

        Call<ResponseModel> call = RetrofitClient.getInstance().getAPI().sendData(name, password,moduleCodes);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse( Call<ResponseModel> call,  Response<ResponseModel> response) {
                //This method will be called on successful server call

                ResponseModel obj = response.body();

                if(obj.isStatus()){
                    //What we want to do on successful insertion
                }else{
                    //What in case of some error
                }

                Toast.makeText(Student_Class_Attending.this, obj.getRemarks(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure( Call<ResponseModel> call, Throwable t) {

                //This method will called in case of failure

                Toast.makeText(Student_Class_Attending.this, "Network Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
