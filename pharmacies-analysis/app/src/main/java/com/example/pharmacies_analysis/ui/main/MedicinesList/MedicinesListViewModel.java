package com.example.pharmacies_analysis.ui.main.MedicinesList;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.data.repositories.RepositoryImpl;

import java.util.List;

import io.reactivex.Flowable;

public class MedicinesListViewModel extends ViewModel {
    private RepositoryImpl repository;

    public MedicinesListViewModel(@NonNull Context context) {
        repository = RepositoryImpl.getRepository(context);
    }

    public void delete(Medicine medicine){
        repository.delete(medicine);
    }

    public Flowable<List<Medicine>> getAllMedicines(){
        return repository.getAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
