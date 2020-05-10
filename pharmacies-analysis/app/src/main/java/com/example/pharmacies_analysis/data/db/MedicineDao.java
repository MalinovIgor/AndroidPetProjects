package com.example.pharmacies_analysis.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MedicineDao {

    @Query("SELECT * FROM Medicine")
    Flowable<List<Medicine>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMedicines(List<Medicine> medicines);

    @Delete
    void delete(Medicine medicine);
}
