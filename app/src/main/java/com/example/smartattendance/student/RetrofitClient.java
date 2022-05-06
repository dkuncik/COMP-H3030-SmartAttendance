package com.example.smartattendance.student;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://46.7.47.239/SmartAttendance/";
    private static RetrofitClient myClient;
    private  Retrofit retrofit;

    private RetrofitClient(){
        //initialisation of retrofit in constructor
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
    }//Constructor

    public static synchronized RetrofitClient getInstance(){

        //check that myClient is initialized or not

        if(myClient == null){
            myClient = new RetrofitClient();
        }
        return myClient;
    }

    public APIsInterface getAPI(){

        return retrofit.create(APIsInterface.class);

    }//We will create the APIsInterface later

}
