package com.example.pharmacies_analysis.data.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Medicine {
    @PrimaryKey()
    @NonNull
    public String name;

    @Ignore
    public boolean isChecked = false;

    public Medicine(String name) {
        this.name = name;
    }
}
