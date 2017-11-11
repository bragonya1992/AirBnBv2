
package com.example.brayany.airbnb.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResult implements Parcelable
{

    @SerializedName("listing")
    @Expose
    private Listing listing;
    @SerializedName("pricing_quote")
    @Expose
    private PricingQuote pricingQuote;
    public final static Creator<SearchResult> CREATOR = new Creator<SearchResult>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SearchResult createFromParcel(Parcel in) {
            return new SearchResult(in);
        }

        public SearchResult[] newArray(int size) {
            return (new SearchResult[size]);
        }

    }
    ;

    protected SearchResult(Parcel in) {
        this.listing = ((Listing) in.readValue((Listing.class.getClassLoader())));
        this.pricingQuote = ((PricingQuote) in.readValue((PricingQuote.class.getClassLoader())));
    }

    public SearchResult() {
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public PricingQuote getPricingQuote() {
        return pricingQuote;
    }

    public void setPricingQuote(PricingQuote pricingQuote) {
        this.pricingQuote = pricingQuote;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(listing);
        dest.writeValue(pricingQuote);
    }

    public int describeContents() {
        return  0;
    }

}
