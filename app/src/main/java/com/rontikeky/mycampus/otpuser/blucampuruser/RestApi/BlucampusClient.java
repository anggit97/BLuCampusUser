package com.rontikeky.mycampus.otpuser.blucampuruser.RestApi;

import com.rontikeky.mycampus.otpuser.blucampuruser.Activity.DetailEvent;
import com.rontikeky.mycampus.otpuser.blucampuruser.Request.SigninRequest;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.DaftarEventResponse;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.DetailEventResponse;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.EventResponse;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.MyEventDetailReponse;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.MyEventReponse;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.SigninResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Anggit on 07/08/2017.
 */
public interface BlucampusClient {

    @Headers("Accept: application/json")
    @POST("login")
    Call<SigninResponse> doLogin(@Body SigninRequest loginRequest);

    @Headers("Accept: application/json")
    @GET("allevents")
    Call<EventResponse> getEvents();

    @Headers("Accept: application/json")
    @GET("events/detail/{idEvent}")
    Call<DetailEventResponse> getDetailEvent(@Path("idEvent") String idEvent);

    @Headers("Accept: application/json")
    @POST("events/detail/daftar/{id}/{idu}")
    Call<DaftarEventResponse> doDaftarEvent(@Path("id") String idEvent, @Path("idu") String idUser);

    @Headers("Accept: application/json")
    @GET("events/myevents/{idUser}")
    Call<MyEventReponse> getMyEvents(@Path("idUser") String idUser);

    @Headers("Accept: application/json")
    @GET("events/myevents/detail/{idUser}/{idEvent}")
    Call<MyEventDetailReponse> getDetailMyEvent(@Path("idEvent") String idEvent, @Path("idUser") String idUser);
}
