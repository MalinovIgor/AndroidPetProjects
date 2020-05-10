package com.example.pharmacies_analysis.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface MedicineDao {

    @Query("SELECT * FROM Medicine")
    Flowable<List<Medicine>> getAll();

    @Insert
    void insert(Medicine medicine);

    @Delete
    void delete(Medicine medicine);
}
