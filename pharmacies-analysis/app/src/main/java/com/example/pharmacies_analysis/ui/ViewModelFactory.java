package com.example.pharmacies_analysis.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.pharmacies_analysis.ui.main.MedicinesList.MedicinesListViewModel;
import com.example.pharmacies_analysis.ui.main.PharmacyMap.PharmacyMapViewModel;
import com.example.pharmacies_analysis.ui.search.SearchViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private Context context;

    public ViewModelFactory(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(context);
        }
        if (modelClass.isAssignableFrom(MedicinesListViewModel.class)){
            return (T) new MedicinesListViewModel(context);
        }
        if (modelClass.isAssignableFrom(PharmacyMapViewModel.class)){
            return (T) new PharmacyMapViewModel(context);
        }
        //noinspection unchecked
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
