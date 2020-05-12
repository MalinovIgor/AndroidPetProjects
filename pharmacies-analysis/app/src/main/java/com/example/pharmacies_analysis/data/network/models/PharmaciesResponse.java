package com.example.pharmacies_analysis.data.network.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PharmaciesResponse {

    @SerializedName("pharmacies")
    @Expose
    private List<Pharmacy> pharmacies = null;
    @SerializedName("notFound")
    @Expose
    private List<String> notFound = null;

    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public List<String> getNotFound() {
        return notFound;
    }

    public void setNotFound(List<String> notFound) {
        this.notFound = notFound;
    }

}