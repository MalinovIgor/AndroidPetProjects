package com.example.pharmacies_analysis.data.network.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PharmaciesRequest {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("medicines")
    @Expose
    private List<String> medicines = null;

    public PharmaciesRequest(Location location, Integer distance, List<String> medicines) {
        this.lat = location.getLat();
        this.lon = location.getLon();
        this.distance = distance;
        this.medicines = medicines;
    }


    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<String> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<String> medicines) {
        this.medicines = medicines;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}