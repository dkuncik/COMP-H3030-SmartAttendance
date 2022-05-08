package com.example.smartattendance.student;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIsInterface {

    //Here we will define the request method for particular php script

    @FormUrlEncoded
    @POST("log_user.php")
    Call<ResponseModel> sendData(
            @Field("NAME") String name,
            @Field("NFC") String nfc,
            @Field("MODULECODE") String moduleCodes,
            @Field("Date&Time") String DateandTimeS
    );

    @FormUrlEncoded
    @POST("authenticate_room.php")
    Call<ResponseModel> sendData2(
            @Field("NFC") String insidetag

    );

    @FormUrlEncoded
    @POST("authenticate_user.php")
    Call<ResponseModel> sendDataCredential(
            @Field("StudentID") String studentID,
            @Field("StudentPassword") String studentPassword

    );


}
