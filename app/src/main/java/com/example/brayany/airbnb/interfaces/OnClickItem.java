package com.example.brayany.airbnb.interfaces;

import com.example.brayany.airbnb.retrofit.SearchResult;

/**
 * Created by brayany on 11/9/2017.
 */

public interface OnClickItem  {
    void onClick(Double latitude, Double longitude, String marker);
    void addFav(SearchResult result);
    void deleteFav(String name);
}
