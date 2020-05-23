package com.example.pharmacies_analysis.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.data.repositories.RepositoryImpl;
import com.example.pharmacies_analysis.ui.OnItemClickListener;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class MedicinesListViewModel extends AndroidViewModel {
    private RepositoryImpl mRepository;
    private Disposable mDisposable;
    private OnItemClickListener mOnItemClickListener;
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> mIsEmpty = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>(false);
    private MutableLiveData<List<Medicine>> mMedicines = new MutableLiveData<>();

    public MedicinesListViewModel(@NonNull Application application, OnItemClickListener listener) {
        super(application);
        mRepository = RepositoryImpl.getRepository(application);
        mOnItemClickListener = listener;
        getAllMedicines();
    }

    public void delete(Medicine medicine) {
        mRepository.delete(medicine);
    }

    private void getAllMedicines() {
        mDisposable = mRepository.getAll()
                .doOnSubscribe(disposable -> setIsLoading(true))
                .doOnTerminate(() -> setIsLoading(false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        medicines -> {
                            setIsLoading(false);
                            setIsErrorVisible(false);
                            if (medicines.isEmpty()) {
                                setIsEmpty(true);
                                return;
                            }
                            setIsEmpty(false);
                            setMedicines(medicines);
                        },
                        throwable -> setIsErrorVisible(true));
    }

    private void setMedicines(List<Medicine> medicines) {
        mMedicines.postValue(medicines);
    }

    private void setIsErrorVisible(boolean isErrorVisible) {
        mIsErrorVisible.postValue(isErrorVisible);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public LiveData<List<Medicine>> getMedicines() {
        return mMedicines;
    }


    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public LiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }

    private void setIsLoading(boolean isLoading) {
        mIsLoading.postValue(isLoading);
    }

    public LiveData<Boolean> getIsEmpty() {
        return mIsEmpty;
    }

    public void setIsEmpty(boolean isLoading) {
        mIsEmpty.postValue(isLoading);
    }

    @Override
    public void onCleared() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }
}
