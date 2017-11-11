package com.example.brayany.airbnb.interfaces;

import com.example.brayany.airbnb.retrofit.AirBnBObject;

/**
 * Created by Brayan on 08/11/2017.
 */
public interface RetrofitCallback {
    void onSuccess(AirBnBObject object);
    void onError(Throwable error);
}
