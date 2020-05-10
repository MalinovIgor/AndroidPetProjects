package com.example.pharmacies_analysis.data.repositories;

import android.content.Context;

import com.example.pharmacies_analysis.data.db.AppDatabase;
import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.data.db.MedicineDao;
import com.example.pharmacies_analysis.data.network.Forms;
import com.example.pharmacies_analysis.data.network.MedicinesService;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RepositoryImpl implements Repository {
    private MedicinesService medicinesService;
    private MedicineDao medicineDao;
    private static RepositoryImpl instance;

    private RepositoryImpl(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        medicineDao = db.drugDao();
        medicinesService = new MedicinesService();
    }

    public static RepositoryImpl getRepository(Context context) {
        if (instance == null) {
            instance = new RepositoryImpl(context);
        }
        return instance;
    }

    @Override
    public void insertAllMedicines(List<Medicine> medicines) {
        Completable.fromRunnable(() -> medicineDao.insertAllMedicines(medicines))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public Flowable<List<Medicine>> getAll() {

        return medicineDao.getAll()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void delete(Medicine medicine) {
        Completable.fromRunnable(() -> medicineDao.delete(medicine))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public Observable<List<Medicine>> search(String query) {
        return medicinesService.getForms(query)
                .flatMap(forms -> Observable.fromIterable(forms.getForms()))
                .map(Medicine::new)
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void getPharmaciesLocations(List<String> medicinesList) {

    }
}
