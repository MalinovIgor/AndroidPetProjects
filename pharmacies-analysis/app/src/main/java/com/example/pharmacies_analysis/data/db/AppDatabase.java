package com.example.pharmacies_analysis.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Medicine.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MedicineDao drugDao();
    private static AppDatabase instance;

    public static AppDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "pharmacies_database").build();
        }
        return instance;
    }
}
