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
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("contacts")
    @Expose
    private List<Contact> contacts = null;
    @SerializedName("workHours")
    @Expose
    private String workHours;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeList(this.contacts);
        dest.writeString(this.workHours);
        dest.writeParcelable(this.coordinates, flags);
        dest.writeTypedList(this.medicines);
    }

    public Pharmacy() {
    }

    protected Pharmacy(Parcel in) {
        this.name = in.readString();
        this.address = in.readString();
        this.contacts = new ArrayList<Contact>();
        in.readList(this.contacts, Contact.class.getClassLoader());
        this.workHours = in.readString();
        this.coordinates = in.readParcelable(Location.class.getClassLoader());
        this.medicines = in.createTypedArrayList(Medicine.CREATOR);
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