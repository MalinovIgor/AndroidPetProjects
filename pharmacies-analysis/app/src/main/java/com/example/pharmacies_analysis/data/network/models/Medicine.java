
package com.example.pharmacies_analysis.data.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medicine implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeValue(this.price);
    }

    public Medicine() {
    }

    protected Medicine(Parcel in) {
        this.name = in.readString();
        this.price = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Medicine> CREATOR = new Parcelable.Creator<Medicine>() {
        @Override
        public Medicine createFromParcel(Parcel source) {
            return new Medicine(source);
        }

        @Override
        public Medicine[] newArray(int size) {
            return new Medicine[size];
        }
    };
}