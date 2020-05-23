package com.example.pharmacies_analysis.utils;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.ui.OnItemClickListener;
import com.example.pharmacies_analysis.ui.main.MedicinesList.MedicinesListAdapter;

import java.util.List;

public class MedicinesListBindingAdapter {

    @BindingAdapter({"bind:data", "bind:clickHandler"})
    public static void configureRecyclerView(RecyclerView recyclerView, List<Medicine> medicines, OnItemClickListener listener) {
        MedicinesListAdapter adapter = new MedicinesListAdapter(listener);
        adapter.setMedicinesList(medicines);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

}
