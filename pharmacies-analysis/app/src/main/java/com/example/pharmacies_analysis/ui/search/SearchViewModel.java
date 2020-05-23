package com.example.pharmacies_analysis.ui.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.data.repositories.RepositoryImpl;

import java.util.List;

import io.reactivex.Observable;

public class SearchViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        repository = RepositoryImpl.getRepository(application);
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
