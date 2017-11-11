package com.example.brayany.airbnb.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import com.example.brayany.airbnb.retrofit.AirBnBObject;
import com.example.brayany.airbnb.retrofit.SearchResult;

/**
 * Created by brayany on 11/9/2017.
 */

public class database {

    private final static String preferences_key="dbAir";
    private final static String preferences_air="object";

    public static void saveAirBnBObject(SearchResult object,Context context){
        AirBnBObject source = getFromDataBase(context);
        if(source== null) {
            source = new AirBnBObject();
            List<SearchResult> list = new ArrayList<>();
            source.setSearchResults(list);
        }
        source.getSearchResults().add(object);
        saveToDB(context,source);
    }

    public static boolean isInFav(String name,Context context){
        AirBnBObject object = getFromDataBase(context);
        if(object!=null) {
            for (SearchResult result : object.getSearchResults()) {
                if(result.getListing().getName().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void delete(String name,Context context){
        AirBnBObject object = getFromDataBase(context);
        if(object!=null) {
            for (int i=0; i<object.getSearchResults().size();i++) {
                SearchResult result = object.getSearchResults().get(i);
                if(result.getListing().getName().equals(name)){
                    object.getSearchResults().remove(i);
                    saveToDB(context,object);
                    return;
                }
            }
        }
    }

    public static AirBnBObject getFromDataBase(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(preferences_key,Context.MODE_PRIVATE);
        String airJson = sharedPref.getString(preferences_air,"");
        try {
            Gson gson = new Gson();
            return gson.fromJson(airJson,AirBnBObject.class);
        }catch (Exception e){
            return null;
        }

    }

    private static void saveToDB(Context context, AirBnBObject finalInput){
        Gson gson = new Gson();
        String jsonAir = gson.toJson(finalInput);
        SharedPreferences sharedPref = context.getSharedPreferences(preferences_key,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(preferences_air,jsonAir);
        editor.commit();
    }
}
