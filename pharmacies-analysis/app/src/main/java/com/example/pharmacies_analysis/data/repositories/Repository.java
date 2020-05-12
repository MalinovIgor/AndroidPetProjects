package com.example.pharmacies_analysis.data.repositories;

import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.data.network.models.Location;
import com.example.pharmacies_analysis.data.network.models.PharmaciesResponse;
import com.example.pharmacies_analysis.data.sharedPreference.SharedPreferenceIntegerLiveData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface Repository {
    void insertAllMedicines(List<Medicine> medicines);
    Flowable<List<Medicine>> getAll();
    void delete(Medicine medicine);
    Observable<List<Medicine>> search(String query);
    Observable<PharmaciesResponse> getPharmacies(Location location, int distance, List<Medicine> medicines);
    void saveRadius(int radius);
    SharedPreferenceIntegerLiveData getRadius();
}
