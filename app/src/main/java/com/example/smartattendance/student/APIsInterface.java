package com.example.smartattendance.student;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIsInterface {

    //Here we will define the request method for particular php script

    @FormUrlEncoded
    @POST("save_user.php")
    Call<ResponseModel> sendData(
            @Field("NAME") String name,
            @Field("PASSWORD") String password,
            @Field("MODULECODE") String moduleCodes
    );

    @FormUrlEncoded
    @POST("getNFC.php")
    Call<ResponseModel> sendData2(
            @Field("NFC") String insidetag

    );


}
