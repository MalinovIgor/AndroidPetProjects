package com.example.pharmacies_analysis.ui.search;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.data.repositories.RepositoryImpl;

import java.util.List;

import io.reactivex.Observable;

public class SearchViewModel extends ViewModel {
    private RepositoryImpl repository;

    public SearchViewModel(@NonNull Context context) {
        repository = RepositoryImpl.getRepository(context);
    }

    public Observable<List<Medicine>> getForms(String query){
        return repository.search(query);
    }

    public void insertAllMedicines(List<Medicine> medicine){
        repository.insertAllMedicines(medicine);
    }

    public void delete(Medicine medicine){
        repository.delete(medicine);
    }
}
