package com.example.pharmacies_analysis.data.repositories;

import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.data.network.Forms;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface Repository {
    void insert(Medicine medicine);
    Flowable<List<Medicine>> getAll();
    void delete(Medicine medicine);
    Observable<List<Medicine>> search(String query);
    void getPharmaciesLocations(List<String> medicinesList);
}
