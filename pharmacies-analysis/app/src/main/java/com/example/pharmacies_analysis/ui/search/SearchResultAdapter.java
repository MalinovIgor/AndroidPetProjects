package com.example.pharmacies_analysis.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacies_analysis.R;
import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.databinding.SearchResultItemBinding;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {
    private List<Medicine> medicines;
    private OnItemClickListener onItemClickListener;
    public static final int ADD = 0;
    public static final int DELETE = 1;

    public SearchResultAdapter(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public SearchResultAdapter.SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    SearchResultItemBinding searchResultItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.search_result_item,
                    parent, false);
        return new SearchResultViewHolder(searchResultItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultAdapter.SearchResultViewHolder holder, int position) {
        Medicine currentMedicine = medicines.get(position);
        holder.searchResultListItemBinding.setMedicine(currentMedicine);
        holder.searchResultListItemBinding.addItem.setOnClickListener(v -> {
            if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                onItemClickListener.onItemClick(holder, medicines.get(position), ADD);
            }
        });
        holder.searchResultListItemBinding.addedItem.setOnClickListener(v -> {
            if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                onItemClickListener.onItemClick(holder, medicines.get(position), DELETE);
            }
        });
        if (medicines.get(position).isChecked){
            holder.searchResultListItemBinding.addItem.setVisibility(View.GONE);
            holder.searchResultListItemBinding.addedItem.setVisibility(View.VISIBLE);
        } else {
            holder.searchResultListItemBinding.addedItem.setVisibility(View.GONE);
            holder.searchResultListItemBinding.addItem.setVisibility(View.VISIBLE);
        }
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

    class SearchResultViewHolder extends RecyclerView.ViewHolder{
        SearchResultItemBinding searchResultListItemBinding;

        public SearchResultViewHolder(@NonNull SearchResultItemBinding searchResultListItemBinding) {
            super(searchResultListItemBinding.getRoot());
            this.searchResultListItemBinding = searchResultListItemBinding;
        }
    }

    public interface OnItemClickListener {

        void onItemClick(SearchResultViewHolder searchResultViewHolder, Medicine medicine, int type);
    }
}
