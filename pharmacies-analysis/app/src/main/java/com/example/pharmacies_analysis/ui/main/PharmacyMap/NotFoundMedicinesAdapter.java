package com.example.pharmacies_analysis.ui.main.PharmacyMap;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacies_analysis.R;
import com.example.pharmacies_analysis.databinding.NotFoundItemBinding;
import com.example.pharmacies_analysis.databinding.PharmacysMedicineListItemBinding;

import java.util.List;

public class NotFoundMedicinesAdapter extends RecyclerView.Adapter<NotFoundMedicinesAdapter.NotFoundItemViewHolder> {
    private List<String> medicines;

    @NonNull
    @Override
    public NotFoundItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    NotFoundItemBinding notFoundItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.not_found_item,
                    parent, false);
        return new NotFoundItemViewHolder(notFoundItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotFoundItemViewHolder holder, int position) {
        String currentMedicine = medicines.get(position);
        holder.notFoundItemBinding.setMedicine(currentMedicine);
    }

    @Override
    public int getItemCount() {
        if (medicines != null) {
            return medicines.size();
        } else {
            return 0;
        }
    }

    public void setMedicinesList(List<String> medicines) {
        this.medicines = medicines;
        notifyDataSetChanged();
    }

    class NotFoundItemViewHolder extends RecyclerView.ViewHolder{
        NotFoundItemBinding notFoundItemBinding;

        public NotFoundItemViewHolder(@NonNull NotFoundItemBinding notFoundItemBinding) {
            super(notFoundItemBinding.getRoot());
            this.notFoundItemBinding = notFoundItemBinding;
        }
    }
}
