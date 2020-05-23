package com.example.pharmacies_analysis.ui.main.MedicinesList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pharmacies_analysis.R;
import com.example.pharmacies_analysis.databinding.MedicinesListFragmentBinding;
import com.example.pharmacies_analysis.ui.OnItemClickListener;
import com.example.pharmacies_analysis.ui.ViewModelFactory;
import com.example.pharmacies_analysis.ui.main.MedicinesListViewModel;

public class MedicinesListFragment extends Fragment {

    private MedicinesListViewModel mViewModel;
    private OnItemClickListener mOnItemClickListener = medicine -> mViewModel.delete(medicine);

    public static MedicinesListFragment newInstance() {
        return new MedicinesListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MedicinesListFragmentBinding medicinesListBinding = DataBindingUtil.inflate(inflater,
                R.layout.medicines_list_fragment, container, false);
        ViewModelFactory viewModelFactory = new ViewModelFactory(requireActivity().getApplication(), mOnItemClickListener);
        mViewModel = new ViewModelProvider(this, viewModelFactory).get(MedicinesListViewModel.class);
        medicinesListBinding.setVm(mViewModel);
        medicinesListBinding.setLifecycleOwner(this);
        return medicinesListBinding.getRoot();
    }

}
