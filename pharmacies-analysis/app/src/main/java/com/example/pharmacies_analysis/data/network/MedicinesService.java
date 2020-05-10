package com.example.pharmacies_analysis.data.network;

import com.example.pharmacies_analysis.data.db.Medicine;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicinesService {
    private MedicinesApi medicinesApi;
    public MedicinesService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://213.21.7.194:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        medicinesApi = retrofit.create(MedicinesApi.class);
    }

    public Observable<Forms> getForms(String medicine){
        return medicinesApi.getMedicines(medicine);
    }
}
