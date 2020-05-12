package com.example.pharmacies_analysis.ui.main.PharmacyMap;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacies_analysis.R;
import com.example.pharmacies_analysis.data.network.models.Pharmacy;
import com.example.pharmacies_analysis.databinding.PharmacyBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PharmacyBottomSheet extends BottomSheetDialogFragment {
    PharmacyBottomSheetBinding pharmacyBottomSheetBinding;
    MedicinesInPharmacyAdapter pharmacyAdapter;
    Pharmacy pharmacy;

    public static PharmacyBottomSheet newInstance(Pharmacy pharmacy) {

        Bundle args = new Bundle();
        args.putParcelable("PHARMACY", pharmacy);
        PharmacyBottomSheet fragment = new PharmacyBottomSheet();
        fragment.setArguments(args);
        return fragment;
    }
    public PharmacyBottomSheet() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        pharmacyBottomSheetBinding = DataBindingUtil.inflate(inflater, R.layout.pharmacy_bottom_sheet, container, false);
        RecyclerView recyclerView = pharmacyBottomSheetBinding.medicinesListRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        pharmacyAdapter = new MedicinesInPharmacyAdapter();
        pharmacy = getArguments().getParcelable("PHARMACY");
        pharmacyAdapter.setMedicinesList(pharmacy.getMedicines());
        recyclerView.setAdapter(pharmacyAdapter);
        pharmacyBottomSheetBinding.setPharmacy(pharmacy);
        return pharmacyBottomSheetBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();

        if (dialog != null) {
            View bottomSheet = dialog.findViewById(R.id.pharmacy_bottom_sheet);
            final float scale = getContext().getResources().getDisplayMetrics().density;
            bottomSheet.getLayoutParams().height = (int) (350 * scale + 0.5f);
        }
    }
}
