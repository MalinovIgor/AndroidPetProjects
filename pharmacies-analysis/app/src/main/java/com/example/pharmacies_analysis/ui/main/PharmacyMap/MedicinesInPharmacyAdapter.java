package com.example.pharmacies_analysis.ui.main.PharmacyMap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacies_analysis.R;
import com.example.pharmacies_analysis.data.network.models.Medicine;
import com.example.pharmacies_analysis.databinding.PharmacysMedicineListItemBinding;

import java.util.List;

public class MedicinesInPharmacyAdapter extends RecyclerView.Adapter<MedicinesInPharmacyAdapter.PharmacysMedicineListItemViewHolder> {
    private List<Medicine> medicines;

    @NonNull
    @Override
    public MedicinesInPharmacyAdapter.PharmacysMedicineListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    PharmacysMedicineListItemBinding pharmacysMedicineListItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.pharmacys_medicine_list_item,
                    parent, false);
        return new PharmacysMedicineListItemViewHolder(pharmacysMedicineListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicinesInPharmacyAdapter.PharmacysMedicineListItemViewHolder holder, int position) {
        Medicine currentMedicine = medicines.get(position);
        holder.pharmacysMedicineListItemBinding.setMedicine(currentMedicine);
    }

    @Override
    public int getItemCount() {
        if (medicines != null) {
            return medicines.size();
        } else {
            return 0;
        }
    }

    public void setMedicinesList(List<Medicine> medicines) {
        this.medicines = medicines;
        notifyDataSetChanged();
    }

    class PharmacysMedicineListItemViewHolder extends RecyclerView.ViewHolder{
        PharmacysMedicineListItemBinding pharmacysMedicineListItemBinding;

        public PharmacysMedicineListItemViewHolder(@NonNull PharmacysMedicineListItemBinding pharmacysMedicineListItemBinding) {
            super(pharmacysMedicineListItemBinding.getRoot());
            this.pharmacysMedicineListItemBinding = pharmacysMedicineListItemBinding;
        }
    }
}
