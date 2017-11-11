package com.example.brayany.airbnb.interfaces;

import com.example.brayany.airbnb.retrofit.AirBnBObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Brayan on 08/11/2017.
 */
public interface RestClient {
        @GET("/v2/search_results?client_id=3092nxybyb0otqw18e8nh5nty")
        Call<AirBnBObject> getData(@Query("_limit") String limit, @Query("lng") String user_lng, @Query("lat") String user_lat);
        @GET("/v2/search_results?client_id=3092nxybyb0otqw18e8nh5nty&location={location}")
        Call<AirBnBObject> getDataCountry(@Query("location") String country);
        @GET("/v2/search_results?client_id=3092nxybyb0otqw18e8nh5nty&_limit={_limit}")
        Call<AirBnBObject> getDataLimit(@Query("_limit") String limit);
}
