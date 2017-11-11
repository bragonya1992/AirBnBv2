
package com.example.brayany.airbnb.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AirBnBObject implements Parcelable
{

    @SerializedName("search_results")
    @Expose
    private List<SearchResult> searchResults = null;
    public final static Creator<AirBnBObject> CREATOR = new Creator<AirBnBObject>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AirBnBObject createFromParcel(Parcel in) {
            return new AirBnBObject(in);
        }

        public AirBnBObject[] newArray(int size) {
            return (new AirBnBObject[size]);
        }

    }
    ;

    protected AirBnBObject(Parcel in) {
        in.readList(this.searchResults, (com.example.brayany.airbnb.retrofit.SearchResult.class.getClassLoader()));
    }

    public AirBnBObject() {
    }

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(searchResults);
    }

    public int describeContents() {
        return  0;
    }

}
