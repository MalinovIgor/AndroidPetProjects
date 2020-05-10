package com.example.pharmacies_analysis.data.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Medicine {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public Medicine(String name){
        this.name = name;
    }
}
