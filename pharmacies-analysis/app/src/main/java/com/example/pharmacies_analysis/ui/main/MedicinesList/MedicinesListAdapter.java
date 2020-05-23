package com.example.pharmacies_analysis.ui.main.MedicinesList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacies_analysis.R;
import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.databinding.MedicinesItemBinding;
import com.example.pharmacies_analysis.ui.OnItemClickListener;

import java.util.List;

public class MedicinesListAdapter extends RecyclerView.Adapter<MedicinesListAdapter.MedicineViewHolder> {
    private List<Medicine> medicines;
    private OnItemClickListener onItemClickListener;

    public MedicinesListAdapter(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    MedicinesItemBinding medicinesItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.medicines_item,
                    parent, false);
        return new MedicineViewHolder(medicinesItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        Medicine currentMedicine = medicines.get(position);
        holder.medicinesItemBinding.setMedicine(currentMedicine);
        holder.medicinesItemBinding.delete.setOnClickListener(v -> {
            if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                onItemClickListener.onItemClick(medicines.get(position));
            }
        });
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

    class MedicineViewHolder extends RecyclerView.ViewHolder{
        MedicinesItemBinding medicinesItemBinding;

        public MedicineViewHolder(@NonNull MedicinesItemBinding medicinesItemBinding) {
            super(medicinesItemBinding.getRoot());
            this.medicinesItemBinding = medicinesItemBinding;
        }
    }

}
