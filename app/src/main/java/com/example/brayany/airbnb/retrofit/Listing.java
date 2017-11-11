
package com.example.brayany.airbnb.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Listing implements Parcelable
{

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("localized_city")
    @Expose
    private String localizedCity;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("picture_url")
    @Expose
    private String pictureUrl;
    @SerializedName("property_type")
    @Expose
    private String propertyType;
    @SerializedName("public_address")
    @Expose
    private String publicAddress;
    @SerializedName("room_type_category")
    @Expose
    private String roomTypeCategory;
    @SerializedName("room_type")
    @Expose
    private String roomType;
    public final static Creator<Listing> CREATOR = new Creator<Listing>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Listing createFromParcel(Parcel in) {
            return new Listing(in);
        }

        public Listing[] newArray(int size) {
            return (new Listing[size]);
        }

    }
    ;

    protected Listing(Parcel in) {
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((Double) in.readValue((Double.class.getClassLoader())));
        this.lng = ((Double) in.readValue((Double.class.getClassLoader())));
        this.localizedCity = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.pictureUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.propertyType = ((String) in.readValue((String.class.getClassLoader())));
        this.publicAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.roomTypeCategory = ((String) in.readValue((String.class.getClassLoader())));
        this.roomType = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Listing() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getLocalizedCity() {
        return localizedCity;
    }

    public void setLocalizedCity(String localizedCity) {
        this.localizedCity = localizedCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    public String getRoomTypeCategory() {
        return roomTypeCategory;
    }

    public void setRoomTypeCategory(String roomTypeCategory) {
        this.roomTypeCategory = roomTypeCategory;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(city);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(localizedCity);
        dest.writeValue(name);
        dest.writeValue(pictureUrl);
        dest.writeValue(propertyType);
        dest.writeValue(publicAddress);
        dest.writeValue(roomTypeCategory);
        dest.writeValue(roomType);
    }

    public int describeContents() {
        return  0;
    }

}
