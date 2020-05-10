package com.example.pharmacies_analysis.ui.main.MedicinesList;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmacies_analysis.R;
import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.databinding.MedicinesItemBinding;
import com.example.pharmacies_analysis.databinding.MedicinesListFragmentBinding;
import com.example.pharmacies_analysis.ui.ViewModelFactory;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MedicinesListFragment extends Fragment {

    private MedicinesListViewModel mViewModel;
    TextView mEmpty;
    MedicinesListAdapter mAdapter;
    List<Medicine> medicines;

    public static MedicinesListFragment newInstance() {
        return new MedicinesListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MedicinesListFragmentBinding medicinesListBinding = DataBindingUtil.inflate(inflater,
                R.layout.medicines_list_fragment, container, false);
        View v = medicinesListBinding.getRoot();
        mEmpty = medicinesListBinding.emptyList;

        RecyclerView recyclerView = medicinesListBinding.medicinesList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        ViewModelFactory ViewModelFactory = new ViewModelFactory(getContext());
        mViewModel = new ViewModelProvider(this, ViewModelFactory).get(MedicinesListViewModel.class);
        mAdapter = new MedicinesListAdapter(medicine -> mViewModel.delete(medicine));
        recyclerView.setAdapter(mAdapter);
        setData();
        return v;
    }

    private void setData() {
        mViewModel.getAllMedicines()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showData);
    }

    private void showData(List<Medicine> medicines) {
        this.medicines = medicines;
        if (this.medicines.size() == 0){
            mEmpty.setVisibility(View.VISIBLE);
        }
        else {
            mEmpty.setVisibility(View.GONE);
            mAdapter.setMedicinesList(medicines);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MedicinesListViewModel.class);
        // TODO: Use the ViewModel
    }

}
