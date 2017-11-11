
package com.example.brayany.airbnb.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PricingQuote implements Parcelable
{

    @SerializedName("listing_currency")
    @Expose
    private String listingCurrency;
    @SerializedName("localized_currency")
    @Expose
    private String localizedCurrency;
    @SerializedName("nightly_price")
    @Expose
    private Integer nightlyPrice;
    public final static Creator<PricingQuote> CREATOR = new Creator<PricingQuote>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PricingQuote createFromParcel(Parcel in) {
            return new PricingQuote(in);
        }

        public PricingQuote[] newArray(int size) {
            return (new PricingQuote[size]);
        }

    }
    ;

    protected PricingQuote(Parcel in) {
        this.listingCurrency = ((String) in.readValue((String.class.getClassLoader())));
        this.localizedCurrency = ((String) in.readValue((String.class.getClassLoader())));
        this.nightlyPrice = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public PricingQuote() {
    }

    public String getListingCurrency() {
        return listingCurrency;
    }

    public void setListingCurrency(String listingCurrency) {
        this.listingCurrency = listingCurrency;
    }

    public String getLocalizedCurrency() {
        return localizedCurrency;
    }

    public void setLocalizedCurrency(String localizedCurrency) {
        this.localizedCurrency = localizedCurrency;
    }

    public Integer getNightlyPrice() {
        return nightlyPrice;
    }

    public void setNightlyPrice(Integer nightlyPrice) {
        this.nightlyPrice = nightlyPrice;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(listingCurrency);
        dest.writeValue(localizedCurrency);
        dest.writeValue(nightlyPrice);
    }

    public int describeContents() {
        return  0;
    }

}
