package com.example.brayany.airbnb.retrofit;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.example.brayany.airbnb.interfaces.RestClient;
import com.example.brayany.airbnb.interfaces.RetrofitCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Brayan on 08/11/2017.
 */
public class RetrofitHelper {
    private static final String urlBase="https://api.airbnb.com";

    public static void getObjects(final String longitude,final String latitude, final String limit,final RetrofitCallback callback){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestClient restClient = retrofit.create(RestClient.class);
        Call<AirBnBObject> call = restClient.getData(limit,longitude,latitude);

        Log.d("Retrofit",retrofit.baseUrl().url().toString());

        call.enqueue(new Callback<AirBnBObject>() {
            @Override
            public void onResponse(Call<AirBnBObject> call, Response<AirBnBObject> response) {
                switch (response.code()) {
                    case 200:
                        //view.notifyDataSetChanged(data.getResults());
                        callback.onSuccess(response.body());
                        break;
                    case 401:
                        callback.onError(new Throwable("page not found"));
                        break;
                    default:
                        callback.onError(new Throwable("unknown error"));
                        break;
                }
            }

            @Override
            public void onFailure(Call<AirBnBObject> call, Throwable t) {
                callback.onError(t);
            }
        });
    }


}
