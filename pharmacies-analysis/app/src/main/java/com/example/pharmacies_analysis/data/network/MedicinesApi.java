package com.example.pharmacies_analysis.data.network;

import com.example.pharmacies_analysis.data.db.Medicine;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MedicinesApi {
    @GET("medicines")
    Observable<Forms> getMedicines(@Query("name") String name);
}
