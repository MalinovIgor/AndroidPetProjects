package com.example.pharmacies_analysis.data.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pharmacy implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("coordinates")
    @Expose
    private Location coordinates;
    @SerializedName("medicines")
    @Expose
    private List<Medicine> medicines = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Location getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Location coordinates) {
        this.coordinates = coordinates;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.info);
        dest.writeParcelable(this.coordinates, flags);
        dest.writeList(this.medicines);
    }

    public Pharmacy() {
    }

    protected Pharmacy(Parcel in) {
        this.name = in.readString();
        this.info = in.readString();
        this.coordinates = in.readParcelable(Location.class.getClassLoader());
        this.medicines = new ArrayList<Medicine>();
        in.readList(this.medicines, Medicine.class.getClassLoader());
    }

    public static final Parcelable.Creator<Pharmacy> CREATOR = new Parcelable.Creator<Pharmacy>() {
        @Override
        public Pharmacy createFromParcel(Parcel source) {
            return new Pharmacy(source);
        }

        @Override
        public Pharmacy[] newArray(int size) {
            return new Pharmacy[size];
        }
    };
}