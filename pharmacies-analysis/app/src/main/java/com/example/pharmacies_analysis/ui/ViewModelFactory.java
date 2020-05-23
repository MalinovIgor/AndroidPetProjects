package com.example.pharmacies_analysis.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.pharmacies_analysis.ui.main.MedicinesListViewModel;
import com.example.pharmacies_analysis.ui.main.PharmacyMap.PharmacyMapViewModel;
import com.example.pharmacies_analysis.ui.search.SearchViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private Application context;
    private OnItemClickListener listener;

    public ViewModelFactory(Application application, OnItemClickListener listener){
        this.context = application;
        this.listener = listener;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(context);
        }
        if (modelClass.isAssignableFrom(MedicinesListViewModel.class)){
            return (T) new MedicinesListViewModel(context, listener);
        }
        if (modelClass.isAssignableFrom(PharmacyMapViewModel.class)){
            return (T) new PharmacyMapViewModel(context);
        }
        //noinspection unchecked
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
