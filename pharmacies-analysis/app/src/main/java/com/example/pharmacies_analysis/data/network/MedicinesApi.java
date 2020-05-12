package com.example.pharmacies_analysis.data.network;

import com.example.pharmacies_analysis.data.network.models.Forms;
import com.example.pharmacies_analysis.data.network.models.PharmaciesRequest;
import com.example.pharmacies_analysis.data.network.models.PharmaciesResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MedicinesApi {
    @GET("medicines")
    Observable<Forms> getMedicines(@Query("name") String name);

    @POST("pharmacies")
    Observable<PharmaciesResponse> getPharmacies(@Body PharmaciesRequest pharmaciesRequest);
}
